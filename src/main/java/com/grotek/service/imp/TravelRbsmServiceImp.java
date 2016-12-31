package com.grotek.service.imp;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.TravelRbsmItemMapper;
import com.grotek.dao.TravelRbsmMapper;
import com.grotek.pojo.TravelRbsm;
import com.grotek.pojo.TravelRbsmItem;
import com.grotek.service.TravelRbsmService;
@Service("travelrbsmService")
@org.springframework.transaction.annotation.Transactional
public class TravelRbsmServiceImp implements TravelRbsmService {

	@Autowired
	private TravelRbsmMapper travelRbsmDao;
	@Autowired
	private TravelRbsmItemMapper itemDao;
	
	@Override
	public List<TravelRbsm> getByEid(String eid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.getTravelRbsmsByEid(eid, pageRequest);
	}

	@Override
	public int allCountByEid(String eid) {
		// TODO Auto-generated method stub
		return travelRbsmDao.allCountByEid(eid);
	}

	@Override
	public List<TravelRbsm> findTravelRbsms(String eid, String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.findTravelRbsms(eid, key, pageRequest);
	}

	@Override
	public int searchCountEid(String eid, String key) {
		// TODO Auto-generated method stub
		return travelRbsmDao.searchCountByEid(eid, key);
	}

	@Override
	public TravelRbsm getByid(int id) {
		// TODO Auto-generated method stub
		return travelRbsmDao.selectByPrimaryKey(id);
	}

	@Override
	public int createTravelRbsm(TravelRbsm travelRbsm) {
		// TODO Auto-generated method stub
		travelRbsm.setStatus(0);
		travelRbsm.setChecked(0);
		travelRbsm.setPaystatus(0);
		return travelRbsmDao.insert(travelRbsm);
	}

	@Override
	public int editTravelRbsm(TravelRbsm travelRbsm) {
		// TODO Auto-generated method stub
		return travelRbsmDao.updateByPrimaryKey(travelRbsm);
	}

	@Override
	public int deleteTravelRbsm(int id) {
		// TODO Auto-generated method stub
		return travelRbsmDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<TravelRbsm> getTravelRbsms(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.getTravelRbsms(pageRequest);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return travelRbsmDao.allCount();
	}

	@Override
	public List<TravelRbsm> findByFullname(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.findByFullname(key, pageRequest);
	}

	@Override
	public int searchCountByFullname(String key) {
		// TODO Auto-generated method stub
		return travelRbsmDao.searchCountByFullname(key);
	}

	@Override
	public List<TravelRbsm> getByEidAndMonth(String eid, Date stime, Date etime) {
		// TODO Auto-generated method stub
		return travelRbsmDao.getByEidAndDate(eid, stime, etime);
	}

	@Override
	public int addTravelRbsmItem(TravelRbsmItem item) {
		// TODO Auto-generated method stub
		item.setStatus(0);
		return itemDao.insert(item);
	}

	@Override
	public int deleteTravelRbsmItem(int id) {
		// TODO Auto-generated method stub
		return itemDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<TravelRbsm> getCheckpendingTravelRbsms(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.getCheckpendingTravelRbsms(pageRequest);
	}

	@Override
	public int allCheckpendingCount() {
		// TODO Auto-generated method stub
		return travelRbsmDao.allCheckpendingCount();
	}

	@Override
	public List<TravelRbsm> findCheckpending(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.findCheckPendingByFullname(key, pageRequest);
	}

	@Override
	public int searchCountCheckpending(String key) {
		// TODO Auto-generated method stub
		return travelRbsmDao.searchCheckPendingCountByFullname(key);
	}

	@Override
	public List<TravelRbsm> getCheckedTravelRbsms(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.getCheckedTravelRbsms(pageRequest);
	}

	@Override
	public int allCheckedCount() {
		// TODO Auto-generated method stub
		return travelRbsmDao.allCheckedCount();
	}

	@Override
	public List<TravelRbsm> findChecked(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.findCheckedByFullname(key, pageRequest);
	}

	@Override
	public int searchCountChecked(String key) {
		// TODO Auto-generated method stub
		return travelRbsmDao.searchCheckedCountByFullname(key);
	}
	
	@Override
	public List<TravelRbsm> getACheckpendingTravelRbsms(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.getACheckpendingTravelRbsms(pageRequest);
	}

	@Override
	public int allACheckpendingCount() {
		// TODO Auto-generated method stub
		return travelRbsmDao.allACheckpendingCount();
	}

	@Override
	public List<TravelRbsm> findACheckpending(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.findACheckPendingByFullname(key, pageRequest);
	}

	@Override
	public int searchCountACheckpending(String key) {
		// TODO Auto-generated method stub
		return travelRbsmDao.searchACheckPendingCountByFullname(key);
	}

	@Override
	public List<TravelRbsm> getACheckedTravelRbsms(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.getACheckedTravelRbsms(pageRequest);
	}

	@Override
	public int allACheckedCount() {
		// TODO Auto-generated method stub
		return travelRbsmDao.allACheckedCount();
	}

	@Override
	public List<TravelRbsm> findAChecked(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return travelRbsmDao.findACheckedByFullname(key, pageRequest);
	}

	@Override
	public int searchCountAChecked(String key) {
		// TODO Auto-generated method stub
		return travelRbsmDao.searchACheckedCountByFullname(key);
	}

}
