package priv.jesse.mall.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import priv.jesse.mall.entity.Comment;


public interface CommentService {
	/**
     * 创建
     *
     * @param comment
     * @return
     */
	
	void creat(Comment comment);
	
	List<Comment> findNewComment(Pageable pageable,int item_id);
}
