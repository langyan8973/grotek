package com.grotek.service;

import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeReward;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

public interface RewardService {

	public List<EmployeeReward> getRewards(PageRequest pageable);
	
	public List<EmployeeReward> getRewardsByEid(String eid);
	
	public int addReward(Employee employee,String item,double amount,String description);
	
	public int allCount();
	
	public List<EmployeeReward> getByEidAndMoth(String eid,Date stime,Date etime);
	
	public int delete(int id);
	
}
