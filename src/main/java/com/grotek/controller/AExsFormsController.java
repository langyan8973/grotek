package com.grotek.controller;

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

import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.ExsRbsm;
import com.grotek.pojo.ExsRbsmItem;
import com.grotek.service.ExsRbsmService;
import com.grotek.util.PublicConfig;

@Controller
@RequestMapping("/manager")
public class AExsFormsController {

	@Autowired
	private ExsRbsmService exsService;
	
	@RequestMapping(value = "/aexsforms/checkpending",method=RequestMethod.GET)
	public String exses(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		List<ExsRbsm> rbsms = null;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			rbsms = exsService.findACheckpending(context, new PageRequest(page, size));
			sumcount = exsService.searchCountACheckpending(context);
		}
		else{
			rbsms = exsService.getACheckpending(new PageRequest(page, size));
			sumcount = exsService.allACheckpendingCount();
		}		
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		return "aexsforms/checkpending";
	}
	
	@RequestMapping(value = "/aexsforms/checked",method=RequestMethod.GET)
	public String checked(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		List<ExsRbsm> rbsms = null;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			rbsms = exsService.findAChecked(context, new PageRequest(page, size));
			sumcount = exsService.searchCountAChecked(context);
		}
		else{
			rbsms = exsService.getAChecked(new PageRequest(page, size));
			sumcount = exsService.allACheckedCount();
		}		
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		return "aexsforms/checked";
	}
	
	@RequestMapping(value = "/aexsforms/passexs", method = RequestMethod.POST)
	@ResponseBody
	public String passexs(@RequestParam(required = true) Integer id) {
		ExsRbsm exsRbsm = exsService.getByid(id);
		exsRbsm.setChecked(2);
		exsRbsm.setDes("");
		exsService.editExsRbsm(exsRbsm);
		EmployeeFee employeeFee = new EmployeeFee();
		double total = 0.0;
		employeeFee.setDate(exsRbsm.getDate());
		employeeFee.setDealer(exsRbsm.getDealer());
		employeeFee.setEmployee(exsRbsm.getEmployee());
		if(exsRbsm.getItems()!=null && exsRbsm.getItems().size()>0){
			for (Iterator iterator = exsRbsm.getItems().iterator(); iterator.hasNext();) {
				ExsRbsmItem item = (ExsRbsmItem) iterator.next();
//				if(item.getType().getLabel().equals(PublicConfig.getPoster())){
//					if(employeeFee.getHbamount()==null){
//						employeeFee.setHbamount(item.getAmount());
//					}
//					else{
//						employeeFee.setHbamount(employeeFee.getHbamount()+item.getAmount());
//					}
//					total+=item.getAmount();
//				}
				if(item.getType().getLabel().equals(PublicConfig.getMeeting())){
					if(employeeFee.getHwamount()==null){
						employeeFee.setHwamount(item.getAmount());
					}
					else{
						employeeFee.setHwamount(employeeFee.getHwamount()+item.getAmount());
					}
					total+=item.getAmount();
				}
				if(item.getType().getLabel().equals(PublicConfig.getGift())){
					if(employeeFee.getLpamount()==null){
						employeeFee.setLpamount(item.getAmount());
					}
					else{
						employeeFee.setLpamount(employeeFee.getLpamount()+item.getAmount());
					}
					total+=item.getAmount();
				}
				if(item.getType().getLabel().equals(PublicConfig.getPrint())){
					if(employeeFee.getPhamount()==null){
						employeeFee.setPhamount(item.getAmount());
					}
					else{
						employeeFee.setPhamount(employeeFee.getPhamount()+item.getAmount());
					}
					total+=item.getAmount();
				}
				if(item.getType().getLabel().equals(PublicConfig.getOther())){
					if(employeeFee.getQtamount()==null){
						employeeFee.setQtamount(item.getAmount());
					}
					else{
						employeeFee.setQtamount(employeeFee.getQtamount()+item.getAmount());
					}
					total+=item.getAmount();
				}
				if(item.getType().getLabel().equals(PublicConfig.getPage())
						||item.getType().getLabel().equals(PublicConfig.getPoster())
						||item.getType().getLabel().equals("qtxcp")){
					if(employeeFee.getXcdyamount()==null){
						employeeFee.setXcdyamount(item.getAmount());
					}
					else{
						employeeFee.setXcdyamount(employeeFee.getXcdyamount()+item.getAmount());
					}
					total+=item.getAmount();
				}
				if(item.getType().getLabel().equals(PublicConfig.getSample())){
					if(employeeFee.getYpamount()==null){
						employeeFee.setYpamount(item.getAmount());
					}
					else{
						employeeFee.setYpamount(employeeFee.getYpamount()+item.getAmount());
					}
					total+=item.getAmount();
				}
				if(item.getType().getLabel().equals(PublicConfig.getEntertain())){
					if(employeeFee.getZdamount()==null){
						employeeFee.setZdamount(item.getAmount());
					}
					else{
						employeeFee.setZdamount(employeeFee.getZdamount()+item.getAmount());
					}
					total+=item.getAmount();
				}
			}
		}
		employeeFee.setRemarks(exsRbsm.getRemarks());
		employeeFee.setType(exsRbsm.getType());
		employeeFee.setTotal(total);
		exsService.addEmployeeFee(employeeFee);
		return "true";
	}

	@RequestMapping(value = "/aexsforms/denyexs", method = RequestMethod.POST)
	public String denyexs(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String tid = multipartRequest.getParameter("tid");
		String remark = multipartRequest.getParameter("remark");
		ExsRbsm exsRbsm = exsService.getByid(Integer.parseInt(tid));
		exsRbsm.setChecked(3);
		exsRbsm.setDes(remark);
		exsService.editExsRbsm(exsRbsm);
		return "redirect:checkpending";
	}
	
	@RequestMapping(value = "/aexsforms/payexs", method = RequestMethod.POST)
	@ResponseBody
	public String payexs(@RequestParam(required = true) Integer id) {
		ExsRbsm exsRbsm = exsService.getByid(id);
		exsRbsm.setPaystatus(1);
		exsService.editExsRbsm(exsRbsm);
		return "true";
	}
}
