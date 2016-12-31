package com.grotek.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.dto.DealersNote;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerGoal;
import com.grotek.pojo.EmployeeFee;

public interface DealerService {

	public List<Dealer> findDealers(String text,PageRequest pageable);
	
	public List<Dealer> getDealers(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public int addDealer(Dealer dealer);
	
	public int delete(int id);
	
	public Dealer getById(int id);
	
	public int editDealer(Dealer dealer);
	
	public List<DealersNote> getDealerNotes();
	
	public List<DealersNote> getHzDealerNotes();
	
	public DealerGoal getGoalByDid(int did);
	
	public int createGoal(DealerGoal dealerGoal);
	
	public List<List> staticBoxOut(int did,Date stime,Date etime);
	
	public List<EmployeeFee> getFeesByDid(int did,PageRequest pageable);
	
	public int feeCountBydid(int did);
	
	public EmployeeFee getFeeById(int id);
	
	public List<List> staticExpenses(int did,Date start,Date end);
	
	public List<Dealer> findDealersByEid(String eid,String text,PageRequest pageable);
	
	public List<Dealer> getDealersByEid(String eid,PageRequest pageable);
	
	public int searchCountByEid(String eid,String text);
	
	public int allCountByEid(String eid);
	
	public List<Dealer> allDealers();
}
