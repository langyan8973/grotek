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

import com.grotek.pojo.DealerCreditDic;
import com.grotek.pojo.ShopTypeDic;
import com.grotek.service.DealerCreditService;
import com.grotek.service.DicsService;

@Controller
@RequestMapping("/manager")
public class DealerCreditController {

	@Autowired
	private DealerCreditService creditService;
	
	@Autowired
	private DicsService dicService;
	
	@RequestMapping(value = "/dealercredits/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<DealerCreditDic> credits = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			credits = creditService.findDealerCreditDics(context, new PageRequest(page,size));
			sumcount = creditService.searchCount(context);
		} else {
			credits = creditService.getDealerCreditDics(new PageRequest(page, size));
			sumcount = creditService.allCount();
		}
		List<ShopTypeDic> types = dicService.getShopTypeDics();
		model.addAttribute("credits", credits);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("types", types);
		return "dealercredits/index";
	}
	
	@RequestMapping(value = "/dealercredits/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String type = multipartRequest.getParameter("type");
		String objective = multipartRequest.getParameter("objective");
		String rebate = multipartRequest.getParameter("rebate");
		String credit = multipartRequest.getParameter("credit");
		String description = multipartRequest.getParameter("description");
		DealerCreditDic dealerCreditDic = new DealerCreditDic();
		ShopTypeDic shopTypeDic = new ShopTypeDic();
		shopTypeDic.setId(Integer.parseInt(type));
		dealerCreditDic.setType(shopTypeDic);
		dealerCreditDic.setObjective(objective);
		dealerCreditDic.setRebate(Double.parseDouble(rebate));
		dealerCreditDic.setCredit(Double.parseDouble(credit));
		dealerCreditDic.setDescription(description);
		dealerCreditDic.setDate(new Date());
		creditService.addDealerCreditDic(dealerCreditDic);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:index";
		
	}
	
	@RequestMapping(value = "/dealercredits/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String eid = multipartRequest.getParameter("eid");
		String objective = multipartRequest.getParameter("eobjective");
		String rebate = multipartRequest.getParameter("erebate");
		String credit = multipartRequest.getParameter("ecredit");
		String description = multipartRequest.getParameter("edescription");
		DealerCreditDic dealerCreditDic = creditService.getById(Integer.parseInt(eid));
		dealerCreditDic.setObjective(objective);
		dealerCreditDic.setRebate(Double.parseDouble(rebate));
		dealerCreditDic.setCredit(Double.parseDouble(credit));
		dealerCreditDic.setDescription(description);
		dealerCreditDic.setDate(new Date());
		creditService.editDealerCreditDic(dealerCreditDic);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:index";
		
	}
	
	@RequestMapping(value = "/dealercredits/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		creditService.deleteDealerCreditDic(id);
		return "true";
	}
}
