package com.grotek.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.TravelRbsm;
import com.grotek.pojo.TravelRbsmItem;


public interface TravelRbsmService {

	public List<TravelRbsm> getByEid(String eid,PageRequest pageRequest);
	
	public int allCountByEid(String eid);
	
	public List<TravelRbsm> findTravelRbsms(String eid,String key,PageRequest pageRequest);
	
	public int searchCountEid(String eid,String key);
	
	public List<TravelRbsm> getTravelRbsms(PageRequest pageRequest);
	
	public int allCount();
	
	public List<TravelRbsm> findByFullname(String key,PageRequest pageRequest);
	
	public int searchCountByFullname(String key);
	
	public List<TravelRbsm> getByEidAndMonth(String eid,Date stime,Date etime);
	
	public TravelRbsm getByid(int id);
	
	public int createTravelRbsm(TravelRbsm travelRbsm);
	
	public int editTravelRbsm(TravelRbsm travelRbsm);
	
	public int deleteTravelRbsm(int id);
	
	public int addTravelRbsmItem(TravelRbsmItem item);
	
	public int deleteTravelRbsmItem(int id);
	
	public List<TravelRbsm> getCheckpendingTravelRbsms(PageRequest pageRequest);
	
	public int allCheckpendingCount();
	
	public List<TravelRbsm> findCheckpending(String key,PageRequest pageRequest);
	
	public int searchCountCheckpending(String key);
	
	public List<TravelRbsm> getCheckedTravelRbsms(PageRequest pageRequest);
	
	public int allCheckedCount();
	
	public List<TravelRbsm> findChecked(String key,PageRequest pageRequest);
	
	public int searchCountChecked(String key);
	
	public List<TravelRbsm> getACheckpendingTravelRbsms(PageRequest pageRequest);
	
	public int allACheckpendingCount();
	
	public List<TravelRbsm> findACheckpending(String key,PageRequest pageRequest);
	
	public int searchCountACheckpending(String key);
	
	public List<TravelRbsm> getACheckedTravelRbsms(PageRequest pageRequest);
	
	public int allACheckedCount();
	
	public List<TravelRbsm> findAChecked(String key,PageRequest pageRequest);
	
	public int searchCountAChecked(String key);
	
}
