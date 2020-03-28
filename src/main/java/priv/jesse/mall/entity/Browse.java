package priv.jesse.mall.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Browse implements Serializable{
	
	@Id
    @GeneratedValue
    @Column
    private Integer id;
	
	/**
     * 买家id
     */
	
    @Column
    private Integer buyer_id;
    /**
     * 用户名
     */
	
    @Column
    private Integer item_id;
    /**
     * 用户名
     */
    @Column
    private Integer click_count;
    
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
	public Integer getClick_count() {
		return click_count;
	}
	public void setClick_count(Integer click_count) {
		this.click_count = click_count;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Browse() {
		super();
	}
	public Browse(Integer id, Integer buyer_id, Integer item_id, Integer click_count) {
		super();
		this.id = id;
		this.buyer_id = buyer_id;
		this.item_id = item_id;
		this.click_count = click_count;
	}
	@Override
	public String toString() {
		return "Browse [id=" + id + ", buyer_id=" + buyer_id + ", item_id=" + item_id + ", click_count=" + click_count
				+ "]";
	}
	
	
    
    
}
