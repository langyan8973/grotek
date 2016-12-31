package com.grotek.service;

import java.util.List;

import com.grotek.pojo.DepartmentDic;

public interface DepartmentService {

	public List<DepartmentDic> getAllDepartments();
	
	public int createDept(String name,String code);
	
	public int check(String name,String code);
	
	public DepartmentDic getByid(int id);
	
	public int editDept(DepartmentDic departmentDic);
	
	public int deleteDept(int id);
	
}
