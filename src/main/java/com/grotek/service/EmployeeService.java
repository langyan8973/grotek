package com.grotek.service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.StreamingHttpOutputMessage;

import com.grotek.dto.EmployeeNote;
import com.grotek.dto.SuperiorsNote;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeGasoline;
import com.grotek.pojo.EmployeeProm;
import com.grotek.pojo.EmployeeReward;

public interface EmployeeService {
	
	public List<Employee> findEmployees(String text,PageRequest pageable);
	
	public List<Employee> getEmployees(PageRequest pageable);
	
	public List<SuperiorsNote> getSuperiors(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public int addEmployee(Employee employee);
	
	public int check(String code);
	
	public Employee getById(String id);
	
	public int editEmployee(Employee employee);
	
	public Employee getByMobile(String mobile);
	
	public int delete(String id);
	
	public EmployeeGasoline getGasolineByEid(String eid);
	
	public int deleteGasoline(int id);
	
	public int addGasoline(EmployeeGasoline employeeGasoline);
	
	public List<List> totalCost(Employee employee,Date start,Date end,Dealer dealer);
	
	public List<List> jobtype(Employee employee,Date start,Date end,Dealer dealer);
	
	public List<Employee> findMembers(String text,String sid,PageRequest pageable);
	
	public List<EmployeeNote> getMembers(String sid);
	
	public int searchCountMembers(String text,String sid);
	
	public int allCountMembers(String sid);
	
}
