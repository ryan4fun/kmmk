package com.gps.ui;

import java.util.ArrayList;
import java.util.List;


public class Function{
	public String name;		
	public List<Link> links;
	public String roles;
	private Function(){
		
	}
	public Function(String name, String roles){
		this.name = name;
		this.roles = roles;
		links = new ArrayList<Link>();
	}
}