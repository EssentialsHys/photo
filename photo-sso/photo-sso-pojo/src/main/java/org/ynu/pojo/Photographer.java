package org.ynu.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Photographer implements Serializable{
	
	private static final long serialVersionUID = 3058675628451390691L;
	
	@JsonIgnore
	private Long pid;
	private String pname;
	@JsonIgnore
	private String ppwd;
	private String school;
	private Integer price;
	
	private List<String> style = new ArrayList<String>();
	
	@JsonIgnore
	private String portray = null;
	@JsonIgnore
	private String graduation = null;
	@JsonIgnore
	private String room = null;
	@JsonIgnore
	private String couple = null;
	@JsonIgnore
	private String face = null;
	
	public Photographer() {
		
	}

	public Photographer(Long pid, String pname, String ppwd, String school, int price, String portray, String graduation,
			String room, String couple, String face) {
		this.pid = pid;
		this.pname = pname;
		this.ppwd = ppwd;
		this.school = school;
		this.price = price;
		this.portray = portray;
		this.graduation = graduation;
		this.room = room;
		this.couple = couple;
		this.face = face;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPpwd() {
		return ppwd;
	}

	public void setPpwd(String ppwd) {
		this.ppwd = ppwd;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPortray() {
		return portray;
	}

	public void setPortray(String portray) {
		this.portray = portray;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getCouple() {
		return couple;
	}

	public void setCouple(String couple) {
		this.couple = couple;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public List<String> getStyle() {
		return style;
	}

	public void setStyle(List<String> style) {
		this.style = style;
	}
	
	@Override
	public String toString() {
		return "pid:"+this.pid+"pname:"+pname;
	}
}
