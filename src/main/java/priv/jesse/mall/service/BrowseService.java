package priv.jesse.mall.service;

import priv.jesse.mall.entity.Browse;

public interface BrowseService {
	Browse findBybuerIdAndItemId(int buyer_id,int item_id);
	
	void create(Browse browse);
	
	void update(Browse browse);
}
