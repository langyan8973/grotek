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
public class ATravelsController {
	
	@Autowired
	private TravelRbsmService travelRbsmService;
	@Autowired
	private UsedCarService usedcarService;
	
	@RequestMapping(value = "/atravels/checkpending",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		List<TravelRbsmDto> rbsms = null;
		List<TravelRbsm> travelRbsms=null;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			travelRbsms = travelRbsmService.findACheckpending(context, new PageRequest(page, size));
			sumcount = travelRbsmService.searchCountACheckpending(context);
		}
		else{
			travelRbsms = travelRbsmService.getACheckpendingTravelRbsms(new PageRequest(page, size));
			sumcount = travelRbsmService.allACheckpendingCount();
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
		return "atravels/checkpending";
	}

	@RequestMapping(value = "/atravels/checked",method=RequestMethod.GET)
	public String checked(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		List<TravelRbsmDto> rbsms = null;
		List<TravelRbsm> travelRbsms=null;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			travelRbsms = travelRbsmService.findAChecked(context, new PageRequest(page, size));
			sumcount = travelRbsmService.searchCountAChecked(context);
		}
		else{
			travelRbsms = travelRbsmService.getACheckedTravelRbsms(new PageRequest(page, size));
			sumcount = travelRbsmService.allACheckedCount();
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
		return "atravels/checked";
	}
	
	@RequestMapping(value = "/atravels/passtravel", method = RequestMethod.POST)
	@ResponseBody
	public String passtravel(@RequestParam(required = true) Integer id) {
		TravelRbsm travelRbsm = travelRbsmService.getByid(id);
		travelRbsm.setChecked(2);
		travelRbsm.setRemark("");
		travelRbsmService.editTravelRbsm(travelRbsm);
		if(travelRbsm.getUsedCars()!=null && travelRbsm.getUsedCars().size()>0){
			for (Iterator iterator = travelRbsm.getUsedCars().iterator(); iterator.hasNext();) {
				UsedCar usedCar = (UsedCar) iterator.next();
				usedcarService.passOne(usedCar.getId());
			}
		}
		return "true";
	}

	@RequestMapping(value = "/atravels/denytravel", method = RequestMethod.POST)
	public String denytravel(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String tid = multipartRequest.getParameter("tid");
		String remark = multipartRequest.getParameter("remark");
		TravelRbsm travelRbsm = travelRbsmService.getByid(Integer.parseInt(tid));
		travelRbsm.setChecked(3);
		travelRbsm.setRemark(remark);
		travelRbsmService.editTravelRbsm(travelRbsm);
		return "redirect:checkpending";
	}
	
	@RequestMapping(value = "/atravels/paytravel", method = RequestMethod.POST)
	@ResponseBody
	public String paytravel(@RequestParam(required = true) Integer id) {
		TravelRbsm travelRbsm = travelRbsmService.getByid(id);		
		travelRbsm.setPaystatus(1);
		travelRbsmService.editTravelRbsm(travelRbsm);
		return "true";
	}
}
