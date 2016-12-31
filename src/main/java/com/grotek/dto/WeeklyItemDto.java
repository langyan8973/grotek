package com.grotek.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.grotek.pojo.WeeklyItem;

public class WeeklyItemDto extends WeeklyItem {

	private String stime;
	private String etime;
	public WeeklyItemDto(WeeklyItem item){
		this.setContent(item.getContent());
		this.setDealer(item.getDealer());
		this.setFri(item.getFri());
		this.setId(item.getId());
		this.setMon(item.getMon());
		this.setSat(item.getSat());
		this.setStatus(item.getStatus());
		this.setSun(item.getSun());
		this.setThu(item.getThu());
		this.setTotal(item.getTotal());
		this.setTue(item.getTue());
		this.setType(item.getType());
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	
}
