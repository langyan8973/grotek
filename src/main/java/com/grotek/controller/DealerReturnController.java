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
import com.grotek.pojo.DealerReturnDic;
import com.grotek.pojo.ShopTypeDic;
import com.grotek.service.DealerReturnService;

@Controller
@RequestMapping("/manager")
public class DealerReturnController {

	@Autowired
	private DealerReturnService returnService;
	
	@RequestMapping(value = "/dealerreturns/index",method=RequestMethod.GET)
	public String index(Model model,@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<DealerReturnDic> dealerReturnDics = null;
		int sumcount = 0;
		dealerReturnDics = returnService.getDealerReturnDics(new PageRequest(page, size));
		sumcount = returnService.allCount();
		model.addAttribute("dics", dealerReturnDics);
		model.addAttribute("sumcount", sumcount);
		return "dealerreturns/index";
	}
	@RequestMapping(value = "/dealerreturns/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String purchases = multipartRequest.getParameter("purchases");
		String rebate = multipartRequest.getParameter("rebate");
		DealerReturnDic dealerReturnDic = new DealerReturnDic();
		dealerReturnDic.setPurchases(Double.parseDouble(purchases));
		dealerReturnDic.setRebate(Double.parseDouble(rebate));
		dealerReturnDic.setDate(new Date());
		returnService.addDealerReturnDic(dealerReturnDic);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:index";
		
	}
	
	@RequestMapping(value = "/dealerreturns/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String eid = multipartRequest.getParameter("eid");
		String purchases = multipartRequest.getParameter("epurchases");
		String rebate = multipartRequest.getParameter("erebate");
		DealerReturnDic dealerReturnDic = returnService.getById(Integer.parseInt(eid));
		dealerReturnDic.setPurchases(Double.parseDouble(purchases));
		dealerReturnDic.setRebate(Double.parseDouble(rebate));
		dealerReturnDic.setDate(new Date());
		returnService.editDealerReturnDic(dealerReturnDic);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:index";
		
	}
	
	@RequestMapping(value = "/dealerreturns/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		returnService.deleteDealerReturnDic(id);
		return "true";
	}
}
