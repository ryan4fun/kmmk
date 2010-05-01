package com.gps.ui;

import java.util.ArrayList;
import java.util.List;


public class Accordion{
	public String name;		
	public List<Link> links;
	public String roles;
	public String img;
	
	private Accordion(){
	}
	public Accordion(String name,String roles,String img){
		this.name = name;			
		this.roles = roles;
		links = new ArrayList<Link>();
		this.img = img==null?"":img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
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
