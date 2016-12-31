package com.grotek.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grotek.pojo.Employee;
import com.grotek.service.EmployeeService;
import com.grotek.service.UserContext;

@Controller
@RequestMapping("/work")
public class AccountController {
	
	@Autowired
	private UserContext userContext;
	
	@Autowired
	private EmployeeService emService;
	
	@RequestMapping(value = "/account/pass",method=RequestMethod.GET)
	public String index(Model model){
		Employee employee = userContext.getCurrentUser();
		employee = emService.getById(employee.getId());
		model.addAttribute("pass", employee.getPassword());
		return "/account/pass";
	}
	
	@RequestMapping(value = "/account/change", method = RequestMethod.POST)
	@ResponseBody
	public String change(@RequestParam(required = true) String old,
			@RequestParam(required = true) String pass) {
		Employee employee = userContext.getCurrentUser();
		employee = emService.getById(employee.getId());
		if(!employee.getPassword().equals(old)){
			return "false";
		}
		employee.setPassword(pass);
		emService.editEmployee(employee);
		return "true";
	}
}
