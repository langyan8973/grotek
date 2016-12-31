package com.grotek.service;

import java.util.List;

import com.grotek.pojo.ExsType;


public interface ExsTypeService {

	public List<ExsType> getAll();
	
	public ExsType getById(int id);
	
	public int addType(ExsType type);
	
	public int editType(ExsType type);
	
	public int check(String name);
	
	public int deleteByid(int id);
	
}
