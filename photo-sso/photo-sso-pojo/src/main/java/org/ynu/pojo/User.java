package org.ynu.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements Serializable{
	
	private static final long serialVersionUID = 4098702459433860140L;
	
	@JsonIgnore
	private Long uid;
	private String uname;
	@JsonIgnore
	private String upwd;
	private String school;
	
	public User() {
	
	}

	public User(Long uid, String uname, String upwd, String school) {
		this.uid = uid;
		this.uname = uname;
		this.upwd = upwd;
		this.school = school;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
}
