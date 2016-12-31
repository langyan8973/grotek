package com.grotek.service.imp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.ApplyForSendMapper;
import com.grotek.dao.ProductPackApItemMapper;
import com.grotek.dao.ProductPageApItemMapper;
import com.grotek.dao.ProductboxApItemMapper;
import com.grotek.dao.SampleApItemMapper;
import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.ProductPackApItem;
import com.grotek.pojo.ProductPageApItem;
import com.grotek.pojo.ProductboxApItem;
import com.grotek.pojo.SampleApItem;
import com.grotek.service.ApplyForSendService;
import com.grotek.util.DateTools;
import com.grotek.util.PublicHelper;
@Service("applyforsendService")
@org.springframework.transaction.annotation.Transactional
public class ApplyForSendServiceImp implements ApplyForSendService {
	
	@Autowired
	private ApplyForSendMapper dao;
	
	@Autowired
	private ProductboxApItemMapper boxitemDao;
	
	@Autowired
	private ProductPageApItemMapper pageitemDao;
	
	@Autowired
	private ProductPackApItemMapper packitemDao;
	
	@Autowired
	private SampleApItemMapper sampleitemDao;

	@Override
	public List<ApplyForSend> findByEid(String eid, String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.findApplyForSendsByEid(eid, key, pageRequest);
	}

	@Override
	public int searchCountByEid(String eid, String key) {
		// TODO Auto-generated method stub
		return dao.searchCountByEid(eid, key);
	}

	@Override
	public List<ApplyForSend> getByEid(String eid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getApplyForSendsByEid(eid, pageRequest);
	}

	@Override
	public int allCountByEid(String eid) {
		// TODO Auto-generated method stub
		return dao.allCountByEid(eid);
	}

	@Override
	public List<ApplyForSend> find(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.findApplyForSends(key, pageRequest);
	}

	@Override
	public int searchCount(String key) {
		// TODO Auto-generated method stub
		return dao.searchCount(key);
	}

	@Override
	public List<ApplyForSend> getAll(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getApplyForSends(pageRequest);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return dao.allCount();
	}

	@Override
	public int createOne(ApplyForSend applyForSend) {
		// TODO Auto-generated method stub
		applyForSend.setOpstatus(0);
		applyForSend.setStatus(0);
		return dao.addone(applyForSend);
	}

	@Override
	public int updateOne(ApplyForSend applyForSend) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(applyForSend);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int addboxitem(ProductboxApItem item) {
		// TODO Auto-generated method stub
		item.setStatus(0);
		return boxitemDao.insert(item);
	}

	@Override
	public int deleteboxitem(int id) {
		// TODO Auto-generated method stub
		return boxitemDao.deleteByPrimaryKey(id);
	}

	@Override
	public int addpageitem(ProductPageApItem item) {
		// TODO Auto-generated method stub
		item.setStatus(0);
		return pageitemDao.insert(item);
	}

	@Override
	public int deletepageitem(int id) {
		// TODO Auto-generated method stub
		return pageitemDao.deleteByPrimaryKey(id);
	}

	@Override
	public int addpackitem(ProductPackApItem item) {
		// TODO Auto-generated method stub
		item.setStatus(0);
		return packitemDao.insert(item);
	}

	@Override
	public int deletepackitem(int id) {
		// TODO Auto-generated method stub
		return packitemDao.deleteByPrimaryKey(id);
	}
	

	@Override
	public ApplyForSend getById(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int editboxitem(ProductboxApItem item) {
		// TODO Auto-generated method stub
		return boxitemDao.updateByPrimaryKey(item);
	}

	@Override
	public int editpackitem(ProductPackApItem item) {
		// TODO Auto-generated method stub
		return packitemDao.updateByPrimaryKey(item);
	}

	@Override
	public int editpageitem(ProductPageApItem item) {
		// TODO Auto-generated method stub
		return pageitemDao.updateByPrimaryKey(item);
	}
	
	@Override
	public int addsampleitem(SampleApItem item) {
		// TODO Auto-generated method stub
		item.setStatus(0);
		return sampleitemDao.insert(item);
	}

	@Override
	public int editsampleitem(SampleApItem item) {
		// TODO Auto-generated method stub
		return sampleitemDao.updateByPrimaryKey(item);
	}

	@Override
	public int deletesampleitem(int id) {
		// TODO Auto-generated method stub
		return sampleitemDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<List> staticSales(String eid, Date statr, Date end) {
		// TODO Auto-generated method stub
		List<String> dates = new ArrayList<String>();
		List<Double> sales = new ArrayList<Double>();
		do {
			Date stime = DateTools.getFirstOfMonth(statr);
			Date etime = DateTools.getFirstOfNextMonth(statr);
			Double sale = 0.0;
			String datestr = DateTools.FormateDateYearMonth(statr);
			List<ApplyForSend> sends = dao.getByMonth(eid, stime, etime);
			if(sends!=null && sends.size()>0){
				for (Iterator iterator = sends.iterator(); iterator.hasNext();) {
					ApplyForSend applyForSend = (ApplyForSend) iterator.next();
					if(applyForSend.getTotal()!=null){
						sale+=applyForSend.getTotal();
					}
				}
			}
			dates.add(datestr);
			double s = sale/10000;
			sales.add(PublicHelper.correctTo(s));
			statr = DateTools.getFirstOfNextMonth(statr);
		} while (statr.getTime()<=end.getTime());
		List<List> result = new ArrayList<List>();
		result.add(dates);
		result.add(sales);
		return result;
	}

	@Override
	public List<ApplyForSend> findCheckpending(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.findCheckpending(key, pageRequest);
	}

	@Override
	public int searchCountCheckpending(String key) {
		// TODO Auto-generated method stub
		return dao.searchCountCheckpending(key);
	}

	@Override
	public List<ApplyForSend> getAllCheckpending(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getCheckpending(pageRequest);
	}

	@Override
	public int allCountCheckpending() {
		// TODO Auto-generated method stub
		return dao.allCountCheckpending();
	}

	@Override
	public List<ApplyForSend> findDelivered(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.findDelivered(key, pageRequest);
	}

	@Override
	public int searchCountDelivered(String key) {
		// TODO Auto-generated method stub
		return dao.searchCountDelivered(key);
	}

	@Override
	public List<ApplyForSend> getAllDelivered(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getDelivered(pageRequest);
	}

	@Override
	public int allCountDelivered() {
		// TODO Auto-generated method stub
		return dao.allCountDelivered();
	}

}
