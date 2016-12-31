package com.grotek.service.imp;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.EmployeeFeeMapper;
import com.grotek.dao.ExsRbsmItemMapper;
import com.grotek.dao.ExsRbsmMapper;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.ExsRbsm;
import com.grotek.pojo.ExsRbsmItem;
import com.grotek.service.ExsRbsmService;
@Service("exsrbsmService")
@org.springframework.transaction.annotation.Transactional
public class ExsRbsmServiceImp implements ExsRbsmService {

	@Autowired
	private ExsRbsmMapper exsDao;
	
	@Autowired
	private EmployeeFeeMapper feeDao;
	
	@Autowired
	private ExsRbsmItemMapper itemDao;
	
	@Override
	public List<ExsRbsm> getByEid(String eid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.getExsRbsmsByEid(eid, pageRequest);
	}

	@Override
	public int allCountByEid(String eid) {
		// TODO Auto-generated method stub
		return exsDao.allCountByEid(eid);
	}

	@Override
	public List<ExsRbsm> findExsRbsms(String eid, String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.findExsRbsms(eid, key, pageRequest);
	}

	@Override
	public int searchCountEid(String eid, String key) {
		// TODO Auto-generated method stub
		return exsDao.searchCountByEid(eid, key);
	}

	@Override
	public ExsRbsm getByid(int id) {
		// TODO Auto-generated method stub
		return exsDao.selectByPrimaryKey(id);
	}

	@Override
	public int createExsRbsm(ExsRbsm exsRbsm) {
		// TODO Auto-generated method stub
		exsRbsm.setChecked(0);
		exsRbsm.setPaystatus(0);
		exsRbsm.setStatus(0);
		return exsDao.insert(exsRbsm);
	}

	@Override
	public int editExsRbsm(ExsRbsm exsRbsm) {
		// TODO Auto-generated method stub
		return exsDao.updateByPrimaryKey(exsRbsm);
	}

	@Override
	public int deleteExsRbsm(int id) {
		// TODO Auto-generated method stub
		return exsDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<ExsRbsm> getExsRbsms(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.getExsRbsms(pageRequest);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return exsDao.allCount();
	}

	@Override
	public List<ExsRbsm> findByFullname(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.findByFullname(key, pageRequest);
	}

	@Override
	public int searchCountByFullname(String key) {
		// TODO Auto-generated method stub
		return exsDao.searchCountByFullname(key);
	}

	@Override
	public int addEmployeeFee(EmployeeFee employeeFee) {
		// TODO Auto-generated method stub
		employeeFee.setStatus(0);
		return feeDao.insert(employeeFee);
	}

	@Override
	public List<ExsRbsm> getByEidAndMonth(String eid, Date stime, Date etime) {
		// TODO Auto-generated method stub
		return exsDao.getByEidAndDate(eid, stime, etime);
	}

	@Override
	public int addExsRbsmItem(ExsRbsmItem item) {
		// TODO Auto-generated method stub
		item.setStatus(0);
		return itemDao.insert(item);
	}

	@Override
	public int deleteExsRbsmItem(int id) {
		// TODO Auto-generated method stub
		return itemDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<ExsRbsm> getCheckpending(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.getCheckpending(pageRequest);
	}

	@Override
	public int allCheckpendingCount() {
		// TODO Auto-generated method stub
		return exsDao.allCheckpendingCount();
	}

	@Override
	public List<ExsRbsm> findCheckpending(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.findCheckpending(key, pageRequest);
	}

	@Override
	public int searchCountCheckpending(String key) {
		// TODO Auto-generated method stub
		return exsDao.searchCountCheckpending(key);
	}

	@Override
	public List<ExsRbsm> getChecked(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.getChecked(pageRequest);
	}

	@Override
	public int allCheckedCount() {
		// TODO Auto-generated method stub
		return exsDao.allCheckedCount();
	}

	@Override
	public List<ExsRbsm> findChecked(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.findChecked(key, pageRequest);
	}

	@Override
	public int searchCountChecked(String key) {
		// TODO Auto-generated method stub
		return exsDao.searchCountChecked(key);
	}
	
	@Override
	public List<ExsRbsm> getACheckpending(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.getACheckpending(pageRequest);
	}

	@Override
	public int allACheckpendingCount() {
		// TODO Auto-generated method stub
		return exsDao.allACheckpendingCount();
	}

	@Override
	public List<ExsRbsm> findACheckpending(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.findACheckpending(key, pageRequest);
	}

	@Override
	public int searchCountACheckpending(String key) {
		// TODO Auto-generated method stub
		return exsDao.searchCountACheckpending(key);
	}

	@Override
	public List<ExsRbsm> getAChecked(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.getAChecked(pageRequest);
	}

	@Override
	public int allACheckedCount() {
		// TODO Auto-generated method stub
		return exsDao.allACheckedCount();
	}

	@Override
	public List<ExsRbsm> findAChecked(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return exsDao.findAChecked(key, pageRequest);
	}

	@Override
	public int searchCountAChecked(String key) {
		// TODO Auto-generated method stub
		return exsDao.searchCountAChecked(key);
	}

}
