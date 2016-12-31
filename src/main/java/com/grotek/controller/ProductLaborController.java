package com.grotek.controller;

import java.io.IOException;
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

import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductLabor;
import com.grotek.pojo.ProductLabor_Time;
import com.grotek.service.ProductLaborService;
import com.grotek.service.ProductLaborTimeService;
import com.grotek.util.DateTools;

@Controller
@RequestMapping("/manager")
public class ProductLaborController {

	@Autowired
	private ProductLaborService laborService;
	@Autowired
	private ProductLaborTimeService timeService;
	
	@RequestMapping(value = "/productlabors/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductLabor> labors = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			labors = laborService.findProductLabors(context, new PageRequest(page,size));
			sumcount = laborService.searchCount(context);
		} else {
			labors = laborService.getProductLabors(new PageRequest(page, size));
			sumcount = laborService.allCount();
		}
		model.addAttribute("labors", labors);
		model.addAttribute("sumcount", sumcount);
		return "productlabors/index";
	}
	
	@RequestMapping(value = "/productlabors/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String sex = multipartRequest.getParameter("sex");
		String age = multipartRequest.getParameter("age");
		String phone = multipartRequest.getParameter("phone");
		String nativeplace = multipartRequest.getParameter("nativeplace");
		String address = multipartRequest.getParameter("address");
		String starttime = multipartRequest.getParameter("starttime");
		String hourprice = multipartRequest.getParameter("hourprice");
		String remarks = multipartRequest.getParameter("remarks");
		if(laborService.check(code)>0){
			redirectAttributes.addFlashAttribute("message", "编码重复");
		}
		else {
			ProductLabor productLabor = new ProductLabor();
			productLabor.setName(name);
			productLabor.setCode(code);
			productLabor.setSex(sex);
			productLabor.setAge(Integer.parseInt(age));
			productLabor.setPhone(phone);
			productLabor.setNativeplace(nativeplace);
			productLabor.setAddress(address);
			productLabor.setStarttime(DateTools.ParseString(starttime));
			productLabor.setHourprice(Double.parseDouble(hourprice));
			productLabor.setRemarks(remarks);
			laborService.addProductLabor(productLabor);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/productlabors/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true) String id){
		ProductLabor labor = laborService.getById(Integer.parseInt(id));
		List<ProductLabor_Time> times = timeService.getProductLabor_TimeByPid(Integer.parseInt(id), new PageRequest(0, 10));
		model.addAttribute("labor", labor);
		model.addAttribute("times", times);
		return "productlabors/profile";
	}
	
	@RequestMapping(value = "/productlabors/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String sex = multipartRequest.getParameter("sex");
		String age = multipartRequest.getParameter("age");
		String phone = multipartRequest.getParameter("phone");
		String nativeplace = multipartRequest.getParameter("nativeplace");
		String address = multipartRequest.getParameter("address");
		String starttime = multipartRequest.getParameter("starttime");
		String hourprice = multipartRequest.getParameter("hourprice");
		String remarks = multipartRequest.getParameter("remarks");
		String id = multipartRequest.getParameter("id");
		ProductLabor productLabor = laborService.getById(Integer.parseInt(id));
		if(!productLabor.getCode().equals(code))
		{
			if(laborService.check(code)>0){
				redirectAttributes.addFlashAttribute("message", "编码重复");
			}
			else{
				productLabor.setName(name);
				productLabor.setCode(code);
				productLabor.setSex(sex);
				productLabor.setAge(Integer.parseInt(age));
				productLabor.setPhone(phone);
				productLabor.setNativeplace(nativeplace);
				productLabor.setAddress(address);
				productLabor.setStarttime(DateTools.ParseString(starttime));
				productLabor.setHourprice(Double.parseDouble(hourprice));
				productLabor.setRemarks(remarks);
				laborService.editProductLabor(productLabor);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else{
			productLabor.setName(name);
			productLabor.setCode(code);
			productLabor.setSex(sex);
			productLabor.setAge(Integer.parseInt(age));
			productLabor.setPhone(phone);
			productLabor.setNativeplace(nativeplace);
			productLabor.setAddress(address);
			productLabor.setStarttime(DateTools.ParseString(starttime));
			productLabor.setHourprice(Double.parseDouble(hourprice));
			productLabor.setRemarks(remarks);
			laborService.editProductLabor(productLabor);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		
		return "redirect:profile?id="+id;
	}
	
	@RequestMapping(value = "/productlabors/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		laborService.deleteOne(id);
		return "true";
	}
	
	@RequestMapping(value = "/productlabors/select",method=RequestMethod.GET)
	public String selectraws(Model model){
		List<ProductLabor> labors = laborService.getProductLabors(new PageRequest(0, 10000));
		
		model.addAttribute("labors", labors);
		return "productlabors/select";
	}
}
