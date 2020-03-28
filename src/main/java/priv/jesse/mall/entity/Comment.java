package priv.jesse.mall.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="comments_table")
public class Comment implements Serializable{
	/**
	 * 评论id
	 * 
	 */
	@Id
	@GeneratedValue
	@Column
	private String comment_id;
	
	@Column
	private Integer buyer_id;
	/**
	 * 商品id
	 * 
	 */
	@Column
	private Integer item_id;
	/**
	 * 评论内容
	 * 
	 */
	@Column
	private String comment_text;
	/**
	 * 评分
	 * 
	 */
	@Column
	private Float comment_score;
	
	@Column
	private Date comment_time;
	
	private static final long serialVersionUID = 1L;
	
	public Integer getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Integer buyer_id) {
		this.buyer_id = buyer_id;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getComment_text() {
		return comment_text;
	}
	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}
	public Float getComment_score() {
		return comment_score;
	}
	public void setComment_score(Float comment_score) {
		this.comment_score = comment_score;
	}
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setcomment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	
	@Override
	public String toString() {
		return "comments_table [buyer_id=" + buyer_id + ", item_id="
				+ item_id + ", comment_text=" + comment_text + ", comment_score=" + comment_score + ", comment_id="
				+ comment_id + ", comment_time=" + comment_time + "]";
	}
	public Comment() {
		super();
	}
	public Comment(Integer buyer_id, Integer item_id, String comment_text,
			Float comment_score, String comment_id, Date comment_time) {
		super();
		
		
		this.buyer_id = buyer_id;
		this.item_id = item_id;
		this.comment_text = comment_text;
		this.comment_score = comment_score;
		this.comment_id = comment_id;
		this.comment_time = comment_time;
	}
	
}
