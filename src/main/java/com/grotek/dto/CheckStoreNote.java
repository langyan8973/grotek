package com.grotek.dto;

import java.io.Serializable;

public class CheckStoreNote implements Serializable {

	private int status;
	
	private String message;
	
	public CheckStoreNote(){
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
