package com.grotek.service;

import com.grotek.pojo.JobtypeDic;

import java.util.List;

public interface JobTypeService {

	public List<JobtypeDic> getAll();
	
	public JobtypeDic getById(int id);
	
	public int addType(JobtypeDic type);
	
	public int editType(JobtypeDic type);
	
	public int check(String name,String code);
	
	public int deleteByid(int id);
	
}
