package org.ynu.pojo;

import java.io.Serializable;

public class Order implements Serializable{
	
	private static final long serialVersionUID = -3824268815822575197L;
	
	private Long oid;
	private User user;
	private Photographer photographer;
	private String location;
	private Integer num;
	private Double totalPrice;
	private String createTime;
	private String startTime;
	private String endTime;
	
	public Order() {
		
	}
	
	public Order(Long oid, User user, Photographer photographer, String location, Integer num, Double totalPrice,
			String createTime, String startTime, String endTime) {
		this.oid = oid;
		this.user = user;
		this.photographer = photographer;
		this.location = location;
		this.num = num;
		this.totalPrice = totalPrice;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Photographer getPhotographer() {
		return photographer;
	}

	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "oid:"+this.oid+"photographer:"+this.photographer;
	}
	
	
	
}
