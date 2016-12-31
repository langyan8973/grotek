package com.grotek.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TotalCostDto extends LaborHourDto implements Serializable {
	
	private int did;

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}
	
	

}
