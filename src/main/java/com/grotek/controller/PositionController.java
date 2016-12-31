package com.grotek.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grotek.pojo.DepartmentDic;
import com.grotek.pojo.PositionDic;
import com.grotek.service.PositionService;

@Controller
@RequestMapping("/manager")
public class PositionController {
	
	@Autowired
	private PositionService positionService;
	
	@RequestMapping(value="/positions/index",method=RequestMethod.GET)
	public String index(Model model){
		List<PositionDic> positions = positionService.getDicstAllPositions();
		model.addAttribute("positions", positions);
		return "positions/index";
	}
	
	@RequestMapping(value = "/positions/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String objective = multipartRequest.getParameter("objective");
		int salestargets = Integer.parseInt(multipartRequest.getParameter("salestargets"));
		double amortization = Double.parseDouble(multipartRequest.getParameter("amortization"));
		double salary = Double.parseDouble(multipartRequest.getParameter("salary"));
		double travelAllow = Double.parseDouble(multipartRequest.getParameter("travelAllow"));
		double mobileAllow = Double.parseDouble(multipartRequest.getParameter("mobileAllow"));
		double costhour = Double.parseDouble(multipartRequest.getParameter("costhour"));
		String description = multipartRequest.getParameter("description");
		if(positionService.check(name)>0){
			redirectAttributes.addFlashAttribute("message", "名称重复");
		}
		else {
			positionService.createPosition(name,salestargets, objective, amortization, salary, travelAllow, mobileAllow, description,costhour);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/positions/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String objective = multipartRequest.getParameter("eobjective");
		int salestargets = Integer.parseInt(multipartRequest.getParameter("esalestargets"));
		double amortization = Double.parseDouble(multipartRequest.getParameter("eamortization"));
		double salary = Double.parseDouble(multipartRequest.getParameter("esalary"));
		double travelAllow = Double.parseDouble(multipartRequest.getParameter("etravelAllow"));
		double costhour = Double.parseDouble(multipartRequest.getParameter("ecosthour"));
		double mobileAllow = Double.parseDouble(multipartRequest.getParameter("emobileAllow"));
		String description = multipartRequest.getParameter("edescription");
		int id = Integer.parseInt(multipartRequest.getParameter("eid"));
		PositionDic positionDic = positionService.getById(id);
		if(positionDic.getName().equals(name)){
			positionDic.setObjective(objective);
			positionDic.setSalestargets(salestargets);
			positionDic.setAmortization(amortization);
			positionDic.setSalary(salary);
			positionDic.setTravelAllow(travelAllow);
			positionDic.setMobileAllow(mobileAllow);
			positionDic.setDescription(description);
			positionDic.setCosthour(costhour);
			positionService.editPositionDic(positionDic);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		else{
			if(positionService.check(name)>0){
				redirectAttributes.addFlashAttribute("message", "名称重复");
			}
			else {
				positionDic.setName(name);
				positionDic.setObjective(objective);
				positionDic.setSalestargets(salestargets);
				positionDic.setAmortization(amortization);
				positionDic.setSalary(salary);
				positionDic.setTravelAllow(travelAllow);
				positionDic.setMobileAllow(mobileAllow);
				positionDic.setDescription(description);
				positionDic.setCosthour(costhour);
				positionService.editPositionDic(positionDic);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:index";
	}
	
	@RequestMapping(value = "/positions/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		positionService.deletePosition(id);
		return "true";
	}
	
	@RequestMapping(value = "/positions/detail", method = RequestMethod.GET)
	public String detail(@RequestParam(required = true) String id, Model model) {

		PositionDic positionDic = positionService.getById(Integer.parseInt(id));		
		model.addAttribute("position", positionDic);
		return "positions/detail";
	}

}
