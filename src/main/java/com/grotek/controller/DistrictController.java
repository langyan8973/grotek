package com.grotek.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.dto.DistrictDto;
import com.grotek.pojo.District;
import com.grotek.pojo.ProductRaw;
import com.grotek.service.DistrictService;

@Controller
@RequestMapping("/manager")
public class DistrictController {

	@Autowired
	private DistrictService districtService;
	
	@RequestMapping(value = "/districts/index",method=RequestMethod.GET)
	public String index(Model model){
		List<DistrictDto> districts = districtService.getDistrictTree();
		model.addAttribute("districts", districts);
		return "districts/index";
	}
	
	@RequestMapping(value = "/districts/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String pid = multipartRequest.getParameter("parentid");
		String area = multipartRequest.getParameter("area");
		District district = new District();
		district.setName(area);
		if(StringUtils.isNotBlank(pid)){
			district.setParentid(Integer.parseInt(pid));
		}
		districtService.createDistrict(district);
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/districts/delete", method = RequestMethod.POST)
	@ResponseBody
	public boolean delete(@RequestParam(required = true)String id) {
		
		try {
			districtService.deleteByid(Integer.parseInt(id));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	@RequestMapping(value = "/districts/select",method=RequestMethod.GET)
	public String select(Model model,@RequestParam(required = false, defaultValue = "0") int level){
		List<DistrictDto> districts = districtService.getDistrictTree();
		model.addAttribute("districts", districts);
		
		return "districts/select";
	}
	
}
