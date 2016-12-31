package com.grotek.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.MonthlyIncomeMapper;
import com.grotek.dto.PieDto;
import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.MonthlyIncome;
import com.grotek.service.MonthlyIncomeService;
import com.grotek.util.DateTools;
import com.grotek.util.PublicHelper;
@Service("monthlyincomeService")
@org.springframework.transaction.annotation.Transactional
public class MonthlyIncomeServiceImp implements MonthlyIncomeService {
	
	@Autowired
	private MonthlyIncomeMapper dao;

	@Override
	public List<MonthlyIncome> getByEid(String eid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getMonthlyIncomeByEid(eid, pageRequest);
	}

	@Override
	public int allCountByEid(String eid) {
		// TODO Auto-generated method stub
		return dao.allCountByEid(eid);
	}

	@Override
	public List<MonthlyIncome> find(String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.findMonthlyIncomes(key, pageRequest);
	}

	@Override
	public int searchCount(String key) {
		// TODO Auto-generated method stub
		return dao.searchCount(key);
	}

	@Override
	public List<MonthlyIncome> getAll(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getMonthlyIncomes(pageRequest);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return dao.allCount();
	}

	@Override
	public MonthlyIncome getById(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int createOne(MonthlyIncome monthlyIncome) {
		// TODO Auto-generated method stub
		monthlyIncome.setStatus(0);
		return dao.insert(monthlyIncome);
	}

	@Override
	public int updateOne(MonthlyIncome monthlyIncome) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(monthlyIncome);
	}

	@Override
	public List<List> getByYear(String eid, Date start, Date end) {
		// TODO Auto-generated method stub
		double total = 0.0;
		List<String> dates = new ArrayList<String>();
		List<Double> incomes = new ArrayList<Double>();
		List<PieDto> pies = new ArrayList<PieDto>();
		PieDto wage = new PieDto();
		wage.setLabel("工资");
		String color = PublicHelper.randomColor();
		wage.setColor(color);
		wage.setHighlight(color);
		wage.setValue(0);
		pies.add(wage);
		PieDto divident = new PieDto();
		divident.setLabel("提成");
		color = PublicHelper.randomColor();
		divident.setColor(color);
		divident.setHighlight(color);
		divident.setValue(0);
		pies.add(divident);
		PieDto allowance = new PieDto();
		allowance.setLabel("补助");
		color = PublicHelper.randomColor();
		allowance.setColor(color);
		allowance.setHighlight(color);
		allowance.setValue(0);
		pies.add(allowance);
		PieDto award = new PieDto();
		award.setLabel("奖金");
		color = PublicHelper.randomColor();
		award.setColor(color);
		award.setHighlight(color);
		award.setValue(0);
		pies.add(award);
		do {
			Date stime = DateTools.getFirstOfMonth(start);
			Date etime = DateTools.getLastOfMonth(start);
			Double mtotal = 0.0;
			List<MonthlyIncome> monthlyIncomes = dao.getMonthlyIncomeByYear(eid, stime, etime);
			for (Iterator iterator = monthlyIncomes.iterator(); iterator.hasNext();) {
				MonthlyIncome monthlyIncome = (MonthlyIncome) iterator.next();
				if(monthlyIncome.getWages()!=null){
					total+=monthlyIncome.getWages();
					mtotal+=monthlyIncome.getWages();
					wage.setValue(wage.getValue()+PublicHelper.correctTo(monthlyIncome.getWages()/10000));
				}
				if(monthlyIncome.getDivident()!=null){
					total+=monthlyIncome.getDivident();
					mtotal+=monthlyIncome.getDivident();
					divident.setValue(divident.getValue()+PublicHelper.correctTo(monthlyIncome.getDivident()/10000));
				}
				if(monthlyIncome.getAllowance()!=null){
					total+=monthlyIncome.getAllowance();
					mtotal+=monthlyIncome.getAllowance();
					allowance.setValue(allowance.getValue()+PublicHelper.correctTo(monthlyIncome.getAllowance()/10000));
				}
				if(monthlyIncome.getAward()!=null){
					total+=monthlyIncome.getAward();
					mtotal+=monthlyIncome.getAward();
					award.setValue(award.getValue()+PublicHelper.correctTo(monthlyIncome.getAward()/10000));
				}
			}
			String datestr = DateTools.FormateDateYearMonth(start);
			dates.add(datestr);
			incomes.add(PublicHelper.correctTo(mtotal/10000));
			start = DateTools.getFirstOfNextMonth(start);
		} while (start.getTime()<DateTools.getFirstOfNextMonth(end).getTime());
		List<Double> tDoubles = new ArrayList<Double>();
		tDoubles.add(PublicHelper.correctTo(total/10000));
		List<List> result = new ArrayList<List>();
		result.add(dates);
		result.add(incomes);
		result.add(pies);
		result.add(tDoubles);
		return result;
	}

}
