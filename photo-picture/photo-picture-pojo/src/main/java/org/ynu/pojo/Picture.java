package org.ynu.pojo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Picture implements Serializable{
	
	@JsonIgnore
	private Long picid;
	private Photographer photographer;
	private String url;
	
	public Picture() {
		
	}

	public Picture(Long picid, Photographer photographer, String url) {
		this.picid = picid;
		this.photographer = photographer;
		this.url = url;
	}

	public Long getPicid() {
		return picid;
	}

	public void setPicid(Long picid) {
		this.picid = picid;
	}

	public Photographer getPhotographer() {
		return photographer;
	}

	public void setPhotographer(Photographer photographer) {
		this.photographer = photographer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
