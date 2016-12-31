package com.grotek.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.ExsRbsm;
import com.grotek.pojo.ExsRbsmItem;


public interface ExsRbsmService {

	public List<ExsRbsm> getByEid(String eid,PageRequest pageRequest);
	
	public int allCountByEid(String eid);
	
	public List<ExsRbsm> findExsRbsms(String eid,String key,PageRequest pageRequest);
	
	public int searchCountEid(String eid,String key);
	
	public List<ExsRbsm> getExsRbsms(PageRequest pageRequest);
	
	public int allCount();
	
	public List<ExsRbsm> findByFullname(String key,PageRequest pageRequest);
	
	public int searchCountByFullname(String key);
	
	public List<ExsRbsm> getByEidAndMonth(String eid,Date stime,Date etime);
	
	public ExsRbsm getByid(int id);
	
	public int createExsRbsm(ExsRbsm exsRbsm);
	
	public int editExsRbsm(ExsRbsm exsRbsm);
	
	public int deleteExsRbsm(int id);
	
	public int addEmployeeFee(EmployeeFee employeeFee);
	
	public int addExsRbsmItem(ExsRbsmItem item);
	
	public int deleteExsRbsmItem(int id);
	
	public List<ExsRbsm> getCheckpending(PageRequest pageRequest);
	
	public int allCheckpendingCount();
	
	public List<ExsRbsm> findCheckpending(String key,PageRequest pageRequest);
	
	public int searchCountCheckpending(String key);
	
	public List<ExsRbsm> getChecked(PageRequest pageRequest);
	
	public int allCheckedCount();
	
	public List<ExsRbsm> findChecked(String key,PageRequest pageRequest);
	
	public int searchCountChecked(String key);
	
	public List<ExsRbsm> getACheckpending(PageRequest pageRequest);
	
	public int allACheckpendingCount();
	
	public List<ExsRbsm> findACheckpending(String key,PageRequest pageRequest);
	
	public int searchCountACheckpending(String key);
	
	public List<ExsRbsm> getAChecked(PageRequest pageRequest);
	
	public int allACheckedCount();
	
	public List<ExsRbsm> findAChecked(String key,PageRequest pageRequest);
	
	public int searchCountAChecked(String key);
	
}
