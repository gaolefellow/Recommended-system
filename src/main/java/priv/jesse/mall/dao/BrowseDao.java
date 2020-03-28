package priv.jesse.mall.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import priv.jesse.mall.entity.Browse;

public interface BrowseDao extends JpaRepository<Browse, Integer>{
	
	@Query(value = "SELECT * FROM browse b where b.buyer_id =?1 and b.item_id =?2",nativeQuery = true)
	Browse findBybuerIdAndItemId(@Param("buyer_id")int buyer_id,@Param("item_id")int item_id);
}
