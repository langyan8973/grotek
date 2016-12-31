package com.grotek.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grotek.pojo.MonthlyIncome;
import com.grotek.service.MonthlyIncomeService;

@Controller
@RequestMapping("/manager")
public class MonthlyIncomeController {

	@Autowired
	private MonthlyIncomeService service;
	
	@RequestMapping(value = "/monthlyincomes/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<MonthlyIncome> incomes = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			incomes = service.find(context, new PageRequest(page,size));
			sumcount = service.searchCount(context);
		} else {
			incomes = service.getAll(new PageRequest(page,size));
			sumcount = service.allCount();
		}
		model.addAttribute("incomes", incomes);
		model.addAttribute("sumcount", sumcount);
		return "monthlyincomes/index";
	}
	
	@RequestMapping(value = "/monthlyincomes/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		int id = Integer.parseInt(multipartRequest.getParameter("eid"));
		String wages = multipartRequest.getParameter("wages");
		String divident = multipartRequest.getParameter("divident");
		String allowance = multipartRequest.getParameter("allowance");
		String travel = multipartRequest.getParameter("travel");
		String award = multipartRequest.getParameter("award");
		String costhour = multipartRequest.getParameter("costhour");
		MonthlyIncome income = service.getById(id);
		if(StringUtils.isNotBlank(wages)){
			income.setWages(Double.parseDouble(wages));
		}
		if(StringUtils.isNotBlank(divident)){
			income.setDivident(Double.parseDouble(divident));
		}
		if(StringUtils.isNotBlank(allowance)){
			income.setAllowance(Double.parseDouble(allowance));
		}
		if(StringUtils.isNotBlank(travel)){
			income.setTravel(Double.parseDouble(travel));
		}
		if(StringUtils.isNotBlank(award)){
			income.setAward(Double.parseDouble(award));
		}
		if(StringUtils.isNotBlank(costhour)){
			income.setCosthour(Double.parseDouble(costhour));
		}
		service.updateOne(income);
		return "redirect:index";
	}
}
