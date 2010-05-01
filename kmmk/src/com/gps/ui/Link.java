package com.gps.ui;

public class Link{
	public String name;		
	public String url;
	public String roles;
	public String img;
	
	private Link(){
	}
	public Link(String name, String url, String roles,String img){
		this.name = name;
		this.url = url;
		this.roles = roles;
		this.img = img==null?"":img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}