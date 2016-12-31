package com.grotek.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.grotek.pojo.Employee;

public class EmployeeNote extends Employee implements Serializable {

	private List<EmployeeNote> notes;
	
	public EmployeeNote(Employee employee){
		this.setAddress(employee.getAddress());
		this.setCode(employee.getCode());
		this.setDept(employee.getDept());
		this.setFullname(employee.getFullname());
		this.setHeadimg(employee.getHeadimg());
		this.setId(employee.getId());
		this.setMobile(employee.getMobile());
		this.setPhone(employee.getPhone());
		this.setPosition(employee.getPosition());
		this.setPostcode(employee.getPostcode());
		this.setRegform(employee.getRegform());
		this.setSex(employee.getSex());
		this.setSname(employee.getSname());
		this.setStatus(employee.getStatus());
		this.setSuperior(employee.getSuperior());
		this.notes = new ArrayList<EmployeeNote>();
	}

	public List<EmployeeNote> getNotes() {
		return notes;
	}

	public void setNotes(List<EmployeeNote> notes) {
		this.notes = notes;
	}
	
	
	
}
