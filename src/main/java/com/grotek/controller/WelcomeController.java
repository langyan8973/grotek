package com.grotek.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.grotek.pojo.GrotekUnit;
import com.grotek.pojo.ManureType;
import com.grotek.pojo.ProductBox;

@Controller
@RequestMapping("/manager")
public class WelcomeController {

	@RequestMapping(value = "/welcome",method=RequestMethod.GET)
	public String index(Model model){
		
		return "welcome/index";
	}
	
}
