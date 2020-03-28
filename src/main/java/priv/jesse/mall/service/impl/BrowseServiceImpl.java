package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.jesse.mall.dao.BrowseDao;
import priv.jesse.mall.entity.Browse;
import priv.jesse.mall.service.BrowseService;

@Service
public class BrowseServiceImpl implements BrowseService{

	@Autowired
    private BrowseDao browseDao;
	
	@Override
	public Browse findBybuerIdAndItemId(int buyer_id, int item_id) {
		
		return browseDao.findBybuerIdAndItemId(buyer_id, item_id);
	}

	@Override
	public void create(Browse browse) {
		browseDao.save(browse);
		
	}

	@Override
	public void update(Browse browse) {
		browseDao.save(browse);
		
	}

}
