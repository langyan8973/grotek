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

import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerSale;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductBox_In;
import com.grotek.pojo.ProductBox_Price;
import com.grotek.pojo.ProductBox_Store;
import com.grotek.pojo.ProductBox_out;
import com.grotek.service.DealerSaleService;

@Controller
@RequestMapping("/manager")
public class DealerSaleController {

	@Autowired
	private DealerSaleService saleService;
	
	@RequestMapping(value = "/dealersales/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<DealerSale> sales = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			sales = saleService.findDealerSales(context, new PageRequest(page,size));
			sumcount = saleService.searchCount(context);
		} else {
			sales = saleService.getDealerSales(new PageRequest(page, size));
			sumcount = saleService.allCount();
		}
		model.addAttribute("sales", sales);
		model.addAttribute("sumcount", sumcount);
		return "dealersales/index";
	}
	
	@RequestMapping(value = "/dealersales/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = false) String id,
			@RequestParam(required = false) String outid){
		DealerSale sale;
		if (StringUtils.isNotBlank(id)) {
			sale = saleService.getById(Integer.parseInt(id));
			model.addAttribute("sale", sale);
		} else if(StringUtils.isNotBlank(outid)) {
			sale = saleService.getByOutId(Integer.parseInt(outid));
			model.addAttribute("sale", sale);
		}		
		
		return "dealersales/profile";
	}
	
	@RequestMapping(value = "/dealersales/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String eid = multipartRequest.getParameter("eid");
		String paystatus = multipartRequest.getParameter("paystatus");
		String amountpaid = multipartRequest.getParameter("amountpaid");
		
		int id = Integer.parseInt(eid);
		DealerSale sale= saleService.getById(id);
		sale.setPaystatus(paystatus);
		sale.setAmountpaid(Double.parseDouble(amountpaid));
		saleService.editDealerSale(sale);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		
		return "redirect:profile?id="+eid;
	}
	
}
