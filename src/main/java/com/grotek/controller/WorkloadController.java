package com.grotek.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.pojo.Employee;
import com.grotek.pojo.JobtypeDic;
import com.grotek.pojo.Weekly;
import com.grotek.pojo.WeeklyItem;
import com.grotek.service.EmployeeService;
import com.grotek.service.JobTypeService;
import com.grotek.service.WeekliesService;
import com.grotek.util.DateTools;

@Controller
@RequestMapping("/manager")
public class WorkloadController {
	
	@Autowired
	private WeekliesService weekliesService;
	
	@Autowired
	private EmployeeService emService;
	
	@Autowired
	private JobTypeService jobtypeService;
	
	@RequestMapping(value = "/workload/works",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "1") int size){
		List<Weekly> weeklies = null;
		Weekly weekly = null;
		List<String> shortdates = new ArrayList<String>();
		int sumcount = 0;
		weeklies = weekliesService.getACheckPending(new PageRequest(page, size));
		sumcount = weekliesService.allCountACheckPending();
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
		return "workload/works";
	}
	
	@RequestMapping(value = "/workload/checkeds",method=RequestMethod.GET)
	public String checkeds(Model model, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "1") int size){
		List<Weekly> weeklies = null;
		Weekly weekly = null;
		List<String> shortdates = new ArrayList<String>();
		int sumcount = 0;
		weeklies = weekliesService.getAChecked(new PageRequest(page, size));
		sumcount = weekliesService.allCountAChecked();
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
		return "workload/checkeds";
	}
	
	@RequestMapping(value = "/workload/back", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		Weekly weekly = weekliesService.getByid(id);
		weekly.setRemark("");
		weekly.setChecked(1);
		weekliesService.editWeekly(weekly);
		return "true";
	}
	
	@RequestMapping(value = "/workload/new",method=RequestMethod.GET)
	public String newProjectWork(Model model,@RequestParam(required = true, defaultValue = "") String id){
		Employee employee = emService.getById(id);
		Date date = DateTools.getPreWeek();
		Weekly weekly = weekliesService.getByDate(employee.getId(), date);
		if(weekly==null){
			weekly = new Weekly();
			weekly.setChecked(0);
			weekly.setEmployee(employee);
			weekly.setEstatus(0);
			weekly.setSid(employee.getSuperior());
			weekly.setStime(DateTools.getMonday(date));
			weekly.setEtime(DateTools.getSunday(date));
			weekliesService.createWeekly(weekly);
		}
		model.addAttribute("weekly", weekly);
		model.addAttribute("em", employee);
		return "workload/edit";
	}
	
	@RequestMapping(value = "/workload/edit",method=RequestMethod.GET)
	public String edit(Model model, @RequestParam(required = true, defaultValue = "0") int id
			,@RequestParam(required = true, defaultValue = "") String eid){
		Employee employee = emService.getById(eid);
		Weekly weekly = weekliesService.getByid(id);
		model.addAttribute("weekly", weekly);
		model.addAttribute("em", employee);
		return "workload/edit";
	}
	
	@RequestMapping(value = "/workload/editone", method = RequestMethod.POST)
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
				eWeekly.setChecked(2);
				eWeekly.setEstatus(1);
				weekliesService.editWeekly(eWeekly);
			}
			
			return "redirect:checkeds";
		} else {
			return "workload/edit";
		}
	}
	
	@RequestMapping(value = "/workload/jobtypes",method=RequestMethod.GET)
	public String jobtypes(Model model){
		
		List<JobtypeDic> types = jobtypeService.getAll();
		
		model.addAttribute("types", types);
		return "workload/jobtypes";
	}
	
	@RequestMapping(value = "/workload/navigatework", method = RequestMethod.POST)
	@ResponseBody
	public String navigatework(@RequestParam(required = true) Integer id) {
		Weekly weekly = weekliesService.getByid(id);
		weekly.setChecked(2);
		weekly.setRemark("");
		weekliesService.editWeekly(weekly);
		return "true";
	}
	@RequestMapping(value = "/workload/denywork", method = RequestMethod.POST)
	public String denywork(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String wid = multipartRequest.getParameter("wid");
		String remark = multipartRequest.getParameter("remark");
		Weekly weekly = weekliesService.getByid(Integer.parseInt(wid));
		weekly.setRemark(remark);
		weekly.setChecked(3);
		weekly.setEstatus(0);
		weekliesService.editWeekly(weekly);
		return "redirect:works";
	}
}
