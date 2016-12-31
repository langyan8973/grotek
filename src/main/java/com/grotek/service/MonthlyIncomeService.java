package com.grotek.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.MonthlyIncome;


public interface MonthlyIncomeService {

	public List<MonthlyIncome> getByEid(String eid,PageRequest pageRequest);
	
	public int allCountByEid(String eid);
	
	public List<MonthlyIncome> find(String key,PageRequest pageRequest);
	
	public int searchCount(String key);
	
	public List<MonthlyIncome> getAll(PageRequest pageRequest);
	
	public int allCount();
	
	public MonthlyIncome getById(int id);
	
	public int createOne(MonthlyIncome monthlyIncome);
	
	public int updateOne(MonthlyIncome monthlyIncome);
	
	public List<List> getByYear(String eid,Date start,Date end);
	
}
