package com.grotek.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeReward;
import com.grotek.pojo.ExsRbsm;
import com.grotek.pojo.MonthlyIncome;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.TravelRbsm;
import com.grotek.service.EmployeeService;
import com.grotek.service.ExsRbsmService;
import com.grotek.service.MonthlyIncomeService;
import com.grotek.service.ProductBoxService;
import com.grotek.service.RewardService;
import com.grotek.service.TravelRbsmService;
import com.grotek.util.DateTools;
import com.grotek.util.PingYinUtil;

@Controller
public class CountController {
	
	@Autowired
	private EmployeeService emService;
	
	@Autowired
	private TravelRbsmService travelRbsmService;
	
	@Autowired
	private ExsRbsmService exsService;
	
	@Autowired
	private RewardService rewardService;
	
	@Autowired
	private MonthlyIncomeService monthService;
	
	@Autowired
	private ProductBoxService boxService;

	@RequestMapping("/rest/count")
	@ResponseBody
	public String count(){
		
		List<Employee> employees = emService.getEmployees(new PageRequest(0, 10000));
		for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			double total=0;
			MonthlyIncome monthlyIncome = new MonthlyIncome();
			Date date = DateTools.getPreday();
			monthlyIncome.setEmployee(employee);
			monthlyIncome.setDate(date);
			monthlyIncome.setWages(employee.getPosition().getSalary());
			monthlyIncome.setCosthour(employee.getPosition().getCosthour());
			total+=employee.getPosition().getSalary();
			monthlyIncome.setAllowance(employee.getPosition().getMobileAllow());
			total+=employee.getPosition().getMobileAllow();
			List<TravelRbsm> travelRbsms = travelRbsmService.getByEidAndMonth(employee.getId(), 
					DateTools.getFirstOfMonth(date), DateTools.getLastOfMonth(date));
			double travels=0;
			for (Iterator iterator2 = travelRbsms.iterator(); iterator2.hasNext();) {
				TravelRbsm travelRbsm = (TravelRbsm) iterator2.next();
				travels+=travelRbsm.getSum();
			}
			List<ExsRbsm> exsRbsms = exsService.getByEidAndMonth(employee.getId(), 
					DateTools.getFirstOfMonth(date), DateTools.getLastOfMonth(date));
			for (Iterator iterator2 = exsRbsms.iterator(); iterator2.hasNext();) {
				ExsRbsm exsRbsm = (ExsRbsm) iterator2.next();
				travels+=exsRbsm.getSum();
			}
			monthlyIncome.setTravel(travels);
			total+=travels;
			double reward = 0;
			List<EmployeeReward> rewards = rewardService.getByEidAndMoth(employee.getId(), 
					DateTools.getFirstOfMonth(date), DateTools.getLastOfMonth(date));
			for (Iterator iterator2 = rewards.iterator(); iterator2.hasNext();) {
				EmployeeReward employeeReward = (EmployeeReward) iterator2.next();
				reward+=employeeReward.getAmount();
			}
			monthlyIncome.setAward(reward);
			total+=reward;
			monthService.createOne(monthlyIncome);
		}
		
		return "true";
	}
	
	@RequestMapping("/rest/pinyin")
	@ResponseBody
	public String createPinyin(){
		List<ProductBox> boxs = boxService.getProductBoxs(new PageRequest(0, 10000));
		for (Iterator iterator = boxs.iterator(); iterator.hasNext();) {
			ProductBox productBox = (ProductBox) iterator.next();
			String pinyin = PingYinUtil.getPingYin(productBox.getName());
			productBox.setPinyin(pinyin);
			boxService.editProductBox(productBox);
		}
		return "true";
	}
}
