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

import com.grotek.dto.TravelRbsmDto;
import com.grotek.pojo.TravelRbsm;
import com.grotek.pojo.UsedCar;
import com.grotek.service.TravelRbsmService;
import com.grotek.service.UsedCarService;

@Controller
@RequestMapping("/manager")
public class TravelsController {

	@Autowired
	private TravelRbsmService travelRbsmService;
	@Autowired
	private UsedCarService usedcarService;
	
	@RequestMapping(value = "/travels/checkpending",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		List<TravelRbsmDto> rbsms = null;
		List<TravelRbsm> travelRbsms=null;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			travelRbsms = travelRbsmService.findCheckpending(context, new PageRequest(page, size));
			sumcount = travelRbsmService.searchCountCheckpending(context);
		}
		else{
			travelRbsms = travelRbsmService.getCheckpendingTravelRbsms(new PageRequest(page, size));
			sumcount = travelRbsmService.allCheckpendingCount();
		}
		if(travelRbsms!=null && travelRbsms.size()>0){
			rbsms = new ArrayList<TravelRbsmDto>();
			for (Iterator iterator = travelRbsms.iterator(); iterator.hasNext();) {
				TravelRbsm travelRbsm = (TravelRbsm) iterator.next();
				TravelRbsmDto dto = new TravelRbsmDto(travelRbsm);
				rbsms.add(dto);
			}
		}
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		return "travels/checkpending";
	}
	
	@RequestMapping(value = "/travels/checked",method=RequestMethod.GET)
	public String checked(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		List<TravelRbsmDto> rbsms = null;
		List<TravelRbsm> travelRbsms=null;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			travelRbsms = travelRbsmService.findChecked(context, new PageRequest(page, size));
			sumcount = travelRbsmService.searchCountChecked(context);
		}
		else{
			travelRbsms = travelRbsmService.getCheckedTravelRbsms(new PageRequest(page, size));
			sumcount = travelRbsmService.allCheckedCount();
		}
		if(travelRbsms!=null && travelRbsms.size()>0){
			rbsms = new ArrayList<TravelRbsmDto>();
			for (Iterator iterator = travelRbsms.iterator(); iterator.hasNext();) {
				TravelRbsm travelRbsm = (TravelRbsm) iterator.next();
				TravelRbsmDto dto = new TravelRbsmDto(travelRbsm);
				rbsms.add(dto);
			}
		}
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		return "travels/checked";
	}
	
	@RequestMapping(value = "/travels/passtravel", method = RequestMethod.POST)
	@ResponseBody
	public String passtravel(@RequestParam(required = true) Integer id) {
		TravelRbsm travelRbsm = travelRbsmService.getByid(id);
		travelRbsm.setChecked(1);
		travelRbsm.setRemark("");
		travelRbsmService.editTravelRbsm(travelRbsm);
		
		return "true";
	}

	@RequestMapping(value = "/travels/denytravel", method = RequestMethod.POST)
	public String denytravel(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String tid = multipartRequest.getParameter("tid");
		String remark = multipartRequest.getParameter("remark");
		TravelRbsm travelRbsm = travelRbsmService.getByid(Integer.parseInt(tid));
		travelRbsm.setChecked(-1);
		travelRbsm.setRemark(remark);
		travelRbsmService.editTravelRbsm(travelRbsm);
		return "redirect:checkpending";
	}
	
}
