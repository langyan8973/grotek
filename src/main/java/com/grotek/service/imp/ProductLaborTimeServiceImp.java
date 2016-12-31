package com.grotek.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.ProductLabor_TimeMapper;
import com.grotek.pojo.ProductLabor_Time;
import com.grotek.service.ProductLaborTimeService;
import com.grotek.util.DateTools;
import com.grotek.util.PublicHelper;
@Service("productlabortimeService")
@org.springframework.transaction.annotation.Transactional
public class ProductLaborTimeServiceImp implements ProductLaborTimeService {

	@Autowired
	private ProductLabor_TimeMapper timeDao;
	
	@Override
	public List<ProductLabor_Time> findProductLabor_Times(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return timeDao.findProductLabor_Times(text, pageable);
	}

	@Override
	public List<ProductLabor_Time> getProductLabor_Times(PageRequest pageable) {
		// TODO Auto-generated method stub
		return timeDao.getProductLabor_Times(pageable);
	}

	@Override
	public int searchProductLabor_TimeCount(String text) {
		// TODO Auto-generated method stub
		return timeDao.searchCount(text);
	}

	@Override
	public int allProductLabor_TimeCount() {
		// TODO Auto-generated method stub
		return timeDao.allCount();
	}

	@Override
	public int addProductLabor_Time(ProductLabor_Time productLabor_Time) {
		// TODO Auto-generated method stub
		productLabor_Time.setStatus(0);
		return timeDao.insert(productLabor_Time);
	}

	@Override
	public List<ProductLabor_Time> getProductLabor_TimeByPid(int pid,PageRequest pageable) {
		// TODO Auto-generated method stub
		return timeDao.getByPid(pid,pageable);
	}

	@Override
	public ProductLabor_Time getProductLabor_TimeById(int id) {
		// TODO Auto-generated method stub
		return timeDao.selectByPrimaryKey(id);
	}

	@Override
	public int editProductLabor_Time(ProductLabor_Time productLabor_Time) {
		// TODO Auto-generated method stub
		return timeDao.updateByPrimaryKey(productLabor_Time);
	}

	@Override
	public int deleteProductLabor_Time(int id) {
		// TODO Auto-generated method stub
		return timeDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<List> staticByMonth(Date start, Date end) {
		// TODO Auto-generated method stub
		double totalhour = 0.0;
		double totalexs = 0.0;
		List<String> dates = new ArrayList<String>();
		List<Double> hours = new ArrayList<Double>();
		List<Double> exss = new ArrayList<Double>();
		List<Double> totals = new ArrayList<Double>();
		do {
			Date stime = DateTools.getPreday(start);
			Date etime = DateTools.getFirstOfNextMonth(start);
			String datestr = DateTools.FormateDateYearMonth(start);
			dates.add(datestr);
			List<ProductLabor_Time> times = timeDao.getByMonth(stime, etime);
			double hour = 0.0;
			double exs = 0.0;
			if(times!=null && times.size()>0){
				for (Iterator iterator = times.iterator(); iterator.hasNext();) {
					ProductLabor_Time productLabor_Time = (ProductLabor_Time) iterator.next();
					if(productLabor_Time.getOverhours()!=null){
						hour+=productLabor_Time.getOverhours();
					}
					if(productLabor_Time.getWorkhours()!=null){
						hour+=productLabor_Time.getWorkhours();
					}
					if(productLabor_Time.getPay()!=null){
						exs+=productLabor_Time.getPay();
					}
				}
			}
			hours.add(hour);
			totalhour+=hour;
			exss.add(PublicHelper.correctTo(exs/10000));
			totalexs+=PublicHelper.correctTo(exs/10000);
			start = DateTools.getFirstOfNextMonth(start);
		} while (start.getTime()<DateTools.getFirstOfNextMonth(end).getTime());
		totals.add(totalhour);
		totals.add(totalexs);
		List<List> result = new ArrayList<List>();
		result.add(dates);
		result.add(hours);
		result.add(exss);
		result.add(totals);
		return result;
	}
	
}
