package com.grotek.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.poifs.property.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.dto.CheckStoreNote;
import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DeliveryOrder;
import com.grotek.pojo.Employee;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductBox_Store;
import com.grotek.pojo.ProductBox_out;
import com.grotek.pojo.ProductPackApItem;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductPageApItem;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductPage_Store;
import com.grotek.pojo.ProductboxApItem;
import com.grotek.pojo.SampleApItem;
import com.grotek.service.ApplyForSendService;
import com.grotek.service.DealerService;
import com.grotek.service.DeliveryOrderService;
import com.grotek.service.EmployeeService;
import com.grotek.service.ProductBoxService;
import com.grotek.service.ProductPackService;
import com.grotek.service.ProductPageService;
import com.grotek.util.CustomXWPFDocument;
import com.grotek.util.DateTools;
import com.grotek.util.PublicConfig;
import com.grotek.util.PublicHelper;
import com.grotek.util.WordUtil;

@Controller
@RequestMapping("/manager")
public class SendApplyController {

	@Autowired
	private ApplyForSendService applyForSendService;
	
	@Autowired
	private ProductBoxService boxService;
	
	@Autowired
	private ProductPackService packService;
	
	@Autowired
	private ProductPageService pageService;
	
	@Autowired
	private EmployeeService emService;
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private DeliveryOrderService deliveryService;
	
	@RequestMapping(value = "/sendapplies/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ApplyForSend> applies = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			applies = applyForSendService.findCheckpending(context, new PageRequest(page, size));
			sumcount = applyForSendService.searchCountCheckpending(context);
		} else {
			applies = applyForSendService.getAllCheckpending(new PageRequest(page, size));
			sumcount = applyForSendService.allCountCheckpending();
		}
		model.addAttribute("applies", applies);
		model.addAttribute("sumcount", sumcount);
		return "sendapplies/index";
	}
	
	@RequestMapping(value = "/sendapplies/delivered",method=RequestMethod.GET)
	public String delivered(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ApplyForSend> applies = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			applies = applyForSendService.findDelivered(context, new PageRequest(page, size));
			sumcount = applyForSendService.searchCountDelivered(context);
		} else {
			applies = applyForSendService.getAllDelivered(new PageRequest(page, size));
			sumcount = applyForSendService.allCountDelivered();
		}
		model.addAttribute("applies", applies);
		model.addAttribute("sumcount", sumcount);
		return "sendapplies/delivered";
	}
	
	@RequestMapping(value = "/sendapplies/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true, defaultValue = "0") int id){
		ApplyForSend applyForSend = applyForSendService.getById(id);
		
		model.addAttribute("apply", applyForSend);
		return "sendapplies/profile";
	}
	
	@RequestMapping(value = "/sendapplies/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOne(@RequestParam(required = true) Integer id) {
		applyForSendService.deleteById(id);
		return "true";
	}
	
	@RequestMapping(value = "/sendapplies/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(required = true, defaultValue = "0") int id){
		ApplyForSend applyForSend = applyForSendService.getById(id);
		model.addAttribute("apply", applyForSend);
		return "sendapplies/edit";
	}
	
	@RequestMapping(value = "/sendapplies/editone", method = RequestMethod.POST)
	public String editone(@ModelAttribute("applyforsend") ApplyForSend applyforsend, RedirectAttributes redirectAttributes) {

		if (applyforsend != null) {
			ApplyForSend send = applyForSendService.getById(applyforsend.getId());
			double total = 0;
			if(send.getBoxitems()!=null && send.getBoxitems().size()>0){
				for (int i=0;i<send.getBoxitems().size();i++) {
					ProductboxApItem bApItem = send.getBoxitems().get(i);
					ProductboxApItem item = applyforsend.getBoxitems().get(i);
					bApItem.setCount(item.getCount());
					bApItem.setTotal(PublicHelper.correctTo(bApItem.getCount()*bApItem.getPrice()));
					applyForSendService.editboxitem(bApItem);
					total+=bApItem.getTotal();
				}
			}
			if(send.getPackitems()!=null && send.getPackitems().size()>0){
				for (int i=0;i<send.getPackitems().size();i++) {
					ProductPackApItem packApItem = send.getPackitems().get(i);
					ProductPackApItem item = applyforsend.getPackitems().get(i);
					packApItem.setCount(item.getCount());
					applyForSendService.editpackitem(packApItem);
				}
			}
			if(send.getSampleitems()!=null && send.getSampleitems().size()>0){
				for (int i=0;i<send.getSampleitems().size();i++) {
					SampleApItem sampleApItem = send.getSampleitems().get(i);
					SampleApItem item = applyforsend.getSampleitems().get(i);
					sampleApItem.setCount(item.getCount());
					applyForSendService.editsampleitem(sampleApItem);
				}
			}
			if(send.getPageitems()!=null && send.getPageitems().size()>0){
				for (int i=0;i<send.getPageitems().size();i++) {
					ProductPageApItem pageApItem = send.getPageitems().get(i);
					ProductPageApItem item = applyforsend.getPageitems().get(i);
					pageApItem.setCount(item.getCount());
					applyForSendService.editpageitem(pageApItem);
				}
			}
			send.setTotal(total);
			int row = applyForSendService.updateOne(send);
			if(row<=0){
				return "sendapplies/edit";
			}
			
			return "redirect:profile?id="+send.getId();
		} else {
			return "sendapplies/edit";
		}

	}
	
	@RequestMapping(value = "/sendapplies/check", method = RequestMethod.POST)
	@ResponseBody
	public CheckStoreNote check(@RequestParam(required = true) Integer id) {
		ApplyForSend applyForSend = applyForSendService.getById(id);
		String result = "";
		for (Iterator iterator = applyForSend.getBoxitems().iterator(); iterator.hasNext();) {
			ProductboxApItem productboxApItem = (ProductboxApItem) iterator.next();
			ProductBox_Store box_Store = boxService.getStoreByPid(productboxApItem.getBox().getId());
			if(box_Store==null || box_Store.getCount()<productboxApItem.getCount()){
				result+=productboxApItem.getBox().getName()+"库存不足  ";
			}
		}
		if(applyForSend.getPackitems()!=null && applyForSend.getPackitems().size()>0){
			for (Iterator iterator = applyForSend.getPackitems().iterator(); iterator.hasNext();) {
				ProductPackApItem packApItem = (ProductPackApItem) iterator.next();
				ProductPack_Store pack_Store = packService.getStoreByPid(packApItem.getPack().getId());
				if(pack_Store==null || pack_Store.getCount()<packApItem.getCount()){
					result+=packApItem.getPack().getName()+"库存不足  ";
				}
			}
		}
		if(applyForSend.getPageitems()!=null && applyForSend.getPageitems().size()>0){
			for (Iterator iterator = applyForSend.getPageitems().iterator(); iterator.hasNext();) {
				ProductPageApItem pageApItem = (ProductPageApItem) iterator.next();
				ProductPage_Store page_Store = pageService.getStoreByPid(pageApItem.getPage().getId());
				if(page_Store==null || page_Store.getCount()<pageApItem.getCount()){
					result+=pageApItem.getPage().getName()+"库存不足  ";
				}
			}
		}		
		CheckStoreNote note = new CheckStoreNote();
		note.setStatus(1);		
		if(result.length()>0)
		{
			note.setStatus(0);
			note.setMessage(result);
		}		
		return note;
	}
	
	@RequestMapping(value = "/sendapplies/newpost",method=RequestMethod.GET)
	public String newpost(Model model,@RequestParam(required = true, defaultValue = "0") int id){
		ApplyForSend applyForSend = applyForSendService.getById(id);
		Employee employee = emService.getById(applyForSend.getEmployee().getId());
		Dealer dealer = dealerService.getById(applyForSend.getDealer().getId());
		if(applyForSend.getBoxitems()!=null){
			for (Iterator iterator = applyForSend.getBoxitems().iterator(); iterator.hasNext();) {
				ProductboxApItem item = (ProductboxApItem) iterator.next();
				ProductBox box = boxService.getById(item.getBox().getId());
				double piececontent = 0;
				if(box.getPiececontent()!=null){
					piececontent = box.getPiececontent();
				}
				item.getBox().setPiececontent(piececontent);
			}
		}
		model.addAttribute("apply", applyForSend);
		model.addAttribute("employee", employee);
		model.addAttribute("dealer", dealer);
		return "sendapplies/newpost";
	}
	
	
	@RequestMapping(value = "/sendapplies/addpost", method = RequestMethod.POST)
	public String addone(@ModelAttribute("deliveryOrder") DeliveryOrder deliveryOrder, RedirectAttributes redirectAttributes) {

		int i = deliveryService.saveDeliveryOrder(deliveryOrder);
		if(i==0){
			return "sendapplies/newpost?id=" + deliveryOrder.getAid();
		}
		else{
			return "redirect:profile?id=" + deliveryOrder.getAid();
		}
	}
	
	@RequestMapping(value = "/sendapplies/order",method=RequestMethod.GET)
	public String order(Model model,@RequestParam(required = false, defaultValue = "0") int id,
			@RequestParam(required = false, defaultValue = "0") int aid){
		DeliveryOrder deliveryOrder;
		if(id!=0){
			deliveryOrder = deliveryService.getById(id);
		}
		else{
			deliveryOrder = deliveryService.getByAid(aid);
		}
		if(deliveryOrder!=null){
			if(deliveryOrder.getBoxitems()!=null){
				for (Iterator iterator = deliveryOrder.getBoxitems().iterator(); iterator.hasNext();) {
					ProductboxApItem item = (ProductboxApItem) iterator.next();
					ProductBox box = boxService.getById(item.getBox().getId());
					double piececontent = 0;
					if(box.getPiececontent()!=null){
						piececontent = box.getPiececontent();
					}
					item.getBox().setPiececontent(piececontent);
				}
			}
			Employee employee = emService.getById(deliveryOrder.getEmployee().getId());
			Dealer dealer = dealerService.getById(deliveryOrder.getDealer().getId());
			model.addAttribute("order", deliveryOrder);
			model.addAttribute("employee", employee);
			model.addAttribute("dealer", dealer);
		}
		
		return "sendapplies/order";
	}
	
	@RequestMapping(value = "/sendapplies/orderdown",method=RequestMethod.GET)
	public void orderdown(Model model,@RequestParam(required = true, defaultValue = "0") int id,
			HttpServletRequest request,HttpServletResponse response) {
		DeliveryOrder deliveryOrder;
		deliveryOrder = deliveryService.getById(id);
		Dealer dealer = dealerService.getById(deliveryOrder.getDealer().getId());
		
		 Map<String, Object> param = new HashMap<String, Object>(); 
		 param.put("payName", deliveryOrder.getPayName()==null?"":deliveryOrder.getPayName());
		 param.put("reName", deliveryOrder.getReceiveName()==null?"":deliveryOrder.getReceiveName());
		 param.put("payAddress", deliveryOrder.getPayAddress()==null?"":deliveryOrder.getPayAddress());
		 param.put("reAddress", deliveryOrder.getReceiveAddress()==null?"":deliveryOrder.getReceiveAddress());
		 param.put("payContact", deliveryOrder.getPayContact()==null?"":deliveryOrder.getPayContact());
		 param.put("reContact", deliveryOrder.getReceiveContact()==null?"":deliveryOrder.getReceiveContact());
		 param.put("payPhone", deliveryOrder.getPayPhone()==null?"":deliveryOrder.getPayPhone());
		 param.put("rePhone", deliveryOrder.getReceivePhone()==null?"":deliveryOrder.getReceivePhone());
		 param.put("employee", deliveryOrder.getEmployee()==null?"":deliveryOrder.getEmployee().getFullname());
		 param.put("dealer", deliveryOrder.getDealer()==null?"":deliveryOrder.getDealer().getName());
		 param.put("date", deliveryOrder.getDate()==null?"":DateTools.FormateDate(deliveryOrder.getDate()));
		 param.put("type", dealer==null?"":dealer.getCreditrating().getName());
		 param.put("mobile", deliveryOrder.getMobile()==null?"":deliveryOrder.getMobile());
		 param.put("goudan", deliveryOrder.getGdnum()==null?"":deliveryOrder.getGdnum());
		 param.put("htcode", deliveryOrder.getHtnum()==null?"":deliveryOrder.getHtnum());		 
		 String packs="";
		 String pages="";
		 
		 List<List> boxs = new ArrayList<List>();
		 if(deliveryOrder.getBoxitems()!=null && deliveryOrder.getBoxitems().size()>0){
			double totalkg=0;
			int count=0;
			double sum=0;
			for (int i = 0; i < deliveryOrder.getBoxitems().size(); i++) {
				ProductboxApItem item = deliveryOrder.getBoxitems().get(i);
				ProductBox box = boxService.getById(item.getBox().getId());
				double piececontent = 0;
				if(box.getPiececontent()!=null){
					piececontent = box.getPiececontent();
				}
				item.getBox().setPiececontent(piececontent);
				List<String> values = new ArrayList<String>();
				values.add(item.getBox().getCode());
				values.add(item.getBox().getName());
				values.add(item.getBox().getSpecification());
				values.add(item.getBox().getPiececount().toString());
				values.add(item.getCount().toString());
				int jz = (int)(item.getBox().getPiececontent()*item.getCount());			
				values.add(jz+"");
				int zz = 0;
				if(item.getKg()!=null){
					zz = (int)(item.getKg()*item.getCount());
				}
				values.add(zz+"");
				boxs.add(values);
				totalkg+=item.getKg()*item.getCount();
				count+=item.getCount();
				sum+=item.getTotal();
			}
			List<String> values = new ArrayList<String>();
			values.add("合计");
			values.add("");
			values.add("");
			values.add("");
			values.add(String.valueOf(count));
			values.add("");
			values.add(String.valueOf((int)totalkg));
			boxs.add(values);
		 }		 
		 if(deliveryOrder.getPackitems()!=null && deliveryOrder.getPackitems().size()>0){
			 for (Iterator iterator = deliveryOrder.getPackitems().iterator(); iterator.hasNext();) {
				ProductPackApItem item = (ProductPackApItem) iterator.next();
				packs+=item.getPack().getName()+item.getCount()+"  ";
			}
		 }
		 if(deliveryOrder.getSampleitems()!=null && deliveryOrder.getSampleitems().size()>0){
			 pages+="附带样品：";
			 for (Iterator iterator = deliveryOrder.getSampleitems().iterator(); iterator.hasNext();) {
				SampleApItem item = (SampleApItem) iterator.next();
				pages+=item.getBox().getName()+"(规格"+item.getBox().getSpecification()+") 数量："+item.getCount()+"  ";
			}
			 pages+="。";
		 }
		 if(deliveryOrder.getPageitems()!=null && deliveryOrder.getPageitems().size()>0){
			 pages+="附带宣传物料";
			 for (Iterator iterator = deliveryOrder.getPageitems().iterator(); iterator.hasNext();) {
				ProductPageApItem item = (ProductPageApItem) iterator.next();
				pages+=item.getPage().getName()+item.getCount()+"  ";
			}
			 pages+="。";
		 }
		 
		 param.put("${pages}", pages);
		 param.put("${packs}", "附带包装材料"+packs+"。");
		 
		 CustomXWPFDocument doc = WordUtil.generateWord(param, PublicConfig.getTemplateUrl(),boxs);  
		 HttpSession session = request.getSession();
	     session.setAttribute("state", null);
	     // 生成提示信息，
		 OutputStream fOut = null;
		 try {
			response.setContentType("application/msword");
			String codedFileName = null;
			fOut = response.getOutputStream();
			codedFileName = java.net.URLEncoder.encode("送货单-" + deliveryOrder.getId(), "UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".docx");
			doc.write(fOut);
			fOut.close();
		} catch (UnsupportedEncodingException e1)
	        {}
	        catch (Exception e)
	        {}
	        finally
	        {
	            try
	            {
	                fOut.flush();
	                fOut.close();
	            }
	            catch (IOException e)
	            {}
	            session.setAttribute("state", "open");
	        } 
	}
	
	@RequestMapping(value = "/sendapplies/applydown",method=RequestMethod.GET)
	public void applydown(Model model,@RequestParam(required = true, defaultValue = "0") int id,
			HttpServletRequest request,HttpServletResponse response) {
		ApplyForSend applyForSend = applyForSendService.getById(id);
		Dealer dealer = dealerService.getById(applyForSend.getDealer().getId());
		
		 Map<String, Object> param = new HashMap<String, Object>(); 
		 param.put("dealername", dealer.getName()==null?"":dealer.getName());
		 param.put("gongsi", dealer.getCompany()==null?"":dealer.getCompany());
		 param.put("address", applyForSend.getAddress()==null?"":applyForSend.getAddress());
		 param.put("contact", applyForSend.getContact()==null?"":applyForSend.getContact());
		 param.put("phone", applyForSend.getPhone()==null?"":applyForSend.getPhone());
		 double totalkg=0;
		 int count=0;
		 double sum=0;
		 List<List> cellvalues = new ArrayList<List>();
		 if(applyForSend.getBoxitems()!=null && applyForSend.getBoxitems().size()>0){
			 List<String> boxtitles = new ArrayList<String>();
			 boxtitles.add("产品");
			 cellvalues.add(boxtitles);
			 List<String> celltitles = new ArrayList<String>();
			 celltitles.add("名称");
			 celltitles.add("价格");
			 celltitles.add("数量");
			 celltitles.add("毛重(kg)");
			 celltitles.add("金额");
			 cellvalues.add(celltitles);
			
			for (int i = 0; i < applyForSend.getBoxitems().size(); i++) {
				ProductboxApItem item = applyForSend.getBoxitems().get(i);
				if(item!=null && item.getBox()!=null){
					List<String> values = new ArrayList<String>();
					values.add(item.getBox().getName());
					values.add(item.getPrice().toString());
					values.add(item.getCount().toString());
					values.add(item.getKg().toString());
					values.add(item.getTotal().toString());
					cellvalues.add(values);
					totalkg+=item.getKg()*item.getCount();
					count+=item.getCount();
					sum+=item.getTotal();
				}				
			}	
		 }		 
		 if(applyForSend.getPackitems()!=null && applyForSend.getPackitems().size()>0){
			 List<String> boxtitles = new ArrayList<String>();
			 boxtitles.add("附带包材");
			 cellvalues.add(boxtitles);
			 List<String> celltitles = new ArrayList<String>();
			 celltitles.add("名称");
			 celltitles.add("数量");
			 cellvalues.add(celltitles);
			 for (Iterator iterator = applyForSend.getPackitems().iterator(); iterator.hasNext();) {
				ProductPackApItem item = (ProductPackApItem) iterator.next();
				if(item!=null && item.getPack()!=null){
					List<String> values = new ArrayList<String>();
					values.add(item.getPack().getName());
					values.add(item.getCount().toString());
					cellvalues.add(values);
				}
			}
		 }
		 if(applyForSend.getPageitems()!=null && applyForSend.getPageitems().size()>0){
			 List<String> boxtitles = new ArrayList<String>();
			 boxtitles.add("附带宣传品");
			 cellvalues.add(boxtitles);
			 List<String> celltitles = new ArrayList<String>();
			 celltitles.add("名称");
			 celltitles.add("数量");
			 cellvalues.add(celltitles);
			 for (Iterator iterator = applyForSend.getPageitems().iterator(); iterator.hasNext();) {
				ProductPageApItem item = (ProductPageApItem) iterator.next();
				if(item!=null && item.getPage()!=null){
					List<String> values = new ArrayList<String>();
					values.add(item.getPage().getName());
					values.add(item.getCount().toString());
					cellvalues.add(values);
				}				
			}
		 }
		 
		 List<String> values = new ArrayList<String>();
		 values.add("合计");
		 values.add(String.valueOf(sum));
		 cellvalues.add(values);
		 values = new ArrayList<String>();
		 values.add("合计毛重(kg)");
		 values.add(String.valueOf(totalkg));
		 cellvalues.add(values);
		 values = new ArrayList<String>();
		 values.add("申请时间");
		 values.add(DateTools.FormateDate(applyForSend.getDate()));
		 cellvalues.add(values);
		 values = new ArrayList<String>();
		 values.add("申请人");
		 values.add(applyForSend.getEmployee().getFullname());
		 cellvalues.add(values);
		 values = new ArrayList<String>();
		 values.add("状态");
		 values.add(applyForSend.getOpstatus()==0?"未发货":"已发货");
		 cellvalues.add(values);
		 
		 CustomXWPFDocument doc = WordUtil.generateWord1(param, PublicConfig.getSendTemplate(),cellvalues);  
		 HttpSession session = request.getSession();
	     session.setAttribute("state", null);
	     // 生成提示信息，
		 OutputStream fOut = null;
		 try {
			response.setContentType("application/msword");
			String codedFileName = null;
			fOut = response.getOutputStream();
			codedFileName = java.net.URLEncoder.encode("发货申请单-" + applyForSend.getId(), "UTF-8");
			response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".docx");
			doc.write(fOut);
			fOut.close();
		} catch (UnsupportedEncodingException e1)
	        {}
	        catch (Exception e)
	        {}
	        finally
	        {
	            try
	            {
	                fOut.flush();
	                fOut.close();
	            }
	            catch (IOException e)
	            {}
	            session.setAttribute("state", "open");
	        } 
	}
	
}
