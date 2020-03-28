package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.jesse.mall.dao.BehaviorDao;
import priv.jesse.mall.entity.Behavior;
import priv.jesse.mall.service.BehaviorService;

@Service
public class BehaviorServiceImpl implements BehaviorService{
	@Autowired
	BehaviorDao behaviorDao;

	@Override
	public void creat(Behavior behavior) {
		
		behaviorDao.save(behavior);
	}
}
