package com.grotek.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.grotek.dto.TravelRbsmDto;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.ExsRbsm;
import com.grotek.pojo.ExsRbsmItem;
import com.grotek.pojo.TravelRbsm;
import com.grotek.pojo.UsedCar;
import com.grotek.service.ExsRbsmService;
import com.grotek.service.JobTypeService;
import com.grotek.service.TravelRbsmService;
import com.grotek.service.UsedCarService;
import com.grotek.util.PublicConfig;

@Controller
@RequestMapping("/manager")
public class ExsFormsController {
	
	@Autowired
	private ExsRbsmService exsService;
	
	
	@RequestMapping(value = "/exsforms/checkpending",method=RequestMethod.GET)
	public String exses(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		List<ExsRbsm> rbsms = null;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			rbsms = exsService.findCheckpending(context, new PageRequest(page, size));
			sumcount = exsService.searchCountCheckpending(context);
		}
		else{
			rbsms = exsService.getCheckpending(new PageRequest(page, size));
			sumcount = exsService.allCheckpendingCount();
		}		
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		return "exsforms/checkpending";
	}
	
	@RequestMapping(value = "/exsforms/checked",method=RequestMethod.GET)
	public String checked(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		List<ExsRbsm> rbsms = null;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			rbsms = exsService.findChecked(context, new PageRequest(page, size));
			sumcount = exsService.searchCountChecked(context);
		}
		else{
			rbsms = exsService.getChecked(new PageRequest(page, size));
			sumcount = exsService.allCheckedCount();
		}		
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		return "exsforms/checked";
	}
	
	@RequestMapping(value = "/exsforms/exsfile",method=RequestMethod.GET)
	public String exsfile(Model model, @RequestParam(required = true, defaultValue = "0") int id){
		ExsRbsm exsRbsm = exsService.getByid(id);
		model.addAttribute("rbsm", exsRbsm);
		return "exsforms/exsfile";
	}
	
	@RequestMapping(value = "/exsforms/passexs", method = RequestMethod.POST)
	@ResponseBody
	public String passexs(@RequestParam(required = true) Integer id) {
		ExsRbsm exsRbsm = exsService.getByid(id);
		exsRbsm.setChecked(1);
		exsRbsm.setDes("");
		exsService.editExsRbsm(exsRbsm);
		
		return "true";
	}

	@RequestMapping(value = "/exsforms/denyexs", method = RequestMethod.POST)
	public String denyexs(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String tid = multipartRequest.getParameter("tid");
		String remark = multipartRequest.getParameter("remark");
		ExsRbsm exsRbsm = exsService.getByid(Integer.parseInt(tid));
		exsRbsm.setChecked(-1);
		exsRbsm.setDes(remark);
		exsService.editExsRbsm(exsRbsm);
		return "redirect:checkpending";
	}
}
