package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeProm;

public interface PromoteService {
	
	public List<EmployeeProm> getPromotes(PageRequest pageable);
	
	public List<EmployeeProm> getEmployeePromsByEid(String eid);
	
	public int addProm(Employee employee,String des);
	
	public int allCount();
	
	public int delete(int id);

}
