package com.grotek.dto;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@DateTimeFormat(pattern = "yyyy-MM")
	private Date starttime;
	@DateTimeFormat(pattern = "yyyy-MM")
	private Date endtime;
	private String eid;

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	
}
