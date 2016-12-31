package com.grotek.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grotek.pojo.ProductPack;

public class PackNote implements Serializable {

	private int typeid;
	private String type;
	private List<ProductPack> packs;
	
	public PackNote(int tid,String tname){
		typeid = tid;
		type = tname;
		packs = new ArrayList<ProductPack>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ProductPack> getPacks() {
		return packs;
	}

	public void setPacks(List<ProductPack> packs) {
		this.packs = packs;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
	
}
