package com.grotek.dto;

import java.io.Serializable;
import java.util.List;

public class ServiceDealerDto implements Serializable {

	private Integer did;
	private String dname;
	private Integer count;
	private List<WeeklyItemDto> items;
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<WeeklyItemDto> getItems() {
		return items;
	}
	public void setItems(List<WeeklyItemDto> items) {
		this.items = items;
	}
	
	
}
