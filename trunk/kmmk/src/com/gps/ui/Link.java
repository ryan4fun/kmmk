package com.gps.ui;

public class Link{
	public String name;		
	public String url;
	public String roles;
	private Link(){
		
	}
	public Link(String name, String url, String roles){
		this.name = name;
		this.url = url;
		this.roles = roles;
	}
}