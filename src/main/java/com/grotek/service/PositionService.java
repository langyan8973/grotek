package com.grotek.service;

import java.util.List;

import com.grotek.pojo.PositionDic;

public interface PositionService {

	public List<PositionDic> getDicstAllPositions();
	
	public int check(String name);
	
	public int createPosition(String name,int salestargets,String objective,double amortization,
			double salary,double travelAllow,double mobileAllow,String description,double costhour);
	
	public PositionDic getById(int id);
	
	public int editPositionDic(PositionDic positionDic);
	
	public int deletePosition(int id);
	
}
