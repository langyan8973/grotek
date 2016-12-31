package com.grotek.service;

import java.util.List;

import com.grotek.pojo.GrotekUnit;

public interface GrotekUnitService {
	
	public GrotekUnit getById(int id);
	
	public List<GrotekUnit> getAll();
	
	public int addOne(GrotekUnit unit);
	
	public int editOne(GrotekUnit unit);
	
	public int delete(int id);
	
	public int check(String name);

}
