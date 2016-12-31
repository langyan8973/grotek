package com.grotek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WLoginController {

	@RequestMapping("/wlogin/form")
	public String aLogin(){
		return "work/login";
	}
	
	@RequestMapping("/work/403")
	public String error() {
		return "work/403";
	}
	
}
