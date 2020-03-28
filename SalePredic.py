import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import calendar
import matplotlib.ticker as tkr
from datetime import datetime, date
import seaborn as sns
import time
import statsmodels.api as sm
import itertools


NewColumns=['datetime',"Total"]
incomedata = pd.DataFrame(columns=NewColumns)
for i in range(4):
	tickets = pd.read_csv('./Sales.csv', parse_dates=[1])
	themonth = i + 5
	dt = "2018-" + 	str(themonth) + "-01"
	dt = datetime.strptime(dt,'%Y-%m-%d')
	print(dt)
	tickets = tickets[(tickets['Hora'].dt.month == dt.month) & (tickets['Hora'].dt.year == dt.year)]
	tickets = tickets.assign(
		datetime = tickets['Hora'].map(lambda x: x.replace(microsecond=0,second=0,minute=0))
	)

	per_day = (tickets
	            .groupby(tickets.datetime.dt.day)
	            .agg({ 
	                  'Total': 'sum'})
	            .reset_index())
	monthRange = calendar.monthrange(2018,themonth)
	timestart = "2018-" + str(themonth) + "-01"
	timeend = "2018-" + str(themonth) + "-" + str(monthRange[1])
	per_day['datetime'] = pd.date_range(timestart,timeend,freq='d')
	incomedata = incomedata.append(per_day)
print(incomedata.head())

y = incomedata.set_index('datetime')
y.plot(figsize=(15, 6))
#plt.show()

#查看分布情况

from pandas import Series
from matplotlib import pyplot
pyplot.figure(1)
pyplot.subplot(211)
y.Total.hist()
pyplot.subplot(212)
y.Total.plot(kind='kde')
#pyplot.show()


#箱型图
fig, ax = plt.subplots(figsize=(15,6))
sns.boxplot(y.Total.index.year, y.Total, ax=ax)
#plt.show()

#分解图
from pylab import rcParams
rcParams['figure.figsize'] = 18, 8
decomposition = sm.tsa.seasonal_decompose(y, model='multiplicative')
fig = decomposition.plot()
#plt.show()

#自相关和偏自相关
from statsmodels.graphics.tsaplots import plot_acf
from statsmodels.graphics.tsaplots import plot_pacf

pyplot.figure()
pyplot.subplot(211)
plot_acf(y.Total, ax=pyplot.gca(), lags = 30)
pyplot.subplot(212)
plot_pacf(y.Total, ax=pyplot.gca(), lags = 30)
#pyplot.show()


# #滑动平均，标准差
# rolmean = pd.DataFrame.rolling(y, window=12).mean()
# rolstd = pd.DataFrame.rolling(y, window=12).std()

# orig = plt.plot(y, color='blue',label='Original')
# mean = plt.plot(rolmean, color='red', label='Rolling Mean')
# std = plt.plot(rolstd, color='black', label = 'Rolling Std')
# plt.legend(loc='best')
# plt.title('Rolling Mean & Standard Deviation')
# plt.show()

from statsmodels.tsa.stattools import adfuller
# print ('Results of Dickey-Fuller Test:')
# dftest = adfuller(y.Total, autolag='AIC')
# dfoutput = pd.Series(dftest[0:4], index=['Test Statistic','p-value','#Lags Used','Number of Observations Used'])
# for key,value in dftest[4].items():
#     dfoutput['Critical Value (%s)'%key] = value
# print (dfoutput)

#测试平稳性
def test_stationarity(timeseries):
    
    #Determing rolling statistics
    rolmean = pd.DataFrame.rolling(timeseries, window=12).mean()
    rolstd = pd.DataFrame.rolling(timeseries, window=12).std()
    #Plot rolling statistics:
    orig = plt.plot(timeseries, color='blue',label='Original')
    mean = plt.plot(rolmean, color='red', label='Rolling Mean')
    std = plt.plot(rolstd, color='black', label = 'Rolling Std')
    plt.legend(loc='best')
    plt.title('Rolling Mean & Standard Deviation')
    #plt.show()
    
    #Perform Dickey-Fuller test:
    print ('Results of Dickey-Fuller Test:')
    dftest = adfuller(timeseries, autolag='AIC')
    dfoutput = pd.Series(dftest[0:4], index=['Test Statistic','p-value','#Lags Used','Number of Observations Used'])
    for key,value in dftest[4].items():
        dfoutput['Critical Value (%s)'%key] = value
    print (dfoutput)

#对数变换，使其平稳
ts_log = np.log(y)
plt.plot(ts_log)

#滑动平均
moving_avg = pd.DataFrame.rolling(ts_log,1).mean()
plt.plot(ts_log)
plt.plot(moving_avg, color='red')
#plt.show()

ts_log_moving_avg_diff = ts_log.Total - moving_avg.Total
ts_log_moving_avg_diff.head(5)

ts_log_moving_avg_diff.dropna(inplace=True)
test_stationarity(ts_log_moving_avg_diff)

ts_log_diff = ts_log

#预测
from statsmodels.tsa.ar_model import AR
from random import random
#自回归
# fit model
model = AR(ts_log_diff)
model_fit = model.fit()

plt.plot(ts_log_diff)
plt.plot(model_fit.fittedvalues, color='red')
plt.title('RSS: %.6f'% np.nansum((model_fit.fittedvalues-ts_log_diff)))
#plt.show()

from sklearn.metrics import mean_squared_error, r2_score, mean_absolute_error, median_absolute_error, mean_squared_log_error




train = y['2018-05-01':'2018-08-27']
valid = y['2018-08-28':'2018-08-31']



# ARIMA example
from statsmodels.tsa.arima_model import ARIMA
from sklearn.metrics import mean_squared_error
from math import sqrt


p = d = q = range(0, 2)
pdq = list(itertools.product(p, d, q))
seasonal_pdq = [(x[0], x[1], x[2], 6) for x in list(itertools.product(p, d, q))]
print('Examples of parameter combinations for Seasonal ARIMA...')
print('SARIMAX: {} x {}'.format(pdq[1], seasonal_pdq[1]))
print('SARIMAX: {} x {}'.format(pdq[1], seasonal_pdq[2]))
print('SARIMAX: {} x {}'.format(pdq[2], seasonal_pdq[3]))
print('SARIMAX: {} x {}'.format(pdq[2], seasonal_pdq[4]))

min_aic = 999999999
for param in pdq:
    for param_seasonal in seasonal_pdq:
        try:
            mod = sm.tsa.statespace.SARIMAX(train,
                                            order=param,
                                            seasonal_order=param_seasonal,
                                            enforce_stationarity=False,
                                            enforce_invertibility=False)
            
            results = mod.fit()
            print('ARIMA{}x{}12 - AIC:{}'.format(param, param_seasonal, results.aic))
            
            #Check for best model with lowest AIC
            if results.aic < min_aic:
                min_aic = results.aic
                min_aic_model = results
        except:
            continue




start_index = valid.index.min()
print(start_index)
end_index = valid.index.max()
print(end_index)
#Predictions
pred = min_aic_model.get_prediction(start=start_index,end=end_index, dynamic=False)

#Predictions
pred_ci = pred.conf_int()
ax = y['2018-5':].plot(label='observed')
pred.predicted_mean.plot(ax=ax, label='Forecast', alpha=.7, figsize=(14, 7))
ax.fill_between(pred_ci.index,
                pred_ci.iloc[:, 0],
                pred_ci.iloc[:, 1], color='k', alpha=.2)
ax.set_xlabel('Date')
ax.set_ylabel('Total')
plt.legend()
#plt.show()

print(mean_absolute_error(y["2018-8-28":],pred.predicted_mean))
print(median_absolute_error(y["2018-8-28":],pred.predicted_mean))
print(mean_squared_log_error(y["2018-8-28":],pred.predicted_mean))

Pre = pd.DataFrame()
y.to_csv("predictData/data.csv", index=True, header=False)

Pre = y["2018-5-1":"2018-8-31"]
print(pred.predicted_mean)
Pre["2018-8-28":"2018-8-31"] = pd.DataFrame(pred.predicted_mean["2018-8-28":"2018-8-31"])
Pre.to_csv("predictData/predict.csv", index=True, header=False)

