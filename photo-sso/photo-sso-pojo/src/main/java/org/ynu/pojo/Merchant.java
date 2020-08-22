package org.ynu.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Merchant implements Serializable{

	private static final long serialVersionUID = 6118397333433431330L;
	
	@JsonIgnore
	private Long mid;
	@JsonIgnore
	private String mname;
	@JsonIgnore
	private String mpwd;
	private String maddress;
	
	public Merchant() {
		
	}
	
	public Merchant(Long mid, String mname, String mpwd, String maddress) {
		this.mid = mid;
		this.mname = mname;
		this.mpwd = mpwd;
		this.maddress = maddress;
	}

	public Long getMid() {
		return mid;
	}

	public void setMid(Long mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMpwd() {
		return mpwd;
	}

	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}

	public String getMaddress() {
		return maddress;
	}

	public void setMaddress(String maddress) {
		this.maddress = maddress;
	}
	
	
	
	
	
}
