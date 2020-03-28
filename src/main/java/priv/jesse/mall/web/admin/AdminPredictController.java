package priv.jesse.mall.web.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.jesse.mall.entity.pojo.ResultBean;

@Controller
@RequestMapping("/admin/predict")
public class AdminPredictController {
	
	
	@RequestMapping("/toList.html")
    public String toList() {
        return "admin/predict/predict";
    }
	
	/*
	 * 读取预测结果文件
	 */
	@RequestMapping("/getPredictData.do")
	@ResponseBody
    public ResultBean<List<ArrayList<String>>> getPredictData(HttpServletRequest request) throws IOException {
		
		try {
			
			String cmd = "python SalePredic.py";
			Runtime rt = Runtime.getRuntime(); // 运行时系统获取
			Process process = rt.exec("cmd.exe /c start " + cmd);		
			System.out.print("执行成功");
		} catch (Exception e) {
			
			System.out.print("执行失败");
			return readData(request);
	 
	    }
		    return readData(request);
		
		
    }
	
	 public ResultBean<List<ArrayList<String>>> readData(HttpServletRequest request) throws IOException {
			List<String> date=new ArrayList<String>();
			List<String> rawdata=new ArrayList<String>();
			List<String> predict=new ArrayList<String>();
			List<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
			
			String filename[] = {"data.csv","predict.csv"};
			for(int i=0;i<2;i++){
				String fileName = filename[i];			 
				File dir = new File("predictData");
				File dataPath = new File(dir.getPath() + "/" + fileName);
				//System.out.print(dataPath); 
				BufferedReader br = new BufferedReader(
				   new InputStreamReader(
				    new FileInputStream(dataPath)
				   )
				);
				
				String line;
				String datetime;
				String tatol;
				
				while ( (line = br.readLine()) != null ) {
				   
				   String[] info = line.split(",");			   
				   tatol = info[1].trim(); 
				   if(i==0){
					   datetime = info[0].trim();
					   date.add(datetime);
					   rawdata.add(tatol);
				   }   
				   else
					   predict.add(tatol);
				  }
			}
			
			data.add((ArrayList<String>) date);
			data.add((ArrayList<String>)rawdata);
			data.add((ArrayList<String>)predict);

			return new ResultBean<>(data);
			
	    }
}
