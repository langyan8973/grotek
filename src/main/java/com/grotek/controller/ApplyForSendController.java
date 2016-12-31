package com.grotek.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.Employee;
import com.grotek.pojo.ProductPackApItem;
import com.grotek.pojo.ProductPageApItem;
import com.grotek.pojo.ProductboxApItem;
import com.grotek.pojo.SampleApItem;
import com.grotek.service.ApplyForSendService;
import com.grotek.service.DealerService;
import com.grotek.service.EmployeeService;
import com.grotek.service.UserContext;

@Controller
@RequestMapping("/work")
public class ApplyForSendController {
	
	@Autowired
	private ApplyForSendService applyService;

	@Autowired
	private UserContext userContext;
	
	@Autowired
	private EmployeeService emService;
	
	@Autowired
	private DealerService dealerService;
	
	@RequestMapping(value = "/applyforsend/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		Employee employee = userContext.getCurrentUser();
		List<ApplyForSend> applies = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			applies = applyService.findByEid(employee.getId(), context, new PageRequest(page, size));
			sumcount = applyService.searchCountByEid(employee.getId(), context);
		} else {
			applies = applyService.getByEid(employee.getId(), new PageRequest(page, size));
			sumcount = applyService.allCountByEid(employee.getId());
		}
		model.addAttribute("applies", applies);
		model.addAttribute("sumcount", sumcount);
		return "applyforsend/index";
	}
	
	@RequestMapping(value = "/applyforsend/new",method=RequestMethod.GET)
	public String newApplyforSend(Model model){
		
		return "applyforsend/new";
	}
	
	@RequestMapping(value = "/applyforsend/addone", method = RequestMethod.POST)
	public String addone(@ModelAttribute("applyforsend") ApplyForSend applyforsend, RedirectAttributes redirectAttributes) {

		if (applyforsend != null) {
			Employee employee = userContext.getCurrentUser();
			String company = dealerService.getById(applyforsend.getDealer().getId()).getCompany();
			applyforsend.setDcompany(company);
			double total = 0;
	
			if(applyforsend.getBoxitems()!=null && applyforsend.getBoxitems().size()>0){
				for (Iterator iterator = applyforsend.getBoxitems().iterator(); iterator.hasNext();) {
					ProductboxApItem bApItem = (ProductboxApItem) iterator.next();
					if(bApItem.getPrice()==null || bApItem.getCount()==null){
						continue;
					}
					bApItem.setTotal(bApItem.getPrice()*bApItem.getCount());
					bApItem.setOpstatus(0);
					bApItem.setStatus(0);
					total+=bApItem.getTotal();
				}
			}
			
			applyforsend.setTotal(total);
			applyforsend.setEmployee(employee);
			applyforsend.setOpstatus(0);
			applyforsend.setStatus(0);
			applyforsend.setDate(new Date());
			int row = applyService.createOne(applyforsend);
			if(row<=0){
				return "applyforsend/new";
			}
			if(applyforsend.getBoxitems()!=null && applyforsend.getBoxitems().size()>0){
				
				for (Iterator iterator = applyforsend.getBoxitems().iterator(); iterator.hasNext();) {
					ProductboxApItem bApItem = (ProductboxApItem) iterator.next();
					if(bApItem.getPrice()==null || bApItem.getCount()==null){
						continue;
					}
					bApItem.setAid(applyforsend.getId());
					applyService.addboxitem(bApItem);
				}
			}
			if(applyforsend.getSampleitems()!=null && applyforsend.getSampleitems().size()>0){
				for (Iterator iterator = applyforsend.getSampleitems().iterator(); iterator.hasNext();) {
					SampleApItem item = (SampleApItem) iterator.next();
					item.setAid(applyforsend.getId());
					item.setOpstatus(0);
					item.setStatus(0);
					applyService.addsampleitem(item);
				}
			}
			if(applyforsend.getPackitems()!=null && applyforsend.getPackitems().size()>0){
				for (Iterator iterator = applyforsend.getPackitems().iterator(); iterator.hasNext();) {
					ProductPackApItem packApItem = (ProductPackApItem) iterator.next();
					packApItem.setAid(applyforsend.getId());
					packApItem.setOpstatus(0);
					packApItem.setStatus(0);
					applyService.addpackitem(packApItem);
				}
			}
			if(applyforsend.getPageitems()!=null && applyforsend.getPageitems().size()>0){
				for (Iterator iterator = applyforsend.getPageitems().iterator(); iterator.hasNext();) {
					ProductPageApItem pageApItem = (ProductPageApItem) iterator.next();
					pageApItem.setAid(applyforsend.getId());
					pageApItem.setOpstatus(0);
					pageApItem.setStatus(0);
					applyService.addpageitem(pageApItem);
				}
			}
			
			return "redirect:index";
		} else {
			return "applyforsend/new";
		}

	}
	
	@RequestMapping(value = "/applyforsend/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true, defaultValue = "0") int id){
		ApplyForSend applyForSend = applyService.getById(id);
		model.addAttribute("apply", applyForSend);
		return "applyforsend/profile";
	}
	
	@RequestMapping(value = "/applyforsend/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(required = true, defaultValue = "0") int id){
		ApplyForSend applyForSend = applyService.getById(id);
		model.addAttribute("apply", applyForSend);
		return "applyforsend/edit";
	}
	
	@RequestMapping(value = "/applyforsend/editone", method = RequestMethod.POST)
	public String editone(@ModelAttribute("applyforsend") ApplyForSend applyforsend, RedirectAttributes redirectAttributes) {

		if (applyforsend != null) {
			ApplyForSend send = applyService.getById(applyforsend.getId());
			if(send.getBoxitems()!=null && send.getBoxitems().size()>0){
				for (Iterator iterator = send.getBoxitems().iterator(); iterator.hasNext();) {
					ProductboxApItem bApItem = (ProductboxApItem) iterator.next();
					applyService.deleteboxitem(bApItem.getId());
				}
			}
			if(send.getSampleitems()!=null && send.getSampleitems().size()>0){
				for (Iterator iterator = send.getSampleitems().iterator(); iterator.hasNext();) {
					SampleApItem item = (SampleApItem) iterator.next();
					applyService.deletesampleitem(item.getId());
				}
			}
			if(send.getPackitems()!=null && send.getPackitems().size()>0){
				for (Iterator iterator = send.getPackitems().iterator(); iterator.hasNext();) {
					ProductPackApItem packApItem = (ProductPackApItem) iterator.next();
					applyService.deletepackitem(packApItem.getId());
				}
			}
			if(send.getPageitems()!=null && send.getPageitems().size()>0){
				for (Iterator iterator = send.getPageitems().iterator(); iterator.hasNext();) {
					ProductPageApItem pageApItem = (ProductPageApItem) iterator.next();
					applyService.deletepageitem(pageApItem.getId());
				}
			}
			String company = dealerService.getById(applyforsend.getDealer().getId()).getCompany();
			applyforsend.setDcompany(company);
			double total = 0;
			if(applyforsend.getBoxitems()!=null && applyforsend.getBoxitems().size()>0){
				
				for (Iterator iterator = applyforsend.getBoxitems().iterator(); iterator.hasNext();) {
					ProductboxApItem bApItem = (ProductboxApItem) iterator.next();
					if(bApItem.getPrice()==null || bApItem.getCount()==null){
						continue;
					}
					bApItem.setTotal(bApItem.getPrice()*bApItem.getCount());
					bApItem.setOpstatus(0);
					bApItem.setStatus(0);
					bApItem.setAid(send.getId());
					total+=bApItem.getTotal();
				}
			}
			applyforsend.setTotal(total);
			applyforsend.setEmployee(send.getEmployee());
			applyforsend.setOpstatus(0);
			applyforsend.setStatus(0);
			applyforsend.setDate(new Date());
			int row = applyService.updateOne(applyforsend);
			if(row<=0){
				return "applyforsend/edit";
			}
			if(applyforsend.getBoxitems()!=null && applyforsend.getBoxitems().size()>0){
				
				for (Iterator iterator = applyforsend.getBoxitems().iterator(); iterator.hasNext();) {
					ProductboxApItem bApItem = (ProductboxApItem) iterator.next();
					bApItem.setTotal(bApItem.getPrice()*bApItem.getCount());
					applyService.addboxitem(bApItem);
				}
			}
			if(applyforsend.getPackitems()!=null && applyforsend.getPackitems().size()>0){
				for (Iterator iterator = applyforsend.getPackitems().iterator(); iterator.hasNext();) {
					ProductPackApItem packApItem = (ProductPackApItem) iterator.next();
					packApItem.setAid(applyforsend.getId());
					packApItem.setOpstatus(0);
					packApItem.setStatus(0);
					applyService.addpackitem(packApItem);
				}
			}
			if(applyforsend.getSampleitems()!=null && applyforsend.getSampleitems().size()>0){
				for (Iterator iterator = applyforsend.getSampleitems().iterator(); iterator.hasNext();) {
					SampleApItem item = (SampleApItem) iterator.next();
					item.setAid(applyforsend.getId());
					item.setOpstatus(0);
					item.setStatus(0);
					applyService.addsampleitem(item);
				}
			}
			if(applyforsend.getPageitems()!=null && applyforsend.getPageitems().size()>0){
				for (Iterator iterator = applyforsend.getPageitems().iterator(); iterator.hasNext();) {
					ProductPageApItem pageApItem = (ProductPageApItem) iterator.next();
					pageApItem.setAid(applyforsend.getId());
					pageApItem.setOpstatus(0);
					pageApItem.setStatus(0);
					applyService.addpageitem(pageApItem);
				}
			}
			
			return "redirect:profile?id="+applyforsend.getId();
		} else {
			return "applyforsend/edit";
		}

	}
	
	@RequestMapping(value = "/applyforsend/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOne(@RequestParam(required = true) int id) {
		applyService.deleteById(id);
		return "true";
	}
}
