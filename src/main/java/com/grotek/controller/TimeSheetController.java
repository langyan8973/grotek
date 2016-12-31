package com.grotek.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerValue;
import com.grotek.pojo.Employee;
import com.grotek.pojo.JobtypeDic;
import com.grotek.pojo.ProductPackApItem;
import com.grotek.pojo.ProductPageApItem;
import com.grotek.pojo.ProductboxApItem;
import com.grotek.pojo.ProjectWork;
import com.grotek.pojo.Weekly;
import com.grotek.pojo.WeeklyItem;
import com.grotek.service.DealerService;
import com.grotek.service.DicsService;
import com.grotek.service.EmployeeService;
import com.grotek.service.JobTypeService;
import com.grotek.service.ProjectWorkService;
import com.grotek.service.UserContext;
import com.grotek.service.WeekliesService;
import com.grotek.util.DateTools;

@Controller
@RequestMapping("/work")
public class TimeSheetController {
	
	@Autowired
	private WeekliesService weekliesService;
	
	@Autowired
	private JobTypeService jobtypeService;
	
	@Autowired
	private UserContext userContext;
	
	@Autowired
	private EmployeeService emService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/timesheet/index",method=RequestMethod.GET)
	public String index(Model model,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "1") int size){
		Employee employee = userContext.getCurrentUser();
		int prestatus = 0;
		int currentstatus = 0;
		Weekly cweekly = weekliesService.getByDate(employee.getId(), new Date());
		if(cweekly==null){
			currentstatus=1;
		}
		else {
			currentstatus=0;
		}
		if(DateTools.afterTue(new Date())){
			prestatus=0;
		}
		else{
			Date date = DateTools.getPreTwoday();
			Weekly pweekly = weekliesService.getByDate(employee.getId(), date);
			if(pweekly==null){
				prestatus = 1;
			}
			else{
				prestatus = 0;
			}
		}
		
		List<Weekly> weeklies = null;
		int sumcount = 0;
		weeklies = weekliesService.getByEid(employee.getId(), new PageRequest(page, size));
		sumcount = weekliesService.allCountByEid(employee.getId());
		Weekly weekly = null;
		List<String> shortdates = new ArrayList<String>();
		if(weeklies!=null && weeklies.size()>0){
			weekly = weeklies.get(0);
			shortdates.add(DateTools.FormateDateOnlyMonth(weekly.getStime()));
			for (int i = 1; i < 7; i++) {
				shortdates.add(DateTools.FormateDateOnlyMonth(DateTools.getNextday(weekly.getStime(), i)));
			}
			double mon=0,tue=0,wed=0,thu=0,fri=0,sat=0,sun=0,sum=0;
			if(weekly.getItems()!=null && weekly.getItems().size()>0){
				for (Iterator iterator = weekly.getItems().iterator(); iterator.hasNext();) {
					WeeklyItem item = (WeeklyItem) iterator.next();
					if(item.getMon()!=null)
						mon=mon+item.getMon();
					if(item.getTue()!=null)
						tue=tue+item.getTue();
					if(item.getWed()!=null)
						wed=wed+item.getWed();
					if(item.getThu()!=null)
						thu=thu+item.getThu();
					if(item.getFri()!=null)
						fri=fri+item.getFri();
					if(item.getSat()!=null)
						sat=sat+item.getSat();
					if(item.getSun()!=null)
						sun=sun+item.getSun();
					if(item.getTotal()!=null)
						sum = sum+item.getTotal();
				}
				WeeklyItem weeklyItem = new WeeklyItem();
				weeklyItem.setContent("合计");
				weeklyItem.setMon(mon);
				weeklyItem.setTue(tue);
				weeklyItem.setWed(wed);
				weeklyItem.setThu(thu);
				weeklyItem.setFri(fri);
				weeklyItem.setSat(sat);
				weeklyItem.setSun(sun);
				weeklyItem.setTotal(sum);
				weekly.getItems().add(weeklyItem);
				weekly.setCount(weekly.getItems().size());
			}
		}
		model.addAttribute("weekly", weekly);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("shortdates", shortdates);
		model.addAttribute("pstatus", prestatus);
		model.addAttribute("cstatus", currentstatus);
		return "timesheet/index";
	}
	
	@RequestMapping(value = "/timesheet/new",method=RequestMethod.GET)
	public String newProjectWork(Model model){
		Employee employee = userContext.getCurrentUser();
		Date date = new Date();
		Weekly weekly = weekliesService.getByDate(employee.getId(), date);
		if(weekly==null){
			weekly = new Weekly();
			weekly.setChecked(0);
			weekly.setEmployee(employee);
			weekly.setEstatus(0);
			employee = emService.getById(employee.getId());
			weekly.setSid(employee.getSuperior());
			weekly.setStime(DateTools.getMonday(date));
			weekly.setEtime(DateTools.getSunday(date));
			weekliesService.createWeekly(weekly);
		}
		model.addAttribute("weekly", weekly);
		return "timesheet/edit";
	}
	
	@RequestMapping(value = "/timesheet/newpre",method=RequestMethod.GET)
	public String newpre(Model model){
		Employee employee = userContext.getCurrentUser();
		Date date = DateTools.getPreTwoday();
		Weekly weekly = weekliesService.getByDate(employee.getId(), date);
		if(weekly==null){
			weekly = new Weekly();
			weekly.setChecked(0);
			weekly.setEmployee(employee);
			weekly.setEstatus(0);
			employee = emService.getById(employee.getId());
			weekly.setSid(employee.getSuperior());
			weekly.setStime(DateTools.getMonday(date));
			weekly.setEtime(DateTools.getSunday(date));
			weekliesService.createWeekly(weekly);
		}
		model.addAttribute("weekly", weekly);
		return "timesheet/edit";
	}
	
	@RequestMapping(value = "/timesheet/edit",method=RequestMethod.GET)
	public String edit(Model model, @RequestParam(required = true, defaultValue = "0") int id){
		Employee employee = userContext.getCurrentUser();
		Weekly weekly = weekliesService.getByid(id);
		model.addAttribute("weekly", weekly);
		return "timesheet/edit";
	}
	
	@RequestMapping(value = "/timesheet/editone", method = RequestMethod.POST)
	public String editOne(@ModelAttribute("weekly") Weekly weekly, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		if (weekly != null) {
			Weekly eWeekly = weekliesService.getByid(weekly.getId());
			if(eWeekly.getItems()!=null && eWeekly.getItems().size()>0){
				for (Iterator iterator = eWeekly.getItems().iterator(); iterator.hasNext();) {
					WeeklyItem item = (WeeklyItem) iterator.next();
					weekliesService.deleteItem(item.getId());
				}
			}
			
			for (Iterator iterator = weekly.getItems().iterator(); iterator.hasNext();) {
				WeeklyItem item = (WeeklyItem) iterator.next();
				if(item.getId()==null && item.getDealer()==null && item.getType()==null && item.getContent()==null){
					continue;
				}
				item.setWid(eWeekly.getId());
				double total=0;
				if(item.getMon()!=null){
					total+=item.getMon();
				}
				if(item.getTue()!=null){
					total+=item.getTue();
				}
				if(item.getWed()!=null){
					total+=item.getWed();
				}
				if(item.getThu()!=null){
					total+=item.getThu();
				}
				if(item.getFri()!=null){
					total+=item.getFri();
				}
				if(item.getSat()!=null){
					total+=item.getSat();
				}
				if(item.getSun()!=null){
					total+=item.getSun();
				}
				item.setTotal(total);
				item.setStatus(0);
				weekliesService.addItem(item);
				eWeekly.setChecked(0);
				eWeekly.setRemark("");
				weekliesService.editWeekly(eWeekly);
			}
			
			return "redirect:index";
		} else {
			return "timesheet/edit";
		}
	}
	
	@RequestMapping(value = "/timesheet/commit", method = RequestMethod.POST)
	@ResponseBody
	public String commit(@RequestParam(required = true) Integer id) {
		Weekly weekly = weekliesService.getByid(id);
		weekly.setChecked(0);
		weekly.setEstatus(1);
		weekly.setRemark("");
		weekliesService.editWeekly(weekly);
		return "true";
	}
}
