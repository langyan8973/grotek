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

import com.grotek.dto.PackNote;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DepartmentDic;
import com.grotek.pojo.PackType;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPack_In;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductRaw;
import com.grotek.pojo.ProductRaw_In;
import com.grotek.pojo.ProductRaw_Out;
import com.grotek.pojo.ProductRaw_Price;
import com.grotek.pojo.ProductRaw_Store;
import com.grotek.pojo.ReferencePackWeight;
import com.grotek.service.ProductPackService;
import com.grotek.util.DateTools;

@Controller
@RequestMapping("/manager")
public class ProductPackController {
	
	@Autowired
	private ProductPackService productPackService;
	
	@RequestMapping(value = "/productpacks/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductPack> productPacks = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productPacks = productPackService.findProductPacks(context, new PageRequest(page,size));
			sumcount = productPackService.searchCount(context);
		} else {
			productPacks = productPackService.getProductPacks(new PageRequest(page, size));
			sumcount = productPackService.allCount();
		}
		List<PackType> types = productPackService.getTypes();
		model.addAttribute("productpacks", productPacks);
		model.addAttribute("types", types);
		model.addAttribute("sumcount", sumcount);
		return "productpacks/index";
	}
	
	@RequestMapping(value = "/productpacks/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String type = multipartRequest.getParameter("type");
		String price = multipartRequest.getParameter("price");
		if(productPackService.check(name, code)>0){
			redirectAttributes.addFlashAttribute("message", "名称或编码重复");
		}
		else {
			ProductPack productPack = new ProductPack();
			productPack.setName(name);
			productPack.setCode(code);
			PackType packType = productPackService.getTypeById(Integer.parseInt(type));
			productPack.setType(packType);
			productPack.setPrice(Double.parseDouble(price));
			productPack.setUadateDate(new Date());
			productPackService.addProductPack(productPack);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/productpacks/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true) String id){
		ProductPack productPack= productPackService.getById(Integer.parseInt(id));
		List<PackType> types = productPackService.getTypes();
		ReferencePackWeight referencePackWeight = productPackService.getReferencePackWeightByPid(Integer.parseInt(id));
		int needreferen = 0;
		ProductPack_Store store = productPackService.getStoreByPid(Integer.parseInt(id));
		List<ProductPack_In> ins = productPackService.getInByPid(Integer.parseInt(id));
		List<ProductPack_Out> outs = productPackService.getOutByPid(Integer.parseInt(id));
		if(productPack.getType().getTrans()==1 && referencePackWeight==null){
			needreferen=1;
		}
		model.addAttribute("productpack", productPack);
		model.addAttribute("types", types);
		model.addAttribute("referen", referencePackWeight);
		model.addAttribute("need", needreferen);
		model.addAttribute("store", store);
		model.addAttribute("ins", ins);
		model.addAttribute("outs", outs);
		return "productpacks/profile";
	}
	
	@RequestMapping(value = "/productpacks/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String eid = multipartRequest.getParameter("id");
		String type = multipartRequest.getParameter("type");
		String price = multipartRequest.getParameter("price");
		int id = Integer.parseInt(eid);
		ProductPack productPack = productPackService.getById(id);
		if(productPack.getCode().equals(code) && productPack.getName().equals(name)){
			PackType packType = productPackService.getTypeById(Integer.parseInt(type));
			productPack.setType(packType);
			productPack.setPrice(Double.parseDouble(price));
			productPack.setUadateDate(new Date());
			productPackService.editProductPack(productPack);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		else if(!productPack.getCode().equals(code) && productPack.getName().equals(name)){
			if(productPackService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				productPack.setCode(code);
				PackType packType = productPackService.getTypeById(Integer.parseInt(type));
				productPack.setType(packType);
				productPack.setPrice(Double.parseDouble(price));
				productPack.setUadateDate(new Date());
				productPackService.editProductPack(productPack);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else if(productPack.getCode().equals(code) && !productPack.getName().equals(name)){
			if(productPackService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				productPack.setName(name);
				PackType packType = productPackService.getTypeById(Integer.parseInt(type));
				productPack.setType(packType);
				productPack.setPrice(Double.parseDouble(price));
				productPack.setUadateDate(new Date());
				productPackService.editProductPack(productPack);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else{
			if(productPackService.check(name, code)>0){
				redirectAttributes.addFlashAttribute("message", "名称或密码重复");
			}
			else {
				productPack.setCode(code);
				productPack.setName(name);
				PackType packType = productPackService.getTypeById(Integer.parseInt(type));
				productPack.setType(packType);
				productPack.setPrice(Double.parseDouble(price));
				productPack.setUadateDate(new Date());
				productPackService.editProductPack(productPack);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:profile?id="+id;
	}
	
	@RequestMapping(value = "/productpacks/editstore", method = RequestMethod.POST)
	public String editstore(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String storecount = multipartRequest.getParameter("storecount");
		String proid = multipartRequest.getParameter("id");
		int propid = Integer.parseInt(proid);
		int count = Integer.parseInt(storecount);
		ProductPack_Store store = productPackService.getStoreByPid(propid);
		if(store==null){
			store = new ProductPack_Store();
			store.setCount(count);
			ProductPack pack = new ProductPack();
			pack.setId(propid);
			store.setPack(pack);
			store.setStatus(0);
			
		}
		else{
			store.setCount(count);
		}
		productPackService.changeStore(store);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+proid;
	}
	
	@RequestMapping(value = "/productpacks/type",method=RequestMethod.GET)
	public String index(Model model){
		List<PackType> types = null;
		types = productPackService.getTypes();
		model.addAttribute("types", types);
		return "productpacks/type";
	}
	
	@RequestMapping(value = "/productpacks/addtype", method = RequestMethod.POST)
	public String addType(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String trans = multipartRequest.getParameter("trans");
		if(productPackService.checkTypeName(name)>0){
			redirectAttributes.addFlashAttribute("message", "名称重复");
		}
		else {
			PackType type = new PackType();
			type.setName(name);
			type.setTrans(Integer.parseInt(trans));
			productPackService.addType(type);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:type";
	}
	
	@RequestMapping(value = "/productpacks/edittype", method = RequestMethod.POST)
	public String editType(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String eid = multipartRequest.getParameter("eid");
		String trans = multipartRequest.getParameter("etrans");
		int id = Integer.parseInt(eid);
		PackType packType = productPackService.getTypeById(id);
		if(packType.getName().equals(name)){
			packType.setTrans(Integer.parseInt(trans));
			productPackService.editType(packType);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		else{
			if(productPackService.checkTypeName(name)>0){
				redirectAttributes.addFlashAttribute("message", "名称或密码重复");
			}
			else {
				packType.setName(name);
				packType.setTrans(Integer.parseInt(trans));
				productPackService.editType(packType);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:type";
	}
	
	@RequestMapping(value = "/productpacks/deletetype", method = RequestMethod.POST)
	@ResponseBody
	public String deleteType(@RequestParam(required = true) Integer id) {
		productPackService.deleteType(id);
		return "true";
	}
	
	
	@RequestMapping(value = "/productpacks/references",method=RequestMethod.GET)
	public String references(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ReferencePackWeight> referens = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			referens = productPackService.findReferencePackWeights(context, new PageRequest(page,size));
			sumcount = productPackService.searchReferencePackWeightCount(context);
		} else {
			referens = productPackService.getReferencePackWeights(new PageRequest(page, size));
			sumcount = productPackService.referencePackWeightallcount();
		}
		model.addAttribute("referens", referens);
		model.addAttribute("sumcount", sumcount);
		return "productpacks/references";
	}
	
	@RequestMapping(value = "/productpacks/editreferen", method = RequestMethod.POST)
	public String editreferen(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String material = multipartRequest.getParameter("material");
		String packid = multipartRequest.getParameter("packid");
		String uprice = multipartRequest.getParameter("uprice");
		String uweight2 = multipartRequest.getParameter("uweight2");
		String rid = multipartRequest.getParameter("rid");
		if(StringUtils.isNotBlank(rid)){
			productPackService.deleteReferencePackWeight(Integer.parseInt(rid));
		}
		ProductPack productPack = productPackService.getById(Integer.parseInt(packid));
		ReferencePackWeight referencePackWeight = new ReferencePackWeight();
		referencePackWeight.setMaterial(material);
		referencePackWeight.setPack(productPack);
		referencePackWeight.setUprice(Double.parseDouble(uprice));
		referencePackWeight.setUweight2(Double.parseDouble(uweight2));
		referencePackWeight.setUpdatedate(new Date());
		productPackService.createReferencePackWeight(referencePackWeight);
		productPack.setPrice(referencePackWeight.getUprice()/(1000/referencePackWeight.getUweight2()));
		productPack.setUadateDate(new Date());
		productPackService.editProductPack(productPack);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		
		return "redirect:profile?id="+packid;
	}
	
	@RequestMapping(value = "/productpacks/store",method=RequestMethod.GET)
	public String stores(Model model,String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductPack_Store> stores = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			stores = productPackService.findProductKcun(context, new PageRequest(page,size));
			sumcount = productPackService.searchkuncCount(context);
 		} else {
		stores = productPackService.getStores(new PageRequest(page, size));
		sumcount = productPackService.storesAllCount();
 		}
		model.addAttribute("stores", stores);
		model.addAttribute("sumcount", sumcount);
		return "productpacks/store";
	}
	
	@RequestMapping(value = "/productpacks/in",method=RequestMethod.GET)
	public String productrawin(Model model,@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductPack_In> productPack_Ins = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productPack_Ins = productPackService.findIns(context, new PageRequest(page,size));
			sumcount = productPackService.searchInCount(context);
		} else {
			productPack_Ins = productPackService.getIns(new PageRequest(page, size));
			sumcount = productPackService.inallcount();
		}
		model.addAttribute("ins", productPack_Ins);
		model.addAttribute("sumcount", sumcount);
		return "productpacks/in";
	}
	
	@RequestMapping(value = "/productpacks/newin",method=RequestMethod.GET)
	public String productrawNewin(Model model,@RequestParam(required = true) int pid){
		ProductPack pack = productPackService.getById(pid);	
		model.addAttribute("pack", pack);
		return "productpacks/newin";
	}

	@RequestMapping(value = "/productpacks/inaddone", method = RequestMethod.POST)
	public String inaddOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String count = multipartRequest.getParameter("count");
		String pid = multipartRequest.getParameter("pid");
		String datestr = multipartRequest.getParameter("date");
		
		ProductPack pack = productPackService.getById(Integer.parseInt(pid));
		int pcount = Integer.parseInt(count);
		if(pack.getType().getTrans()==1){
			ReferencePackWeight referencePackWeight = productPackService.getReferencePackWeightByPid(pack.getId());
			pcount = (int)(pcount*(1000/referencePackWeight.getUweight2()));
		}
		ProductPack_In productPack_In = new ProductPack_In();
		productPack_In.setCount(pcount);
		productPack_In.setPack(pack);
		productPack_In.setDate(DateTools.ParseString(datestr));
		productPackService.createIn(productPack_In);
		
		ProductPack_Store productPack_Store = productPackService.getStoreByPid(pack.getId());
		if(productPack_Store==null){
			productPack_Store = new ProductPack_Store();
			productPack_Store.setPack(pack);
			productPack_Store.setCount(0);
		}
		productPack_Store.setCount(productPack_Store.getCount()+pcount);
		productPackService.changeStore(productPack_Store);
		redirectAttributes.addFlashAttribute("success", "入库成功");
		
		return "redirect:profile?id="+pid;
	}
	
	@RequestMapping(value = "/productpacks/out",method=RequestMethod.GET)
	public String productpackout(Model model,@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductPack_Out> productPack_Outs = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productPack_Outs = productPackService.findOuts(context, new PageRequest(page,size));
			sumcount = productPackService.searchOutCount(context);
		} else {
			productPack_Outs = productPackService.getOuts(new PageRequest(page, size));
			sumcount = productPackService.outallcount();
		}
		model.addAttribute("outs", productPack_Outs);
		model.addAttribute("sumcount", sumcount);
		return "productpacks/out";
	}
	
	@RequestMapping(value = "/productpacks/newout",method=RequestMethod.GET)
	public String productpackNewout(Model model,@RequestParam(required = true) int pid){
		ProductPack pack = productPackService.getById(pid);
		
		model.addAttribute("pack", pack);
		return "productpacks/newout";
	}
	
	@RequestMapping(value = "/productpacks/outaddone", method = RequestMethod.POST)
	public String outaddOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String count = multipartRequest.getParameter("count");
		String did = multipartRequest.getParameter("did");
		String pid = multipartRequest.getParameter("pid");
		String datestr = multipartRequest.getParameter("date");
		
		ProductPack_Store productPack_Store = productPackService.getStoreByPid(Integer.parseInt(pid));
		if(productPack_Store==null){
			redirectAttributes.addFlashAttribute("message", "库存为0，无法出库");
		}
		else{
			if(productPack_Store.getCount()<Integer.parseInt(count)){
				redirectAttributes.addFlashAttribute("message", "库存不足，无法出库");
			}
			else{
				ProductPack_Out productPack_Out = new ProductPack_Out();
				productPack_Out.setCount(Integer.parseInt(count));
				ProductPack productPack = new ProductPack();
				productPack.setId(Integer.parseInt(pid));
				productPack_Out.setPack(productPack);
				Dealer dealer = new Dealer();
				dealer.setId(Integer.parseInt(did));
				productPack_Out.setDealer(dealer);
				productPack_Out.setDate(DateTools.ParseString(datestr));
				productPackService.createOut(productPack_Out);
				
				productPack_Store.setCount(productPack_Store.getCount()-Integer.parseInt(count));
				productPackService.changeStore(productPack_Store);
				redirectAttributes.addFlashAttribute("success", "出库成功");
			}
		}
		
		return "redirect:profile?id="+pid;
	}
	
	@RequestMapping(value = "/productpacks/selectpacks",method=RequestMethod.GET)
	public String selectraws(Model model){
		
		List<PackNote> notes = productPackService.getPackNotes();
		
		model.addAttribute("notes", notes);
		return "productpacks/selectpacks";
	}
}
