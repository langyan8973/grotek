package com.grotek.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.Weekly;
import com.grotek.pojo.WeeklyItem;


public interface WeekliesService {

	public List<Weekly> getByEid(String eid,PageRequest pageRequest);
	
	public int allCountByEid(String eid);
	
	public List<Weekly> getBySid(String sid,PageRequest pageRequest);
	
	public int allCountBySid(String sid);
	
	public List<Weekly> getCheckPending(String sid,PageRequest pageRequest);
	
	public int allCountCheckPending(String sid);
	
	public List<Weekly> getChecked(String sid,PageRequest pageRequest);
	
	public int allCountChecked(String sid);
	
	public Weekly getByid(int id);
	
	public Weekly getByDate(String eid,Date date);
	
	public int createWeekly(Weekly weekly);
	
	public int editWeekly(Weekly weekly);
	
	public int addItem(WeeklyItem item);
	
	public int deleteItem(int id);
	
	public List<List> staticLaborHours(String eid,Date start,Date end);
	
	public List<List> staticDealerService(String eid,Date start,Date end,double consthour);
	
	public List<Weekly> allByEid(String eid,PageRequest pageRequest);
	
	public int checkedCount(String eid);
	
	public List<Weekly> getACheckPending(PageRequest pageRequest);
	
	public int allCountACheckPending();
	
	public List<Weekly> getAChecked(PageRequest pageRequest);
	
	public int allCountAChecked();
	
}
