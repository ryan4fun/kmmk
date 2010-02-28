package com.gps.ui;

import java.util.ArrayList;
import java.util.List;

public class Tab{
	public String name;
	public String folder;
	public List<Accordion> accordions;
	public String roles;
	private Tab(){
		
	}
	public Tab(String name, String folder, String roles){
		this.name = name;
		this.folder = folder;
		this.roles = roles;
		accordions = new ArrayList<Accordion>();
	}
}
