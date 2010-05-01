package com.gps.ui;

import java.util.ArrayList;
import java.util.List;

public class Tab{
	public String name;
	public String folder;
	public List<Accordion> accordions;
	public String roles;
	public String img;
	
	private Tab(){
	}
	
	public Tab(String name, String folder, String roles,String img){
		this.name = name;
		this.folder = folder;
		this.roles = roles;
		accordions = new ArrayList<Accordion>();
		this.img = img==null?"":img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public List<Accordion> getAccordions() {
		return accordions;
	}

	public void setAccordions(List<Accordion> accordions) {
		this.accordions = accordions;
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
