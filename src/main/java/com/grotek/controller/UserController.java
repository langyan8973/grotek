package com.grotek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.grotek.pojo.User;
import com.grotek.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index(@RequestParam(required = true) String id, Model model){
		User user = this.userService.getUserById(Integer.parseInt(id));  
        model.addAttribute("user", user);  
		return "user/index";
	}
	
}
