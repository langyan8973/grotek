package com.grotek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login/form")
	public String aLogin(){
		return "login";
	}
	
	@RequestMapping("/403")
	public String error() {
		return "403";
	}
	
}
