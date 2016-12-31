package com.grotek.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grotek.pojo.Dealer;

public class DealersNote implements Serializable {

	private int sid;
	
	private String sname;
	
	private List<Dealer> dealers;
	
	public DealersNote(int id,String name){
		
		sid = id;
		sname = name;
		dealers=new ArrayList<Dealer>();
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public List<Dealer> getDealers() {
		return dealers;
	}

	public void setDealers(List<Dealer> dealers) {
		this.dealers = dealers;
	}
	
}
