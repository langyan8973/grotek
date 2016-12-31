package com.grotek.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.pojo.ExsType;
import com.grotek.pojo.GrotekUnit;
import com.grotek.pojo.JobtypeDic;
import com.grotek.pojo.ManureType;
import com.grotek.pojo.TravelExsType;
import com.grotek.service.ExsTypeService;
import com.grotek.service.GrotekUnitService;
import com.grotek.service.JobTypeService;
import com.grotek.service.ManureTypeService;
import com.grotek.service.TravelExsTypeService;

@Controller
@RequestMapping("/manager")
public class DicsController {
	
	@Autowired
	private JobTypeService jobTypeService;
	@Autowired
	private TravelExsTypeService travelexstypeService;
	@Autowired
	private ExsTypeService exstypeService;
	@Autowired
	private GrotekUnitService unitService;
	@Autowired
	private ManureTypeService manuretypeService;

	@RequestMapping(value="/dics/jobtypes",method=RequestMethod.GET)
	public String index(Model model){
		List<JobtypeDic> types = jobTypeService.getAll();
		model.addAttribute("types", types);
		return "dics/jobtypes";
	}
	
	@RequestMapping(value = "/dics/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		if(jobTypeService.check(name, code)>0){
			redirectAttributes.addFlashAttribute("message", "名称或编码重复");
		}
		else {
			JobtypeDic jobtypeDic  = new JobtypeDic();
			jobtypeDic.setName(name);
			jobtypeDic.setCode(code);
			jobTypeService.addType(jobtypeDic);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:jobtypes";
	}
	
	@RequestMapping(value = "/dics/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String code = multipartRequest.getParameter("ecode");
		String eid = multipartRequest.getParameter("eid");
		int id = Integer.parseInt(eid);
		JobtypeDic jobtypeDic = jobTypeService.getById(Integer.parseInt(eid));
		if(jobtypeDic.getCode().equals(code) && jobtypeDic.getName().equals(name)){
			
		}
		else if(!jobtypeDic.getCode().equals(code) && jobtypeDic.getName().equals(name)){
			if(jobTypeService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				jobtypeDic.setCode(code);
				jobTypeService.editType(jobtypeDic);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else if(jobtypeDic.getCode().equals(code) && !jobtypeDic.getName().equals(name)){
			if(jobTypeService.check(name, code)>1){
				redirectAttributes.addFlashAttribute("message", "名称或编码重复");
			}
			else {
				jobtypeDic.setName(name);
				jobTypeService.editType(jobtypeDic);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else{
			if(jobTypeService.check(name, code)>0){
				redirectAttributes.addFlashAttribute("message", "名称或密码重复");
			}
			else {
				jobtypeDic.setCode(code);
				jobtypeDic.setName(name);
				jobTypeService.editType(jobtypeDic);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:jobtypes";
	}
	
	@RequestMapping(value = "/dics/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		jobTypeService.deleteByid(id);
		return "true";
	}
	
	
	@RequestMapping(value="/dics/travelexstypes",method=RequestMethod.GET)
	public String travelexstypes(Model model){
		List<TravelExsType> types = travelexstypeService.getAll();
		model.addAttribute("types", types);
		return "dics/travelexstypes";
	}
	
	@RequestMapping(value = "/dics/addtravelexstype", method = RequestMethod.POST)
	public String addtravelexstype(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		if(travelexstypeService.check(name)>0){
			redirectAttributes.addFlashAttribute("message", "名称重复");
		}
		else {
			TravelExsType travelExsType  = new TravelExsType();
			travelExsType.setName(name);
			travelexstypeService.addType(travelExsType);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:travelexstypes";
	}
	
	@RequestMapping(value = "/dics/edittravelexstype", method = RequestMethod.POST)
	public String edittravelexstype(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String eid = multipartRequest.getParameter("eid");
		int id = Integer.parseInt(eid);
		TravelExsType travelExsType = travelexstypeService.getById(Integer.parseInt(eid));
		if(travelExsType.getName().equals(name)){
			
		}
		else{
			if(travelexstypeService.check(name)>0){
				redirectAttributes.addFlashAttribute("message", "名称重复");
			}
			else {
				travelExsType.setName(name);
				travelexstypeService.editType(travelExsType);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:travelexstypes";
	}
	
	@RequestMapping(value = "/dics/deletetravelexstype", method = RequestMethod.POST)
	@ResponseBody
	public String deletetravelexstype(@RequestParam(required = true) Integer id) {
		travelexstypeService.deleteByid(id);
		return "true";
	}
	
	@RequestMapping(value="/dics/exstypes",method=RequestMethod.GET)
	public String exstypes(Model model){
		List<ExsType> types = exstypeService.getAll();
		model.addAttribute("types", types);
		return "dics/exstypes";
	}
	
	@RequestMapping(value = "/dics/addexstype", method = RequestMethod.POST)
	public String addexstype(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String label = multipartRequest.getParameter("label");
		if(exstypeService.check(name)>0){
			redirectAttributes.addFlashAttribute("message", "名称重复");
		}
		else {
			ExsType exsType  = new ExsType();
			exsType.setName(name);
			exsType.setLabel(label);
			exstypeService.addType(exsType);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:exstypes";
	}
	
	@RequestMapping(value = "/dics/editexstype", method = RequestMethod.POST)
	public String editexstype(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String label = multipartRequest.getParameter("elabel");
		String eid = multipartRequest.getParameter("eid");
		int id = Integer.parseInt(eid);
		ExsType exsType = exstypeService.getById(Integer.parseInt(eid));
		if(exsType.getName().equals(name)){
			exsType.setLabel(label);
			exstypeService.editType(exsType);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		else{
			if(exstypeService.check(name)>0){
				redirectAttributes.addFlashAttribute("message", "名称重复");
			}
			else {
				exsType.setName(name);
				exsType.setLabel(label);
				exstypeService.editType(exsType);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:exstypes";
	}
	
	@RequestMapping(value = "/dics/deleteexstype", method = RequestMethod.POST)
	@ResponseBody
	public String deleteexstype(@RequestParam(required = true) Integer id) {
		exstypeService.deleteByid(id);
		return "true";
	}
	
	@RequestMapping(value="/dics/units",method=RequestMethod.GET)
	public String units(Model model){
		List<GrotekUnit> units = unitService.getAll();
		model.addAttribute("units", units);
		return "dics/units";
	}
	
	@RequestMapping(value = "/dics/addunit", method = RequestMethod.POST)
	public String addunit(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		if(unitService.check(name)>0){
			redirectAttributes.addFlashAttribute("message", "名称重复");
		}
		else {
			GrotekUnit grotekUnit  = new GrotekUnit();
			grotekUnit.setName(name);
			unitService.addOne(grotekUnit);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:units";
	}
	
	@RequestMapping(value = "/dics/editunit", method = RequestMethod.POST)
	public String editunit(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String eid = multipartRequest.getParameter("eid");
		int id = Integer.parseInt(eid);
		GrotekUnit grotekUnit = unitService.getById(Integer.parseInt(eid));
		if(grotekUnit.getName().equals(name)){
			
		}
		else{
			if(unitService.check(name)>0){
				redirectAttributes.addFlashAttribute("message", "名称重复");
			}
			else {
				grotekUnit.setName(name);
				unitService.editOne(grotekUnit);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:units";
	}
	
	@RequestMapping(value = "/dics/deleteunit", method = RequestMethod.POST)
	@ResponseBody
	public String deleteunit(@RequestParam(required = true) Integer id) {
		unitService.delete(id);
		return "true";
	}
	
	@RequestMapping(value="/dics/manuretypes",method=RequestMethod.GET)
	public String manuretypes(Model model){
		List<ManureType> types = manuretypeService.getAll();
		model.addAttribute("types", types);
		return "dics/manuretypes";
	}
	
	@RequestMapping(value = "/dics/addmanure", method = RequestMethod.POST)
	public String addmanu(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		if(manuretypeService.check(name)>0){
			redirectAttributes.addFlashAttribute("message", "名称重复");
		}
		else {
			ManureType manureType  = new ManureType();
			manureType.setName(name);
			manuretypeService.insertOne(manureType);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:manuretypes";
	}
	
	@RequestMapping(value = "/dics/editmanure", method = RequestMethod.POST)
	public String editmanure(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("ename");
		String eid = multipartRequest.getParameter("eid");
		int id = Integer.parseInt(eid);
		ManureType manureType = manuretypeService.getById(Integer.parseInt(eid));
		if(manureType.getName().equals(name)){
			
		}
		else{
			if(manuretypeService.check(name)>0){
				redirectAttributes.addFlashAttribute("message", "名称重复");
			}
			else {
				manureType.setName(name);
				manuretypeService.updateOne(manureType);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:manuretypes";
	}
	
	@RequestMapping(value = "/dics/deletemanure", method = RequestMethod.POST)
	@ResponseBody
	public String deletemanure(@RequestParam(required = true) Integer id) {
		manuretypeService.delete(id);
		return "true";
	}
	
}
