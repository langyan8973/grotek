package com.grotek.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class IncomeDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = "yyyy")
	private Date starttime;
	private String eid;

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
}
