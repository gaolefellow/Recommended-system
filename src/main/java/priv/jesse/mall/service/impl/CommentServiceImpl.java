package priv.jesse.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import priv.jesse.mall.dao.CommentDao;
import priv.jesse.mall.entity.Comment;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Override
	public void creat(Comment comment) {
		
		commentDao.save(comment);
	}
	
	@Override
    public List<Comment> findNewComment(Pageable pageable,int item_id) {
		System.out.print("getNewComment");
        return commentDao.findByItemId(pageable,item_id);
    }
}
