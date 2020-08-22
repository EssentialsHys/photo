package org.ynu.pojo;

import java.io.Serializable;
import java.util.List;

public class Shopping implements Serializable{

	private static final long serialVersionUID = 147405470611487056L;
	
	private Long spid;
	private Long oid;
	private Long iid;
	
	public Shopping() {

	}

	public Shopping(Long spid, Long oid, Long iid) {
		this.spid = spid;
		this.oid = oid;
		this.iid = iid;
	}

	public Long getSpid() {
		return spid;
	}

	public void setSpid(Long spid) {
		this.spid = spid;
	}

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Long getIid() {
		return iid;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}

	
	
	
	
	
	
	
	
	

}
