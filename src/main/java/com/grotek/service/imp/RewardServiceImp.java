package com.grotek.service.imp;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.EmployeeRewardMapper;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeReward;
import com.grotek.service.RewardService;
@Service("rewardService")
@org.springframework.transaction.annotation.Transactional
public class RewardServiceImp implements RewardService {
	
	@Autowired
	private EmployeeRewardMapper employeeRewardDao;

	@Override
	public List<EmployeeReward> getRewards(PageRequest pageable) {
		// TODO Auto-generated method stub
		return employeeRewardDao.getRewards(pageable);
	}

	@Override
	public List<EmployeeReward> getRewardsByEid(String eid) {
		// TODO Auto-generated method stub
		return employeeRewardDao.getEmployeeRewardsByEid(eid);
	}



	@Override
	public int addReward(Employee employee, String item, double amount, String description) {
		// TODO Auto-generated method stub
		EmployeeReward employeeReward = new EmployeeReward();
		employeeReward.setAmount(amount);
		employeeReward.setDate(new Date());
		employeeReward.setDescription(description);
		employeeReward.setEmployee(employee);
		employeeReward.setItem(item);
		employeeReward.setStatus(0);
		return employeeRewardDao.insert(employeeReward);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return employeeRewardDao.allCount();
	}

	@Override
	public List<EmployeeReward> getByEidAndMoth(String eid, Date stime, Date etime) {
		// TODO Auto-generated method stub
		return employeeRewardDao.getByEidAndDate(eid, stime, etime);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return employeeRewardDao.deleteByPrimaryKey(id);
	}

}
