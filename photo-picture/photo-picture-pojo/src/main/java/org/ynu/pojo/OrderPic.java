package org.ynu.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderPic implements Serializable{
	
	private static final long serialVersionUID = -777773576400297051L;
	
	@JsonIgnore
	private Long opid;
	private Order order;
	private String url;
	
	public OrderPic() {

	}
	
	public OrderPic(Long opid, Order order, String url) {
		this.opid = opid;
		this.order = order;
		this.url = url;
	}

	public Long getOpid() {
		return opid;
	}

	public void setOpid(Long opid) {
		this.opid = opid;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
}
