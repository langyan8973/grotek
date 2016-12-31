package com.grotek.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grotek.pojo.Employee;

public class SuperiorsNote implements Serializable {
	
	private int positionid;
	
	private String positionname;
	
	private List<Employee> items;
	
	public SuperiorsNote(int id,String name){
		positionid = id;
		positionname = name;
		items = new ArrayList<Employee>();
	}

	public int getPositionid() {
		return positionid;
	}

	public void setPositionid(int positionid) {
		this.positionid = positionid;
	}

	public String getPositionname() {
		return positionname;
	}

	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}

	public List<Employee> getItems() {
		return items;
	}

	public void setItems(List<Employee> items) {
		this.items = items;
	}

	
}
