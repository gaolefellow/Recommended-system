package priv.jesse.mall.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import priv.jesse.mall.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, Integer> {
	
	
    @Transactional
    //select DISTINCT v.id,v.title,v.count,case when vu.user_id is null then 'false' else 'true' end as flag from table1 v left join table2 vu on v.id = vu.vote_id and vu.user_id=:user order by v.id desc
   
	@Query(value = "SELECT * FROM(SELECT * FROM comments_table c join order_item o on c.item_id=o.order_id and o.product_id=:item_id) a /*#pageable*/",nativeQuery = true)
    List<Comment> findByItemId(Pageable pageable, @Param("item_id")int item_id);

}
