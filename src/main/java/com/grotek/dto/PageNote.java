package com.grotek.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grotek.pojo.ProductPage;

public class PageNote implements Serializable {

	private int typeid;
	private String type;
	private List<ProductPage> pages;
	
	public PageNote(int tid,String tname){
		typeid = tid;
		type = tname;
		pages = new ArrayList<ProductPage>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ProductPage> getPages() {
		return pages;
	}

	public void setPages(List<ProductPage> pages) {
		this.pages = pages;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
}
