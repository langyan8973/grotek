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

import com.grotek.pojo.Admin;
import com.grotek.pojo.DepartmentDic;
import com.grotek.pojo.Employee;
import com.grotek.pojo.Role;
import com.grotek.service.AdminContext;
import com.grotek.service.AdminService;
import com.grotek.service.UserContext;

@Controller
@RequestMapping("/manager")
public class AdminController {
	
	@Autowired
	private AdminService servece;
	
	@Autowired
	private AdminContext adminContext;
	
	@RequestMapping(value="/admin/index",method=RequestMethod.GET)
	public String index(Model model){
		List<Admin> admins = servece.getAll();
		List<Role> roles = servece.getRoles();
		model.addAttribute("admins", admins);
		model.addAttribute("roles", roles);
		return "admin/index";
	}

	@RequestMapping(value = "/admin/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String role = multipartRequest.getParameter("role");
		if(servece.getByName(name)!=null){
			redirectAttributes.addFlashAttribute("message", "用户名重复");
		}
		else {
			Admin admin = new Admin();
			admin.setName(name);
			admin.setPassword("grotek2016");
			Role role2 = new Role();
			role2.setId(Integer.parseInt(role));
			admin.setRole(role2);
			servece.createAdmin(admin);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/admin/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String erole = multipartRequest.getParameter("erole");
		String eid = multipartRequest.getParameter("eid");
		int id = Integer.parseInt(eid);
		Admin admin = servece.getById(id);
		Role role = new Role();
		role.setId(Integer.parseInt(erole));
		admin.setRole(role);
		servece.editAdmin(admin);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:index";
	}
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		servece.deleteAdmin(id);
		return "true";
	}
	
	@RequestMapping(value = "/admin/pass",method=RequestMethod.GET)
	public String admin(Model model){
		
		return "/admin/pass";
	}
	
	@RequestMapping(value = "/admin/change", method = RequestMethod.POST)
	@ResponseBody
	public String change(@RequestParam(required = true) String old,
			@RequestParam(required = true) String pass) {
		Admin admin = adminContext.getCurrentUser();
		admin = servece.getById(admin.getId());
		if(!admin.getPassword().equals(old)){
			return "false";
		}
		admin.setPassword(pass);
		servece.editAdmin(admin);
		return "true";
	}
}
