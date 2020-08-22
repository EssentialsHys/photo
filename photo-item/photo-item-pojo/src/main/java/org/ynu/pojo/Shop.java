package org.ynu.pojo;

public class Shop {
	
	private Long sid;
	private Long mid;
	private Long iid;
	
	public Shop() {

	}
	
	public Shop(Long sid, Long mid, Long iid) {
		this.sid = sid;
		this.mid = mid;
		this.iid = iid;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public Long getIid() {
		return iid;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}
	
	
	
	
}
