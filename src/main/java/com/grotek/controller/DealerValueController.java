package com.grotek.controller;

import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerPrice;
import com.grotek.pojo.DealerSale;
import com.grotek.pojo.DealerValue;
import com.grotek.pojo.ProductBox;
import com.grotek.service.DealerService;
import com.grotek.service.DealerValueService;

@Controller
@RequestMapping("/manager")
public class DealerValueController {
	
	@Autowired
	private DealerValueService valueService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/dealervalues/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<DealerValue> values = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			values = valueService.findDealerValues(context, new PageRequest(page,size));
			sumcount = valueService.searchCount(context);
		} else {
			values = valueService.getDealerValues(new PageRequest(page, size));
			sumcount = valueService.allCount();
		}
		model.addAttribute("values", values);
		model.addAttribute("sumcount", sumcount);
		return "dealervalues/index";
	}
	@RequestMapping(value = "/dealervalues/new",method=RequestMethod.GET)
	public String newdealerprice(Model model, @RequestParam(required = false, defaultValue = "0") int did){
		model.addAttribute("path", "");
		if(did!=0){
			Dealer dealer = dealerService.getById(did);
			model.addAttribute("dealer", dealer);
			model.addAttribute("path", "did");
		}
		
		return "dealervalues/new";
	}
	
	@RequestMapping(value = "/dealervalues/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String did = multipartRequest.getParameter("did");
		String promote = multipartRequest.getParameter("promote");
		String rebate = multipartRequest.getParameter("rebate");
		String reward = multipartRequest.getParameter("reward");
		String description = multipartRequest.getParameter("description");
		String path = multipartRequest.getParameter("path");
		DealerValue dealerValue = new DealerValue();
		Dealer dealer = new Dealer();
		dealer.setId(Integer.parseInt(did));
		dealerValue.setDealer(dealer);
		dealerValue.setPromote(Double.parseDouble(promote));
		dealerValue.setRebate(Double.parseDouble(rebate));
		dealerValue.setReward(Double.parseDouble(reward));
		dealerValue.setDescription(description);
		dealerValue.setDate(new Date());
		valueService.addDealerValue(dealerValue);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		if(StringUtils.isBlank(path)){
			return "redirect:index";
		}
		else{
			return "redirect:/manager/dealers/profile?id="+did;
		}
		
	}
	
	@RequestMapping(value = "/dealervalues/edit",method=RequestMethod.GET)
	public String edit(Model model, @RequestParam(required = true, defaultValue = "0") int id){
		DealerValue dealerValue = valueService.getById(id);
		model.addAttribute("dvalue", dealerValue);
		return "dealervalues/edit";
	}
	
	@RequestMapping(value = "/dealervalues/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String did = multipartRequest.getParameter("did");
		String promote = multipartRequest.getParameter("promote");
		String rebate = multipartRequest.getParameter("rebate");
		String reward = multipartRequest.getParameter("reward");
		String description = multipartRequest.getParameter("description");
		String id = multipartRequest.getParameter("id");
		DealerValue dealerValue = valueService.getById(Integer.parseInt(id));
		Dealer dealer = new Dealer();
		dealer.setId(Integer.parseInt(did));
		dealerValue.setDealer(dealer);
		dealerValue.setPromote(Double.parseDouble(promote));
		dealerValue.setRebate(Double.parseDouble(rebate));
		dealerValue.setReward(Double.parseDouble(reward));
		dealerValue.setDescription(description);
		dealerValue.setDate(new Date());
		valueService.editDealerValue(dealerValue);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:index";
	}
	
	@RequestMapping(value = "/dealervalues/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		valueService.deleteDealerValue(id);
		return "true";
	}
}
