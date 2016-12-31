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
import com.grotek.service.DepartmentService;

@Controller
@RequestMapping("/manager")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@RequestMapping(value="/departments/index",method=RequestMethod.GET)
	public String index(Model model){
		List<DepartmentDic> departmentDics = departmentService.getAllDepartments();
		model.addAttribute("departments", departmentDics);
		return "departments/index";
	}
	
	@RequestMapping(value = "/departments/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		if(departmentService.check(name, code)>0){
			redirectAttributes.addFlashAttribute("message", "名称或编码重复");
		}
		else {
			departmentService.createDept(name, code);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/departments/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String code = multipartRequest.getParameter("ecode");
		String eid = multipartRequest.getParameter("eid");
		int id = Integer.parseInt(eid);
		DepartmentDic departmentDic = departmentService.getByid(id);
		if(departmentDic.getCode().equals(code) && departmentDic.getName().equals(name)){
			
		}
		else if(!departmentDic.getCode().equals(code) && departmentDic.getName().equals(name)){
			if(departmentService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				departmentDic.setCode(code);
				departmentService.editDept(departmentDic);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else if(departmentDic.getCode().equals(code) && !departmentDic.getName().equals(name)){
			if(departmentService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				departmentDic.setName(name);
				departmentService.editDept(departmentDic);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else{
			if(departmentService.check(name, code)>0){
				redirectAttributes.addFlashAttribute("message", "名称或密码重复");
			}
			else {
				departmentDic.setCode(code);
				departmentDic.setName(name);
				departmentService.editDept(departmentDic);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:index";
	}
	
	@RequestMapping(value = "/departments/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		departmentService.deleteDept(id);
		return "true";
	}
}
