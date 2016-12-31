package com.grotek.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grotek.pojo.ProductBox;

public class BoxNote implements Serializable {

	private int typeid;
	private String type;
	private List<ProductBox> boxs;
	
	public BoxNote(int tid,String tname) {
		// TODO Auto-generated constructor stub
		typeid = tid;
		type = tname;
		boxs = new ArrayList<ProductBox>();
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ProductBox> getBoxs() {
		return boxs;
	}

	public void setBoxs(List<ProductBox> boxs) {
		this.boxs = boxs;
	}
	
	
}
