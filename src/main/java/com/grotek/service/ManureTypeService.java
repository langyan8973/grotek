package com.grotek.service;

import java.util.List;

import com.grotek.pojo.ManureType;

public interface ManureTypeService {

	public int insertOne(ManureType type);
	
	public int delete(int id);
	
	public int updateOne(ManureType type);
	
	public ManureType getById(int id);
	
	public List<ManureType> getAll();
	
	public int check(String name);
}
