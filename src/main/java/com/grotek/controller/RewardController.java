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

import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeReward;
import com.grotek.service.RewardService;
import com.grotek.util.PublicConfig;

@Controller
@RequestMapping("/manager")
public class RewardController {
	
	@Autowired
	private RewardService rewardService;
	
	@RequestMapping(value = "/rewards/index",method=RequestMethod.GET)
	public String index(Model model,@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<EmployeeReward> rewards = null;
		int sumcount = 0;
		rewards = rewardService.getRewards(new PageRequest(page, size));
		sumcount = rewardService.allCount();
		model.addAttribute("rewards", rewards);
		model.addAttribute("sumcount", sumcount);
		return "rewards/index";
	}

}
