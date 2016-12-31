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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerPrice;
import com.grotek.pojo.PackType;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductPack;
import com.grotek.service.DealerPriceService;
import com.grotek.service.DealerService;
import com.grotek.service.ProductBoxService;

@Controller
@RequestMapping("/manager")
public class DealerPriceController {

	@Autowired
	private DealerPriceService priceService;
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private ProductBoxService boxService;
	
	@RequestMapping(value = "/dealerprices/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<DealerPrice> prices = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			prices = priceService.findDealerPrices(context, new PageRequest(page,size));
			sumcount = priceService.searchCount(context);
		} else {
			prices = priceService.getDealerPrices(new PageRequest(page, size));
			sumcount = priceService.allCount();
		}
		model.addAttribute("prices", prices);
		model.addAttribute("sumcount", sumcount);
		return "dealerprices/index";
	}
	
	@RequestMapping(value = "/dealerprices/new",method=RequestMethod.GET)
	public String newdealerprice(Model model, @RequestParam(required = false, defaultValue = "0") int did,
			@RequestParam(required = false, defaultValue = "0") int pid){
		model.addAttribute("path", "");
		if(did!=0){
			Dealer dealer = dealerService.getById(did);
			model.addAttribute("dealer", dealer);
			model.addAttribute("path", "did");
		}
		if(pid!=0){
			ProductBox box = boxService.getById(pid);
			model.addAttribute("box", box);
			model.addAttribute("path", "pid");
		}
		return "dealerprices/new";
	}
	
	@RequestMapping(value = "/dealerprices/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String did = multipartRequest.getParameter("did");
		String bid = multipartRequest.getParameter("bid");
		String price = multipartRequest.getParameter("price");
		String path = multipartRequest.getParameter("path");
		DealerPrice dealerPrice = new DealerPrice();
		Dealer dealer = new Dealer();
		dealer.setId(Integer.parseInt(did));
		ProductBox box = new ProductBox();
		box.setId(Integer.parseInt(bid));
		dealerPrice.setBox(box);
		dealerPrice.setDealer(dealer);
		dealerPrice.setPrice(Double.parseDouble(price));
		dealerPrice.setDate(new Date());
		priceService.createDealerPrice(dealerPrice);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		if(StringUtils.isBlank(path)){
			return "redirect:index";
		}
		else if(path.equals("did")){
			return "redirect:/manager/dealers/profile?id="+did;
		}
		else{
			return "redirect:/manager/productboxes/profile?id="+bid;
		}
	}
	
}
