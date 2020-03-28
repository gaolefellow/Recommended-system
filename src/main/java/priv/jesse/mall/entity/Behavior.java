package priv.jesse.mall.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Behavior implements Serializable{

	@Id
	@GeneratedValue
	@Column
	private Integer id;
	/**
	 * 行为类型 “1”：浏览 “2”：加入购物车 “3”：购物车删除 “4”：下单 “5”：评论
	 * 
	 */
	@Column
	private String behav_status;
	/**
	 *买家id
	 * 
	 */
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
	/**
	 * 评论id
	 * 
	 */
	@Column
	private String comment_id;
	/**
	 * 评论内容
	 * 
	 */
	@Column
	private Date behav_time;
	
	private static final long serialVersionUID = 1L;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBehav_status() {
		return behav_status;
	}
	public void setBehav_status(String behav_status) {
		this.behav_status = behav_status;
	}
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
	public Date getBehav_time() {
		return behav_time;
	}
	public void setBehav_time(Date behav_time) {
		this.behav_time = behav_time;
	}
	@Override
	public String toString() {
		return "behavior [id=" + id + ", behav_status=" + behav_status + ", buyer_id=" + buyer_id + ", item_id="
				+ item_id + ", comment_text=" + comment_text + ", comment_score=" + comment_score + ", comment_id="
				+ comment_id + ", behav_time=" + behav_time + "]";
	}
	public Behavior() {
		super();
	}
	public Behavior(Integer id, String behav_status, Integer buyer_id, Integer item_id, String comment_text,
			Float comment_score, String comment_id, Date behav_time) {
		super();
		this.id = id;
		this.behav_status = behav_status;
		this.buyer_id = buyer_id;
		this.item_id = item_id;
		this.comment_text = comment_text;
		this.comment_score = comment_score;
		this.comment_id = comment_id;
		this.behav_time = behav_time;
	}
	
	

}
