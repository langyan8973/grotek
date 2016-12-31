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

import com.grotek.dto.PageNote;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.PageType;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductPage;
import com.grotek.pojo.ProductPage_In;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductPage_Store;
import com.grotek.pojo.ProductRaw;
import com.grotek.service.ProductPageService;
import com.grotek.util.DateTools;


@Controller
@RequestMapping("/manager")
public class ProductPageController {
	
	@Autowired
	private ProductPageService pageService;
	
	@RequestMapping(value = "/productpages/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductPage> productPages = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productPages = pageService.findProductPages(context, new PageRequest(page,size));
			sumcount = pageService.searchCount(context);
		} else {
			productPages = pageService.getProductPages(new PageRequest(page, size));
			sumcount = pageService.allCount();
		}
		List<PageType> types = pageService.getTypes();
		model.addAttribute("productpages", productPages);
		model.addAttribute("types", types);
		model.addAttribute("sumcount", sumcount);
		return "productpages/index";
	}
	
	@RequestMapping(value = "/productpages/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String type = multipartRequest.getParameter("type");
		String price = multipartRequest.getParameter("price");
		if(pageService.check(name, code)>0){
			redirectAttributes.addFlashAttribute("message", "名称或编码重复");
		}
		else {
			ProductPage productPage = new ProductPage();
			productPage.setName(name);
			productPage.setCode(code);
			PageType pageType = pageService.getTypeById(Integer.parseInt(type));
			productPage.setType(pageType);
			productPage.setPrice(Double.parseDouble(price));
			productPage.setDate(new Date());
			pageService.addProductPage(productPage);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/productpages/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true) String id){
		ProductPage productPage= pageService.getById(Integer.parseInt(id));
		List<PageType> types = pageService.getTypes();
		ProductPage_Store store = pageService.getStoreByPid(Integer.parseInt(id));
		List<ProductPage_In> ins = pageService.getInByPid(Integer.parseInt(id));
		List<ProductPage_Out> outs = pageService.getOutByPid(Integer.parseInt(id));
		model.addAttribute("page", productPage);
		model.addAttribute("types", types);
		model.addAttribute("store", store);
		model.addAttribute("ins", ins);
		model.addAttribute("outs", outs);
		return "productpages/profile";
	}
	
	@RequestMapping(value = "/productpages/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String eid = multipartRequest.getParameter("id");
		String type = multipartRequest.getParameter("type");
		String price = multipartRequest.getParameter("price");
		int id = Integer.parseInt(eid);
		ProductPage productPage = pageService.getById(id);
		if(productPage.getCode().equals(code) && productPage.getName().equals(name)){
			PageType pageType = pageService.getTypeById(Integer.parseInt(type));
			productPage.setType(pageType);
			productPage.setPrice(Double.parseDouble(price));
			productPage.setDate(new Date());
			pageService.editProductPage(productPage);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		else if(!productPage.getCode().equals(code) && productPage.getName().equals(name)){
			if(pageService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				productPage.setCode(code);
				PageType pageType = pageService.getTypeById(Integer.parseInt(type));
				productPage.setType(pageType);
				productPage.setPrice(Double.parseDouble(price));
				productPage.setDate(new Date());
				pageService.editProductPage(productPage);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else if(productPage.getCode().equals(code) && !productPage.getName().equals(name)){
			if(pageService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				productPage.setName(name);
				PageType pageType = pageService.getTypeById(Integer.parseInt(type));
				productPage.setType(pageType);
				productPage.setPrice(Double.parseDouble(price));
				productPage.setDate(new Date());
				pageService.editProductPage(productPage);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else{
			if(pageService.check(name, code)>0){
				redirectAttributes.addFlashAttribute("message", "名称或密码重复");
			}
			else {
				productPage.setCode(code);
				productPage.setName(name);
				PageType pageType = pageService.getTypeById(Integer.parseInt(type));
				productPage.setType(pageType);
				productPage.setPrice(Double.parseDouble(price));
				productPage.setDate(new Date());
				pageService.editProductPage(productPage);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:profile?id="+id;
	}
	
	@RequestMapping(value = "/productpages/editstore", method = RequestMethod.POST)
	public String editstore(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String storecount = multipartRequest.getParameter("storecount");
		String proid = multipartRequest.getParameter("id");
		int propid = Integer.parseInt(proid);
		int count = Integer.parseInt(storecount);
		ProductPage_Store store = pageService.getStoreByPid(propid);
		if(store==null){
			store = new ProductPage_Store();
			store.setCount(count);
			ProductPage page = new ProductPage();
			page.setId(propid);
			store.setPage(page);
			store.setStatus(0);
			
		}
		else{
			store.setCount(count);
		}
		pageService.changeStore(store);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+proid;
	}

	@RequestMapping(value = "/productpages/type",method=RequestMethod.GET)
	public String index(Model model){
		List<PageType> types = null;
		types = pageService.getTypes();
		model.addAttribute("types", types);
		return "productpages/type";
	}
	
	@RequestMapping(value = "/productpages/addtype", method = RequestMethod.POST)
	public String addType(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		if(pageService.checkTypeName(name)>0){
			redirectAttributes.addFlashAttribute("message", "名称重复");
		}
		else {
			PageType type = new PageType();
			type.setName(name);
			pageService.addType(type);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:type";
	}
	
	@RequestMapping(value = "/productpages/edittype", method = RequestMethod.POST)
	public String editType(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String eid = multipartRequest.getParameter("eid");
		int id = Integer.parseInt(eid);
		PageType pageType = pageService.getTypeById(id);
		if(pageType.getName().equals(name)){
			pageService.editType(pageType);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		else{
			if(pageService.checkTypeName(name)>0){
				redirectAttributes.addFlashAttribute("message", "名称重复");
			}
			else {
				pageType.setName(name);
				pageService.editType(pageType);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:type";
	}
	
	@RequestMapping(value = "/productpages/deletetype", method = RequestMethod.POST)
	@ResponseBody
	public String deleteType(@RequestParam(required = true) Integer id) {
		pageService.deleteType(id);
		return "true";
	}
	
	@RequestMapping(value = "/productpages/store",method=RequestMethod.GET)
	public String stores(Model model,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductPage_Store> stores = null;
		int sumcount = 0;
		stores = pageService.getStores(new PageRequest(page, size));
		sumcount = pageService.storesAllCount();
		model.addAttribute("stores", stores);
		model.addAttribute("sumcount", sumcount);
		return "productpages/store";
	}
	
	@RequestMapping(value = "/productpages/in",method=RequestMethod.GET)
	public String productpagein(Model model,@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductPage_In> productPage_Ins = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productPage_Ins = pageService.findIns(context, new PageRequest(page,size));
			sumcount = pageService.searchInCount(context);
		} else {
			productPage_Ins = pageService.getIns(new PageRequest(page, size));
			sumcount = pageService.inallcount();
		}
		model.addAttribute("ins", productPage_Ins);
		model.addAttribute("sumcount", sumcount);
		return "productpages/in";
	}
	
	@RequestMapping(value = "/productpages/newin",method=RequestMethod.GET)
	public String productpageNewin(Model model,@RequestParam(required = true) int pid){
		ProductPage page = pageService.getById(pid);	
		model.addAttribute("page", page);
		return "productpages/newin";
	}
	
	@RequestMapping(value = "/productpages/inaddone", method = RequestMethod.POST)
	public String inaddOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String count = multipartRequest.getParameter("count");
		String pid = multipartRequest.getParameter("pid");
		String datestr = multipartRequest.getParameter("date");
		
		ProductPage page = pageService.getById(Integer.parseInt(pid));
		int pcount = Integer.parseInt(count);
		ProductPage_In productPage_In = new ProductPage_In();
		productPage_In.setCount(pcount);
		productPage_In.setPage(page);
		productPage_In.setDate(DateTools.ParseString(datestr));
		pageService.createIn(productPage_In);
		
		ProductPage_Store productPage_Store = pageService.getStoreByPid(page.getId());
		if(productPage_Store==null){
			productPage_Store = new ProductPage_Store();
			productPage_Store.setPage(page);
			productPage_Store.setCount(0);
		}
		productPage_Store.setCount(productPage_Store.getCount()+pcount);
		pageService.changeStore(productPage_Store);
		redirectAttributes.addFlashAttribute("success", "入库成功");
		
		return "redirect:profile?id="+pid;
	}
	
	@RequestMapping(value = "/productpages/out",method=RequestMethod.GET)
	public String productpageout(Model model,@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductPage_Out> productPage_Outs = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productPage_Outs = pageService.findOuts(context, new PageRequest(page,size));
			sumcount = pageService.searchOutCount(context);
		} else {
			productPage_Outs = pageService.getOuts(new PageRequest(page, size));
			sumcount = pageService.outallcount();
		}
		model.addAttribute("outs", productPage_Outs);
		model.addAttribute("sumcount", sumcount);
		return "productpages/out";
	}
	
	@RequestMapping(value = "/productpages/newout",method=RequestMethod.GET)
	public String productpageNewout(Model model,@RequestParam(required = true) int pid){
		ProductPage page = pageService.getById(pid);
		
		model.addAttribute("page", page);
		return "productpages/newout";
	}
	
	@RequestMapping(value = "/productpages/outaddone", method = RequestMethod.POST)
	public String outaddOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String count = multipartRequest.getParameter("count");
		String did = multipartRequest.getParameter("did");
		String pid = multipartRequest.getParameter("pid");
		String datestr = multipartRequest.getParameter("date");
		
		ProductPage_Store productPage_Store = pageService.getStoreByPid(Integer.parseInt(pid));
		if(productPage_Store==null){
			redirectAttributes.addFlashAttribute("message", "库存为0，无法出库");
		}
		else{
			if(productPage_Store.getCount()<Integer.parseInt(count)){
				redirectAttributes.addFlashAttribute("message", "库存不足，无法出库");
			}
			else{
				ProductPage_Out productPage_Out = new ProductPage_Out();
				productPage_Out.setCount(Integer.parseInt(count));
				ProductPage page = new ProductPage();
				page.setId(Integer.parseInt(pid));
				productPage_Out.setPage(page);
				Dealer dealer = new Dealer();
				dealer.setId(Integer.parseInt(did));
				productPage_Out.setDealer(dealer);
				productPage_Out.setDate(DateTools.ParseString(datestr));
				pageService.createOut(productPage_Out);
				productPage_Store.setCount(productPage_Store.getCount()-Integer.parseInt(count));
				pageService.changeStore(productPage_Store);
				redirectAttributes.addFlashAttribute("success", "出库成功");
			}
		}
		
		return "redirect:profile?id="+pid;
	}
	
	@RequestMapping(value = "/productpages/selectpages",method=RequestMethod.GET)
	public String selectraws(Model model){
		List<PageNote> notes = pageService.getPageNotes();
		
		model.addAttribute("notes", notes);
		return "productpages/selectpages";
	}
}
