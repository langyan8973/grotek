package com.grotek.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.grotek.pojo.EmployeeProm;
import com.grotek.pojo.EmployeeReward;
import com.grotek.service.PromoteService;

@Controller
@RequestMapping("/manager")
public class PromoteController {
	
	@Autowired
	private PromoteService promoteService;
	
	@RequestMapping(value = "/promote/index",method=RequestMethod.GET)
	public String index(Model model,@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<EmployeeProm> proms = null;
		int sumcount = 0;
		proms = promoteService.getPromotes(new PageRequest(page, size));
		sumcount = promoteService.allCount();
		model.addAttribute("proms", proms);
		model.addAttribute("sumcount", sumcount);
		return "promote/index";
	}

}
