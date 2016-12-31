package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.UsedCar;

public interface UsedCarService {

	public List<UsedCar> findByFullname(String name,PageRequest pageable);
	
	public List<UsedCar> getUsedCars(PageRequest pageable);
	
	public int searchCount(String name);
	
	public int allCount();
	
	public UsedCar getById(int id);
	
	public List<UsedCar> getByTiid(int tiid);
	
	public int addUsedCar(UsedCar usedCar);
	
	public int editUsedCar(UsedCar usedCar);
	
	public int deleteOne(int id);
	
	public int passOne(int id);
	
}
