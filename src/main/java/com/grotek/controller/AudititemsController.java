package com.grotek.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.pojo.Employee;
import com.grotek.pojo.ProjectWork;
import com.grotek.pojo.Weekly;
import com.grotek.pojo.WeeklyItem;
import com.grotek.service.ProjectWorkService;
import com.grotek.service.UserContext;
import com.grotek.service.WeekliesService;
import com.grotek.util.DateTools;

@Controller
@RequestMapping("/work")
public class AudititemsController {

	@Autowired
	private UserContext userContext;
	
	@Autowired
	private WeekliesService weekliesService;
	
	@RequestMapping(value = "/audititems/works",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "1") int size){
		Employee employee = userContext.getCurrentUser();
		List<Weekly> weeklies = null;
		Weekly weekly = null;
		List<String> shortdates = new ArrayList<String>();
		int sumcount = 0;
		weeklies = weekliesService.getCheckPending(employee.getId(), new PageRequest(page, size));
		sumcount = weekliesService.allCountCheckPending(employee.getId());
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
		return "audititems/works";
	}
	
	@RequestMapping(value = "/audititems/checkeds",method=RequestMethod.GET)
	public String checkeds(Model model, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "1") int size){
		Employee employee = userContext.getCurrentUser();
		List<Weekly> weeklies = null;
		Weekly weekly = null;
		List<String> shortdates = new ArrayList<String>();
		int sumcount = 0;
		weeklies = weekliesService.getChecked(employee.getId(), new PageRequest(page, size));
		sumcount = weekliesService.allCountChecked(employee.getId());
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
		return "audititems/checkeds";
	}
	
	@RequestMapping(value = "/audititems/navigatework", method = RequestMethod.POST)
	@ResponseBody
	public String navigatework(@RequestParam(required = true) Integer id) {
		Weekly weekly = weekliesService.getByid(id);
		weekly.setRemark("");
		weekly.setChecked(1);
		weekliesService.editWeekly(weekly);
		return "true";
	}
	@RequestMapping(value = "/audititems/denywork", method = RequestMethod.POST)
	public String denywork(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String wid = multipartRequest.getParameter("wid");
		String remark = multipartRequest.getParameter("remark");
		Weekly weekly = weekliesService.getByid(Integer.parseInt(wid));
		weekly.setRemark(remark);
		weekly.setChecked(-1);
		weekly.setEstatus(0);
		weekliesService.editWeekly(weekly);
		return "redirect:works";
	}
}
