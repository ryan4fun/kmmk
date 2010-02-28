package com.gps.ui;

import java.util.ArrayList;
import java.util.List;


public class Accordion{
	public String name;		
	public List<Link> links;
	public String roles;
	private Accordion(){
		
	}
	public Accordion(String name,String roles){
		this.name = name;			
		this.roles = roles;
		links = new ArrayList<Link>();
	}
}
