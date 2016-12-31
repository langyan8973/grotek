package com.grotek.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.dto.TravelRbsmDto;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeGasoline;
import com.grotek.pojo.ExsRbsm;
import com.grotek.pojo.ExsRbsmItem;
import com.grotek.pojo.JobtypeDic;
import com.grotek.pojo.TravelRbsm;
import com.grotek.pojo.TravelRbsmItem;
import com.grotek.pojo.UsedCar;
import com.grotek.service.DealerService;
import com.grotek.service.EmployeeService;
import com.grotek.service.ExsRbsmService;
import com.grotek.service.JobTypeService;
import com.grotek.service.TravelRbsmService;
import com.grotek.service.UsedCarService;
import com.grotek.service.UserContext;
import com.grotek.util.DateTools;
import com.grotek.util.PublicHelper;

@Controller
@RequestMapping("/work")
public class ExpensesController {

	@Autowired
	private TravelRbsmService travelRbsmService;
	
	@Autowired
	private ExsRbsmService exsService;
	
	@Autowired
	private JobTypeService jobtypeService;
	
	@Autowired
	private UserContext userContext;
	
	@Autowired
	private EmployeeService emService;
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private UsedCarService usedcarService;
	
	@RequestMapping(value = "/expenses/travels",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		Employee employee = userContext.getCurrentUser();
		List<TravelRbsmDto> rbsms = null;
		int sumcount = 0;
		List<TravelRbsm> travelRbsms = travelRbsmService.getByEid(employee.getId(),  new PageRequest(page, size));
		if(travelRbsms!=null && travelRbsms.size()>0){
			rbsms = new ArrayList<TravelRbsmDto>();
			for (Iterator iterator = travelRbsms.iterator(); iterator.hasNext();) {
				TravelRbsm travelRbsm = (TravelRbsm) iterator.next();
				TravelRbsmDto dto = new TravelRbsmDto(travelRbsm);
				rbsms.add(dto);
			}
		}
		sumcount = travelRbsmService.allCountByEid(employee.getId());
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		return "expenses/travels";
	}
	
	@RequestMapping(value = "/expenses/newtravel",method=RequestMethod.GET)
	public String newProjectWork(Model model){
		List<JobtypeDic> types = jobtypeService.getAll();
		model.addAttribute("types", types);
		
		return "expenses/newtravel";
	}
	
	@RequestMapping(value = "/expenses/addtravel", method = RequestMethod.POST)
	public String addOne(@ModelAttribute("travelrbsm") TravelRbsm travelrbsm, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		Employee employee = userContext.getCurrentUser();
		employee = emService.getById(employee.getId());
		EmployeeGasoline gasoline = emService.getGasolineByEid(employee.getId());
		if(gasoline==null){
			redirectAttributes.addFlashAttribute("message", "请联系管理员设置员工的每公里汽油费数值");
			return "redirect:travels";
		}
		double sum=0.0;

		if(travelrbsm!=null){
			if((travelrbsm.getItems()==null || travelrbsm.getItems().size()==0)&&
					(travelrbsm.getUsedCars()==null || travelrbsm.getUsedCars().size()==0)){
				return "expenses/newtravel";
			}
			travelrbsm.setEmployee(employee);
			if(travelrbsm.getItems()!=null && travelrbsm.getItems().size()>0){
				for (Iterator iterator = travelrbsm.getItems().iterator(); iterator.hasNext();) {
					TravelRbsmItem item = (TravelRbsmItem) iterator.next();
					if(item.getAmount()!=null){					
						sum+=item.getAmount();
					}
				}
			}
			if(travelrbsm.getUsedCars()!=null && travelrbsm.getUsedCars().size()>0){
				for (Iterator iterator = travelrbsm.getUsedCars().iterator(); iterator.hasNext();) {
					UsedCar usedCar = (UsedCar) iterator.next();
					if(usedCar.getKmcount()==null){
						continue;
					}
					usedCar.setChecked(0);
					usedCar.setDealer(travelrbsm.getDealer());
					usedCar.setEmployee(employee);
					if(usedCar.getKmcount()!=null){
						usedCar.setGasoline(gasoline.getExs()*usedCar.getKmcount());
						sum+=usedCar.getGasoline();
					}					
					usedCar.setGasolineunit(gasoline.getExs());
					usedCar.setStatus(0);
					usedCar.setType(travelrbsm.getType());					
				}
			}
			travelrbsm.setSum(PublicHelper.correctTo(sum));
			travelrbsm.setDate(new Date());
			travelRbsmService.createTravelRbsm(travelrbsm);
			if(travelrbsm.getItems()!=null && travelrbsm.getItems().size()>0){
				for (Iterator iterator = travelrbsm.getItems().iterator(); iterator.hasNext();) {
					TravelRbsmItem item = (TravelRbsmItem) iterator.next();
					item.setEid(employee.getId());
					item.setPid(travelrbsm.getId());
					travelRbsmService.addTravelRbsmItem(item);
				}
			}
			if(travelrbsm.getUsedCars()!=null && travelrbsm.getUsedCars().size()>0){
				for (Iterator iterator = travelrbsm.getUsedCars().iterator(); iterator.hasNext();) {
					UsedCar usedCar = (UsedCar) iterator.next();
					if(usedCar.getKmcount()==null){
						continue;
					}
					usedCar.setTiid(travelrbsm.getId());	
					usedcarService.addUsedCar(usedCar);
				}
			}
			redirectAttributes.addFlashAttribute("success", "编辑成功");
			return "redirect:travels";
		}
		else{
			return "expenses/newtravel";
		}
		
	}
	
	@RequestMapping(value = "/expenses/travelfile",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = true, defaultValue = "0") int id){
		TravelRbsm travelRbsm = travelRbsmService.getByid(id);
		model.addAttribute("rbsm", travelRbsm);
		return "expenses/travelfile";
	}
	
	@RequestMapping(value = "/expenses/edittravel",method=RequestMethod.GET)
	public String edittravel(Model model, @RequestParam(required = true, defaultValue = "0") int id){
		TravelRbsm travelRbsm = travelRbsmService.getByid(id);
		model.addAttribute("rbsm", travelRbsm);
		List<JobtypeDic> types = jobtypeService.getAll();
		model.addAttribute("types", types);
		return "expenses/edittravel";
	}
	
	@RequestMapping(value = "/expenses/updatetravel", method = RequestMethod.POST)
	public String updatetravel(@ModelAttribute("travelrbsm") TravelRbsm travelrbsm, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		Employee employee = userContext.getCurrentUser();
		employee = emService.getById(employee.getId());
		EmployeeGasoline gasoline = emService.getGasolineByEid(employee.getId());
		double sum=0.0;

		if(travelrbsm!=null){
			if((travelrbsm.getItems()==null || travelrbsm.getItems().size()==0)&&
					(travelrbsm.getUsedCars()==null || travelrbsm.getUsedCars().size()==0)){
				return "expenses/edittravel";
			}
			
			TravelRbsm rbsm = travelRbsmService.getByid(travelrbsm.getId());
			if(rbsm.getItems()!=null && rbsm.getItems().size()>0){
				for (Iterator iterator = rbsm.getItems().iterator(); iterator.hasNext();) {
					TravelRbsmItem ite = (TravelRbsmItem) iterator.next();
					travelRbsmService.deleteTravelRbsmItem(ite.getId());					
				}
			}
			if(rbsm.getUsedCars()!=null && rbsm.getUsedCars().size()>0){
				for (Iterator iterator = rbsm.getUsedCars().iterator(); iterator.hasNext();) {
					UsedCar usedCar = (UsedCar) iterator.next();
					usedcarService.deleteOne(usedCar.getId());
				}
			}
			travelrbsm.setEmployee(employee);
			if(travelrbsm.getItems()!=null && travelrbsm.getItems().size()>0){
				for (Iterator iterator = travelrbsm.getItems().iterator(); iterator.hasNext();) {
					TravelRbsmItem item = (TravelRbsmItem) iterator.next();
					if(item.getAmount()!=null){					
						sum+=item.getAmount();
						item.setEid(employee.getId());
						item.setPid(travelrbsm.getId());
						travelRbsmService.addTravelRbsmItem(item);
					}
				}
			}
			if(travelrbsm.getUsedCars()!=null && travelrbsm.getUsedCars().size()>0){
				for (Iterator iterator = travelrbsm.getUsedCars().iterator(); iterator.hasNext();) {
					UsedCar usedCar = (UsedCar) iterator.next();
					if(usedCar.getKmcount()==null){
						continue;
					}
					usedCar.setChecked(0);
					usedCar.setDealer(travelrbsm.getDealer());
					usedCar.setEmployee(employee);
					if(usedCar.getKmcount()!=null){
						usedCar.setGasoline(gasoline.getExs()*usedCar.getKmcount());
						sum+=usedCar.getGasoline();
					}					
					usedCar.setGasolineunit(gasoline.getExs());
					usedCar.setStatus(0);
					usedCar.setType(travelrbsm.getType());
					usedCar.setTiid(travelrbsm.getId());	
					usedcarService.addUsedCar(usedCar);
				}
			}

			travelrbsm.setSum(PublicHelper.correctTo(sum));
			travelrbsm.setDate(new Date());
			travelrbsm.setRemark("");
			travelrbsm.setChecked(0);
			travelrbsm.setPaystatus(0);
			travelrbsm.setStatus(0);
			travelRbsmService.editTravelRbsm(travelrbsm);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
			return "redirect:travels";
		}
		else{
			return "expenses/edittravel";
		}
		
	}	
	
	@RequestMapping(value = "/expenses/deletetravel", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) Integer id) {
		travelRbsmService.deleteTravelRbsm(id);
		return "true";
	}
	
	@RequestMapping(value = "/expenses/exses",method=RequestMethod.GET)
	public String exses(Model model, @RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "4") int size){
		Employee employee = userContext.getCurrentUser();
		List<ExsRbsm> rbsms = null;
		int sumcount = 0;
		rbsms = exsService.getByEid(employee.getId(),  new PageRequest(page, size));
		sumcount = exsService.allCountByEid(employee.getId());
		model.addAttribute("rbsms", rbsms);
		model.addAttribute("sumcount", sumcount);
		return "expenses/exses";
	}
	
	@RequestMapping(value = "/expenses/newexs",method=RequestMethod.GET)
	public String newexs(Model model){
		List<JobtypeDic> types = jobtypeService.getAll();
		model.addAttribute("types", types);
		
		return "expenses/newexs";
	}
	
	@RequestMapping(value = "/expenses/addexs", method = RequestMethod.POST)
	public String addExs(@ModelAttribute("exsrbsm") ExsRbsm exsRbsm, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		
		Employee employee = userContext.getCurrentUser();
		employee = emService.getById(employee.getId());
		
		double sum=0.0;

		if(exsRbsm!=null){
			if(exsRbsm.getItems()==null || exsRbsm.getItems().size()==0){
				return "expenses/newexs";
			}
			exsRbsm.setEmployee(employee);
			if(exsRbsm.getItems()!=null && exsRbsm.getItems().size()>0){
				for (Iterator iterator = exsRbsm.getItems().iterator(); iterator.hasNext();) {
					ExsRbsmItem item = (ExsRbsmItem) iterator.next();
					if(item.getAmount()!=null){					
						sum+=item.getAmount();
					}
				}
			}

			exsRbsm.setSum(PublicHelper.correctTo(sum));
			exsRbsm.setDate(new Date());
			exsRbsm.setChecked(0);
			exsRbsm.setPaystatus(0);
			exsRbsm.setStatus(0);
			exsService.createExsRbsm(exsRbsm);
			if(exsRbsm.getItems()!=null && exsRbsm.getItems().size()>0){
				for (Iterator iterator = exsRbsm.getItems().iterator(); iterator.hasNext();) {
					ExsRbsmItem item = (ExsRbsmItem) iterator.next();
					item.setEid(employee.getId());
					item.setPid(exsRbsm.getId());
					exsService.addExsRbsmItem(item);
				}
			}
			redirectAttributes.addFlashAttribute("success", "编辑成功");
			return "redirect:exses";
		}
		else{
			return "expenses/newexs";
		}
	}
	
	@RequestMapping(value = "/expenses/exsfile",method=RequestMethod.GET)
	public String exsfile(Model model, @RequestParam(required = true, defaultValue = "0") int id){
		ExsRbsm exsRbsm = exsService.getByid(id);
		model.addAttribute("rbsm", exsRbsm);
		return "expenses/exsfile";
	}
	
	@RequestMapping(value = "/expenses/editexs",method=RequestMethod.GET)
	public String editexs(Model model, @RequestParam(required = true, defaultValue = "0") int id){
		ExsRbsm exsRbsm = exsService.getByid(id);
		model.addAttribute("rbsm", exsRbsm);
		List<JobtypeDic> types = jobtypeService.getAll();
		model.addAttribute("types", types);
		return "expenses/editexs";
	}
	
	@RequestMapping(value = "/expenses/editexsitem", method = RequestMethod.POST)
	public String editexsitem(@ModelAttribute("exsrbsm") ExsRbsm exsRbsm, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		
		Employee employee = userContext.getCurrentUser();
		employee = emService.getById(employee.getId());
		EmployeeGasoline gasoline = emService.getGasolineByEid(employee.getId());
		double sum=0.0;

		if(exsRbsm!=null){
			if(exsRbsm.getItems()==null || exsRbsm.getItems().size()==0){
				return "expenses/editexs";
			}
			
			ExsRbsm rbsm = exsService.getByid(exsRbsm.getId());
			if(rbsm.getItems()!=null && rbsm.getItems().size()>0){
				for (Iterator iterator = rbsm.getItems().iterator(); iterator.hasNext();) {
					ExsRbsmItem ite = (ExsRbsmItem) iterator.next();
					exsService.deleteExsRbsmItem(ite.getId());					
				}
			}
			exsRbsm.setEmployee(employee);
			if(exsRbsm.getItems()!=null && exsRbsm.getItems().size()>0){
				for (Iterator iterator = exsRbsm.getItems().iterator(); iterator.hasNext();) {
					ExsRbsmItem item = (ExsRbsmItem) iterator.next();
					if(item.getAmount()!=null){					
						sum+=item.getAmount();
						item.setEid(employee.getId());
						item.setPid(exsRbsm.getId());
						exsService.addExsRbsmItem(item);
					}
				}
			}
			
			exsRbsm.setSum(PublicHelper.correctTo(sum));
			exsRbsm.setDate(new Date());
			exsRbsm.setDes("");
			exsRbsm.setChecked(0);
			exsRbsm.setPaystatus(0);
			exsRbsm.setStatus(0);
			exsService.editExsRbsm(exsRbsm);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
			return "redirect:exses";
		}
		else{
			return "expenses/editexs";
		}
		
	}
	
	@RequestMapping(value = "/expenses/deleteexs", method = RequestMethod.POST)
	@ResponseBody
	public String deleteexs(@RequestParam(required = true) Integer id) {
		exsService.deleteExsRbsm(id);
		return "true";
	}
}
