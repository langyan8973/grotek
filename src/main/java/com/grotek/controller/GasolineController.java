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

import com.grotek.pojo.Dealer;
import com.grotek.pojo.UsedCar;
import com.grotek.service.UsedCarService;

@Controller
@RequestMapping("/manager")
public class GasolineController {
	@Autowired
	private UsedCarService service;
	@RequestMapping(value = "/gasolines/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<UsedCar> costs = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			costs = service.findByFullname(context, new PageRequest(page,size));
			sumcount = service.searchCount(context);
		} else {
			costs = service.getUsedCars(new PageRequest(page, size));
			sumcount = service.allCount();
		}
		model.addAttribute("costs", costs);
		model.addAttribute("sumcount", sumcount);
		return "gasolines/index";
	}

}
