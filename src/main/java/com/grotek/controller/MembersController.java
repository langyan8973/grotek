package com.grotek.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grotek.dto.EmployeeNote;
import com.grotek.dto.LaborHourDto;
import com.grotek.dto.TravelRbsmDto;
import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.Employee;
import com.grotek.pojo.ExsRbsm;
import com.grotek.pojo.TravelRbsm;
import com.grotek.pojo.Weekly;
import com.grotek.pojo.WeeklyItem;
import com.grotek.service.ApplyForSendService;
import com.grotek.service.EmployeeService;
import com.grotek.service.ExsRbsmService;
import com.grotek.service.TravelRbsmService;
import com.grotek.service.UserContext;
import com.grotek.service.WeekliesService;
import com.grotek.util.DateTools;
import com.grotek.util.PublicConfig;

@Controller
@RequestMapping("/work")
public class MembersController {
	@Autowired
	private UserContext userContext;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private WeekliesService weeklyService;
	@Autowired
	private TravelRbsmService travelRbsmService;
	@Autowired
	private ExsRbsmService exsService;
	@Autowired
	private ApplyForSendService applyService;

	@RequestMapping(value = "/members/index",method=RequestMethod.GET)
	public String index(Model model){
		Employee employee = userContext.getCurrentUser();
		model.addAttribute("sid", employee.getId());
		return "members/index";
	}
	
	@RequestMapping(value = "/members/index.json", method = RequestMethod.GET)
	@ResponseBody
	public List<EmployeeNote> indexjson(@RequestParam(required = true) String sid) {
		List<EmployeeNote> notes = employeeService.getMembers(sid);
		return notes;
	}
	
	@RequestMapping(value = "/members/works",method=RequestMethod.GET)
	public String works(Model model,@RequestParam(required = true) String eid,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "1") int size){
		List<Weekly> weeklies = null;
		Employee employee = employeeService.getById(eid);
		int sumcount = 0;
		Weekly weekly = null;
		List<String> shortdates = new ArrayList<String>();
		weeklies = weeklyService.allByEid(eid, new PageRequest(page, size));
		sumcount = weeklyService.checkedCount(eid);
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
		model.addAttribute("employee", employee);
		return "members/works";
	}
	
	@RequestMapping(value = "/members/travels",method=RequestMethod.GET)
	public String travels(Model model, @RequestParam(required = true) String eid,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		Employee employee = employeeService.getById(eid);
		List<TravelRbsmDto> rbsms = null;
		int sumcount = 0;
		List<TravelRbsm> travelRbsms = travelRbsmService.getByEid(employee.getId(),  new PageRequest(page, size));
		if(travelRbsms!=null && travelRbsms.size()>0){
			rbsms = new ArrayList<TravelRbsmDto>();
			for (Iterator iterator = travelRbsms.iterator(); iterator.hasNext();) {
				TravelRbsm travelRbsm = (TravelRbsm) iterator.next();
				TravelRbsmDto dto = new TravelRbsmDto(travelRbsm);
				rbsms.add(dto);
			}
		}
		sumcount = travelRbsmService.allCountByEid(employee.getId());
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("employee", employee);
		return "members/travels";
	}
	
	@RequestMapping(value = "/members/exs",method=RequestMethod.GET)
	public String exses(Model model, @RequestParam(required = true) String eid,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		Employee employee = employeeService.getById(eid);
		List<ExsRbsm> rbsms = null;
		int sumcount = 0;
		rbsms = exsService.getByEid(employee.getId(),  new PageRequest(page, size));
		sumcount = exsService.allCountByEid(employee.getId());
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("employee", employee);
		return "members/exs";
	}
	
	@RequestMapping(value = "/members/sends",method=RequestMethod.GET)
	public String applyforsends(Model model, @RequestParam(required = true) String eid,
			@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		Employee employee = employeeService.getById(eid);
		List<ApplyForSend> applies = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			applies = applyService.findByEid(employee.getId(), context, new PageRequest(page, size));
			sumcount = applyService.searchCountByEid(employee.getId(), context);
		} else {
			applies = applyService.getByEid(employee.getId(), new PageRequest(page, size));
			sumcount = applyService.allCountByEid(employee.getId());
		}
		model.addAttribute("applies", applies);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("employee", employee);
		return "members/sends";
	}
}
