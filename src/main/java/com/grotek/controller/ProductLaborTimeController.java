package com.grotek.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
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

import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerValue;
import com.grotek.pojo.ProductLabor;
import com.grotek.pojo.ProductLabor_Time;
import com.grotek.service.ProductLaborService;
import com.grotek.service.ProductLaborTimeService;
import com.grotek.util.DateTools;

@Controller
@RequestMapping("/manager")
public class ProductLaborTimeController {

	@Autowired
	private ProductLaborTimeService timeService;
	@Autowired
	private ProductLaborService laborService;
	
	@RequestMapping(value = "/labortimes/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductLabor_Time> times = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			times = timeService.findProductLabor_Times(context, new PageRequest(page,size));
			sumcount = timeService.searchProductLabor_TimeCount(context);
		} else {
			times = timeService.getProductLabor_Times(new PageRequest(page, size));
			sumcount = timeService.allProductLabor_TimeCount();
		}
		model.addAttribute("times", times);
		model.addAttribute("sumcount", sumcount);
		return "labortimes/index";
	}
	
	@RequestMapping(value = "/labortimes/new",method=RequestMethod.GET)
	public String newdealerprice(Model model, @RequestParam(required = false, defaultValue = "0") int pid){
		model.addAttribute("path", "");
		if(pid!=0){
			ProductLabor labor = laborService.getById(pid);
			model.addAttribute("labor", labor);
			model.addAttribute("path", "did");
		}
		
		return "labortimes/new";
	}
	
	@RequestMapping(value = "/labortimes/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String lid = multipartRequest.getParameter("lid");
		String workhours = multipartRequest.getParameter("workhours");
		String overhours = multipartRequest.getParameter("overhours");
		String pay = multipartRequest.getParameter("pay");
		String date = multipartRequest.getParameter("date");
		String path = multipartRequest.getParameter("path");
		String bonus = multipartRequest.getParameter("bonus");
		ProductLabor_Time productLabor_Time = new ProductLabor_Time();
		ProductLabor labor = laborService.getById(Integer.parseInt(lid));
		productLabor_Time.setLabor(labor);
		productLabor_Time.setWorkhours(Double.parseDouble(workhours));
		if(StringUtils.isNotBlank(overhours)){
			productLabor_Time.setOverhours(Double.parseDouble(overhours));
		}
		if(StringUtils.isNotBlank(bonus)){
			productLabor_Time.setBonus(Double.parseDouble(bonus));
		}
		productLabor_Time.setPay(Double.parseDouble(pay));
		productLabor_Time.setDate(DateTools.ParseShortString(date));
		timeService.addProductLabor_Time(productLabor_Time);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		if(StringUtils.isBlank(path)){
			return "redirect:index";
		}
		else{
			return "redirect:/manager/productlabors/profile?id="+lid;
		}
		
	}
	
	@RequestMapping(value = "/labortimes/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String id = multipartRequest.getParameter("id");
		String workhours = multipartRequest.getParameter("workhours");
		String overhours = multipartRequest.getParameter("overhours");
		String pay = multipartRequest.getParameter("pay");
		String date = multipartRequest.getParameter("date");
		String path = multipartRequest.getParameter("path");
		String bonus = multipartRequest.getParameter("bonus");
		ProductLabor_Time productLabor_Time = timeService.getProductLabor_TimeById(Integer.parseInt(id));
		productLabor_Time.setWorkhours(Double.parseDouble(workhours));
		if(StringUtils.isNotBlank(overhours)){
			productLabor_Time.setOverhours(Double.parseDouble(overhours));
		}
		if(StringUtils.isNotBlank(bonus)){
			productLabor_Time.setBonus(Double.parseDouble(bonus));
		}
		productLabor_Time.setPay(Double.parseDouble(pay));
		productLabor_Time.setDate(DateTools.ParseShortString(date));
		timeService.editProductLabor_Time(productLabor_Time);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:index";
		
	}
	
	@RequestMapping(value = "/labortimes/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		timeService.deleteProductLabor_Time(id);
		return "true";
	}
}
