package org.ynu.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Item implements Serializable{

	private static final long serialVersionUID = 9168039664813138123L;
	
	@JsonIgnore
	private Long iid;
	private String name;
	private Double price;
	private String description;
	private Integer num;
	private String pic;
	
	public Item() {

	}
	
	public Item(Long iid, String name, Double price, String description, Integer num, String pic) {
		this.iid = iid;
		this.name = name;
		this.price = price;
		this.description = description;
		this.num = num;
		this.pic = pic;
	}

	public Long getIid() {
		return iid;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	
	
	

}
