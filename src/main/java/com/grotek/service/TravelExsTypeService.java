package com.grotek.service;

import java.util.List;

import com.grotek.pojo.TravelExsType;

public interface TravelExsTypeService {

	public List<TravelExsType> getAll();
	
	public TravelExsType getById(int id);
	
	public int addType(TravelExsType type);
	
	public int editType(TravelExsType type);
	
	public int check(String name);
	
	public int deleteByid(int id);
	
}
