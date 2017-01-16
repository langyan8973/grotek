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
import com.grotek.pojo.GrotekUnit;
import com.grotek.pojo.ProductRaw;
import com.grotek.pojo.ProductRaw_In;
import com.grotek.pojo.ProductRaw_Out;
import com.grotek.pojo.ProductRaw_Price;
import com.grotek.pojo.ProductRaw_Store;
import com.grotek.service.GrotekUnitService;
import com.grotek.service.ProductRawService;
import com.grotek.util.DateTools;

@Controller
@RequestMapping("/manager")
public class ProductRawController {

	@Autowired
	private ProductRawService productRawService;
	@Autowired
	private GrotekUnitService unitService;
	
	
	@RequestMapping(value = "/productraws/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductRaw> productraws = null;
		List<GrotekUnit> units = unitService.getAll();
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productraws = productRawService.findProductRaws(context, new PageRequest(page,size));
			sumcount = productRawService.searchCount(context);
			System.out.println(context);
		} else {
			productraws = productRawService.getProductRaws(new PageRequest(page, size));
			sumcount = productRawService.allCount();
		}
		model.addAttribute("productraws", productraws);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("units", units);
		return "productraws/index";
	}
	
	@RequestMapping(value = "/productraws/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String uid = multipartRequest.getParameter("unit");
		String description = multipartRequest.getParameter("description");
		if(productRawService.check(name, code)>0){
			redirectAttributes.addFlashAttribute("message", "名称或编码重复");
		}
		else {
			ProductRaw productRaw = new ProductRaw();
			productRaw.setName(name);
			productRaw.setCode(code);
			int unitid = Integer.parseInt(uid);
			GrotekUnit unit = new GrotekUnit();
			unit.setId(unitid);
			productRaw.setUnit(unit);
			productRaw.setDescription(description);
			productRawService.addProductRaw(productRaw);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/productraws/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true) String id){
		ProductRaw productRaw = productRawService.getById(Integer.parseInt(id));
		ProductRaw_Price price = productRawService.getPriceByPid(Integer.parseInt(id));
		ProductRaw_Store store = productRawService.getStoreByPid(Integer.parseInt(id));
		List<ProductRaw_In> ins = productRawService.getInByPid(Integer.parseInt(id));
		List<ProductRaw_Out> outs = productRawService.getOutByPid(Integer.parseInt(id));
		List<GrotekUnit> units = unitService.getAll();
		model.addAttribute("productraw", productRaw);
		model.addAttribute("price", price);
		model.addAttribute("store", store);
		model.addAttribute("ins", ins);
		model.addAttribute("outs", outs);
		model.addAttribute("units", units);
		return "productraws/profile";
	}
	
	@RequestMapping(value = "/productraws/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String eid = multipartRequest.getParameter("id");
		String uid = multipartRequest.getParameter("unit");
		String description = multipartRequest.getParameter("description");
		int id = Integer.parseInt(eid);
		int unitid = Integer.parseInt(uid);
		GrotekUnit unit = new GrotekUnit();
		unit.setId(unitid);
		
		ProductRaw productRaw = productRawService.getById(id);
		if(productRaw.getCode().equals(code) && productRaw.getName().equals(name)){
			productRaw.setDescription(description);
			productRaw.setUnit(unit);
			productRawService.editProductRaw(productRaw);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		else if(!productRaw.getCode().equals(code) && productRaw.getName().equals(name)){
			if(productRawService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				productRaw.setCode(code);
				productRaw.setUnit(unit);
				productRaw.setDescription(description);
				productRawService.editProductRaw(productRaw);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else if(productRaw.getCode().equals(code) && !productRaw.getName().equals(name)){
			if(productRawService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				productRaw.setName(name);
				productRaw.setUnit(unit);
				productRaw.setDescription(description);
				productRawService.editProductRaw(productRaw);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else{
			if(productRawService.check(name, code)>0){
				redirectAttributes.addFlashAttribute("message", "名称或密码重复");
			}
			else {
				productRaw.setCode(code);
				productRaw.setName(name);
				productRaw.setUnit(unit);
				productRaw.setDescription(description);
				productRawService.editProductRaw(productRaw);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:profile?id="+id;
	}
	
	@RequestMapping(value = "/productraws/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		productRawService.deleteOne(id);
		return "true";
	}
	
	
	@RequestMapping(value = "/productraws/price",method=RequestMethod.GET)
	public String prices(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductRaw_Price> productRaw_Prices = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productRaw_Prices = productRawService.findprices(context, new PageRequest(page,size));
			sumcount = productRawService.searchPriceCount(context);
		} else {
			productRaw_Prices = productRawService.getPrices(new PageRequest(page, size));
			sumcount = productRawService.priceallcount();
		}
		model.addAttribute("prices", productRaw_Prices);
		model.addAttribute("sumcount", sumcount);
		return "productraws/price";
	}
	
	@RequestMapping(value = "/productraws/editprice", method = RequestMethod.POST)
	public String editprice(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String pricestr = multipartRequest.getParameter("price");
		String proid = multipartRequest.getParameter("id");
		String priceid= multipartRequest.getParameter("priceid");
		int productrawid = Integer.parseInt(proid);
		double price = Double.parseDouble(pricestr);
		if(StringUtils.isNotBlank(priceid)){
			productRawService.deletePrice(Integer.parseInt(priceid));
		}
		ProductRaw_Price productRaw_Price = new ProductRaw_Price();
		productRaw_Price.setPrice(price);
		ProductRaw productRaw = productRawService.getById(productrawid);
		productRaw_Price.setProductRaw(productRaw);
		productRaw_Price.setDate(new Date());
		productRawService.createPrice(productRaw_Price);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+productrawid;
	}
	
	@RequestMapping(value = "/productraws/editstore", method = RequestMethod.POST)
	public String editstore(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String storecount = multipartRequest.getParameter("storecount");
		String proid = multipartRequest.getParameter("id");
		int productrawid = Integer.parseInt(proid);
		double count = Double.parseDouble(storecount);
		ProductRaw_Store store = productRawService.getStoreByPid(productrawid);
		if(store==null){
			store = new ProductRaw_Store();
			store.setCount(count);
			ProductRaw raw = new ProductRaw();
			raw.setId(productrawid);
			store.setProductRaw(raw);
			store.setStatus(0);
			
		}
		else{
			store.setCount(count);
		}
		productRawService.changeStore(store);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+productrawid;
	}
	
	@RequestMapping(value = "/productraws/store",method=RequestMethod.GET)
	public String stores(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductRaw_Store> stores = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			stores = productRawService.findProductKcun(context, new PageRequest(page,size));
			sumcount = productRawService.searchkuncCount(context);
 		} else {
		stores = productRawService.getStores(new PageRequest(page, size));
		sumcount = productRawService.storesAllCount();
		}
		model.addAttribute("stores", stores);
		model.addAttribute("sumcount", sumcount);
		return "productraws/store";
	}
	
	
	@RequestMapping(value = "/productraws/in",method=RequestMethod.GET)
	public String productrawin(Model model,@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductRaw_In> productRaw_Ins = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productRaw_Ins = productRawService.findIns(context, new PageRequest(page,size));
			sumcount = productRawService.searchInCount(context);
		} else {
			productRaw_Ins = productRawService.getIns(new PageRequest(page, size));
			sumcount = productRawService.inallcount();
		}
		model.addAttribute("ins", productRaw_Ins);
		model.addAttribute("sumcount", sumcount);
		return "productraws/in";
	}
	
	@RequestMapping(value = "/productraws/newin",method=RequestMethod.GET)
	public String productrawNewin(Model model,@RequestParam(required = true) int pid){
		ProductRaw productRaw = productRawService.getById(pid);
		
		model.addAttribute("productRaw", productRaw);
		return "productraws/newin";
	}
	
	@RequestMapping(value = "/productraws/inaddone", method = RequestMethod.POST)
	public String inaddOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String count = multipartRequest.getParameter("count");
//		String did = multipartRequest.getParameter("did");
		String pid = multipartRequest.getParameter("pid");
		String datestr = multipartRequest.getParameter("date");
		
		ProductRaw_In productRaw_In = new ProductRaw_In();
		productRaw_In.setCount(Double.parseDouble(count));
		ProductRaw productRaw = new ProductRaw();
		productRaw.setId(Integer.parseInt(pid));
		productRaw_In.setProductRaw(productRaw);
//		Dealer dealer = new Dealer();
//		dealer.setId(Integer.parseInt(did));
//		productRaw_In.setDealer(dealer);
		productRaw_In.setDate(DateTools.ParseString(datestr));
		productRawService.createIn(productRaw_In);
		
		ProductRaw_Store productRaw_Store = productRawService.getStoreByPid(productRaw.getId());
		if(productRaw_Store==null){
			productRaw_Store = new ProductRaw_Store();
			productRaw_Store.setProductRaw(productRaw);
			productRaw_Store.setCount(0.0);
		}
		productRaw_Store.setCount(productRaw_Store.getCount()+Double.parseDouble(count));
		productRawService.changeStore(productRaw_Store);
		redirectAttributes.addFlashAttribute("success", "入库成功");
		
		return "redirect:profile?id="+pid;
	}
	
	@RequestMapping(value = "/productraws/out",method=RequestMethod.GET)
	public String productrawout(Model model,@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductRaw_Out> productRaw_Outs = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productRaw_Outs = productRawService.findOuts(context, new PageRequest(page,size));
			sumcount = productRawService.searchOutCount(context);
		} else {
			productRaw_Outs = productRawService.getOuts(new PageRequest(page, size));
			sumcount = productRawService.outallcount();
		}
		model.addAttribute("outs", productRaw_Outs);
		model.addAttribute("sumcount", sumcount);
		return "productraws/out";
	}
	
	@RequestMapping(value = "/productraws/newout",method=RequestMethod.GET)
	public String productrawNewout(Model model,@RequestParam(required = true) int pid){
		ProductRaw productRaw = productRawService.getById(pid);
		
		model.addAttribute("productRaw", productRaw);
		return "productraws/newout";
	}
	
	@RequestMapping(value = "/productraws/outaddone", method = RequestMethod.POST)
	public String outaddOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String count = multipartRequest.getParameter("count");
		String did = multipartRequest.getParameter("did");
		String pid = multipartRequest.getParameter("pid");
		String datestr = multipartRequest.getParameter("date");
		
		ProductRaw_Store productRaw_Store = productRawService.getStoreByPid(Integer.parseInt(pid));
		if(productRaw_Store==null){
			redirectAttributes.addFlashAttribute("message", "库存为0，无法出库");
		}
		else{
			if(productRaw_Store.getCount()<Double.parseDouble(count)){
				redirectAttributes.addFlashAttribute("message", "库存不足，无法出库");
			}
			else{
				ProductRaw_Out productRaw_Out = new ProductRaw_Out();
				productRaw_Out.setCount(Double.parseDouble(count));
				ProductRaw productRaw = new ProductRaw();
				productRaw.setId(Integer.parseInt(pid));
				productRaw_Out.setProductRaw(productRaw);
				Dealer dealer = new Dealer();
				dealer.setId(Integer.parseInt(did));
				productRaw_Out.setDealer(dealer);
				productRaw_Out.setDate(DateTools.ParseString(datestr));
				productRawService.createOut(productRaw_Out);
				
				productRaw_Store.setCount(productRaw_Store.getCount()-Double.parseDouble(count));
				productRawService.changeStore(productRaw_Store);
				redirectAttributes.addFlashAttribute("success", "出库成功");
			}
		}
		
		return "redirect:profile?id="+pid;
	}
	
	@RequestMapping(value = "/productraws/selectraws",method=RequestMethod.GET)
	public String selectraws(Model model){
		List<ProductRaw> raws = productRawService.getProductRaws(new PageRequest(0, 1000));
		
		model.addAttribute("raws", raws);
		return "productraws/selectraws";
	}
}
