package com.grotek.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.PageRequest;

import com.grotek.dto.SuperiorsNote;
import com.grotek.pojo.DepartmentDic;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeGasoline;
import com.grotek.pojo.EmployeeProm;
import com.grotek.pojo.EmployeeReward;
import com.grotek.pojo.PositionDic;
import com.grotek.service.DepartmentService;
import com.grotek.service.EmployeeService;
import com.grotek.service.PositionService;
import com.grotek.service.PromoteService;
import com.grotek.service.RewardService;
import com.grotek.util.ExcelUtil;
import com.grotek.util.PublicConfig;
import com.grotek.util.PublicHelper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/manager")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired 
	private DepartmentService deptServie;
	@Autowired
	private PositionService positionService;
	@Autowired
	private RewardService rewardService;
	@Autowired
	private PromoteService promoteService;
	
	@RequestMapping(value = "/employees/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<Employee> employees = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			employees = employeeService.findEmployees(context, new PageRequest(page,size));
			sumcount = employeeService.searchCount(context);
		} else {
			employees = employeeService.getEmployees(new PageRequest(page, size));
			sumcount = employeeService.allCount();
		}
		model.addAttribute("imageUrl", PublicConfig.getImageUrl() + "employees/small");
		model.addAttribute("employees", employees);
		model.addAttribute("sumcount", sumcount);
		return "employees/index";
	}
	
	@RequestMapping(value = "/employees/new",method=RequestMethod.GET)
	public String newEmployee(Model model){
		List<PositionDic> positionDics = positionService.getDicstAllPositions();
		List<DepartmentDic> departments = deptServie.getAllDepartments();
		model.addAttribute("imageUrl", PublicConfig.getImageUrl() + "employees/small");
		model.addAttribute("positions", positionDics);
		model.addAttribute("departments", departments);
		return "employees/new";
	}
	
	@RequestMapping(value = "/employees/superiors",method=RequestMethod.GET)
	public String superiors(Model model){
		
		List<SuperiorsNote> superiorsNotes = employeeService.getSuperiors(new PageRequest(0, 10000));
		
		model.addAttribute("superiors", superiorsNotes);
		return "employees/superiors";
	}
	
	@RequestMapping(value = "/employees/edit",method=RequestMethod.GET)
	public String editEmployee(Model model,@RequestParam(required = true) String id){
		Employee employee = employeeService.getById(id);
		List<PositionDic> positionDics = positionService.getDicstAllPositions();
		List<DepartmentDic> departments = deptServie.getAllDepartments();
		model.addAttribute("employee", employee);
		model.addAttribute("imageUrl", PublicConfig.getImageUrl() + "employees/small");
		model.addAttribute("positions", positionDics);
		model.addAttribute("departments", departments);
		return "employees/edit";
	}
	
	@RequestMapping(value = "/employees/change",method=RequestMethod.GET)
	public String changeEmployee(Model model,@RequestParam(required = true) String id,
			@RequestParam(required = false) String prom){
		Employee employee = employeeService.getById(id);
		List<PositionDic> positionDics = positionService.getDicstAllPositions();
		List<DepartmentDic> departments = deptServie.getAllDepartments();
		model.addAttribute("employee", employee);
		model.addAttribute("positions", positionDics);
		model.addAttribute("departments", departments);
		model.addAttribute("prom", prom);
		return "employees/change";
	}
	
	@RequestMapping(value = "/employees/reward",method=RequestMethod.GET)
	public String rewardEmployee(Model model,@RequestParam(required = true) String id,
			@RequestParam(required = false) String reward){
		Employee employee = employeeService.getById(id);
		model.addAttribute("employee", employee);
		model.addAttribute("reward", reward);
		return "employees/reward";
	}
	
	@RequestMapping(value = "/employees/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String fullname = multipartRequest.getParameter("fullname");
		String code = multipartRequest.getParameter("code");
		String sex = multipartRequest.getParameter("sex");
		String address = multipartRequest.getParameter("address");
		String mobile = multipartRequest.getParameter("mobile");
		String postcode = multipartRequest.getParameter("postcode");
		String phone = multipartRequest.getParameter("phone");
		String position = multipartRequest.getParameter("position");
		String dept = multipartRequest.getParameter("dept");
		String headimg = multipartRequest.getParameter("headimg");
		String regform = multipartRequest.getParameter("regform");
		String superior = multipartRequest.getParameter("superior");
		String sname = multipartRequest.getParameter("sname");
		if(employeeService.check(code)>0){
			redirectAttributes.addFlashAttribute("message", "编码重复");
		}
		else {
			Employee employee = new Employee();
			employee.setFullname(fullname);
			employee.setCode(code);
			employee.setSex(sex);
			employee.setAddress(address);
			employee.setMobile(mobile);
			employee.setPostcode(postcode);
			employee.setPhone(phone);
			PositionDic positionDic = new PositionDic();
			positionDic.setId(Integer.parseInt(position));
			employee.setPosition(positionDic);
			DepartmentDic departmentDic = new DepartmentDic();
			departmentDic.setId(Integer.parseInt(dept));
			employee.setDept(departmentDic);
			employee.setHeadimg(headimg);
			employee.setRegform(regform);
			employee.setSuperior(superior);
			employee.setSname(sname);
			employeeService.addEmployee(employee);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/employees/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String fullname = multipartRequest.getParameter("fullname");
		String code = multipartRequest.getParameter("code");
		String sex = multipartRequest.getParameter("sex");
		String address = multipartRequest.getParameter("address");
		String mobile = multipartRequest.getParameter("mobile");
		String postcode = multipartRequest.getParameter("postcode");
		String phone = multipartRequest.getParameter("phone");
		String headimg = multipartRequest.getParameter("headimg");
		String regform = multipartRequest.getParameter("regform");
		String id = multipartRequest.getParameter("id");
		Employee employee = employeeService.getById(id);
		if(!employee.getCode().equals(code))
		{
			if(employeeService.check(code)>0){
				redirectAttributes.addFlashAttribute("message", "编码重复");
			}
			else{
				employee.setFullname(fullname);
				employee.setCode(code);
				employee.setSex(sex);
				employee.setAddress(address);
				employee.setMobile(mobile);
				employee.setPostcode(postcode);
				employee.setPhone(phone);
				employee.setHeadimg(headimg);
				employee.setRegform(regform);
				employeeService.editEmployee(employee);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}
		else{
			employee.setFullname(fullname);
			employee.setCode(code);
			employee.setSex(sex);
			employee.setAddress(address);
			employee.setMobile(mobile);
			employee.setPostcode(postcode);
			employee.setPhone(phone);
			employee.setHeadimg(headimg);
			employee.setRegform(regform);
			employeeService.editEmployee(employee);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		
		return "redirect:profile?id="+id;
	}
	
	@RequestMapping(value = "/employees/resetpass", method = RequestMethod.POST)
	public String resetpass(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String eid = multipartRequest.getParameter("eid");
		String pass = multipartRequest.getParameter("pass");
		String cpass = multipartRequest.getParameter("cpass");
		
		Employee employee = employeeService.getById(eid);
		if(!pass.equals(cpass)){
			redirectAttributes.addFlashAttribute("message", "密码输入不一致");
		}
		else{
			employee.setPassword(pass);
			employeeService.editEmployee(employee);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		
		return "redirect:profile?id="+eid;
	}
	
	
	@RequestMapping(value = "/employees/changeone", method = RequestMethod.POST)
	public String changeOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		String position = multipartRequest.getParameter("position");
		String dept = multipartRequest.getParameter("dept");
		String superior = multipartRequest.getParameter("superior");
		String sname = multipartRequest.getParameter("sname");
		String id = multipartRequest.getParameter("id");
		String description = multipartRequest.getParameter("description");
		String prom = multipartRequest.getParameter("prom");
		Employee employee = employeeService.getById(id);
		
		PositionDic positionDic = new PositionDic();
		positionDic.setId(Integer.parseInt(position));
		employee.setPosition(positionDic);
		DepartmentDic departmentDic = new DepartmentDic();
		departmentDic.setId(Integer.parseInt(dept));
		employee.setDept(departmentDic);
		employee.setSuperior(superior);
		employee.setSname(sname);
		employeeService.editEmployee(employee);
		promoteService.addProm(employee, description);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		
		if(StringUtils.isNotBlank(prom)){
			return "redirect:/manager/promote/index";
		}
		else{
			return "redirect:profile?id="+id;
		}
	}
	
	@RequestMapping(value = "/employees/rewardone", method = RequestMethod.POST)
	public String rewardOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		String item = multipartRequest.getParameter("item");
		String amount = multipartRequest.getParameter("amount");
		String id = multipartRequest.getParameter("id");
		String description = multipartRequest.getParameter("description");
		String reward = multipartRequest.getParameter("reward");
		Employee employee = employeeService.getById(id);
		
		employeeService.editEmployee(employee);
		rewardService.addReward(employee, item, Double.parseDouble(amount), description);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		if(StringUtils.isNotBlank(reward)){
			return "redirect:/manager/rewards/index";
		}
		else{
			return "redirect:profile?id="+id;
		}		
	}
	
	@RequestMapping(value = "/employees/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true) String id){
		Employee employee = employeeService.getById(id);
		List<EmployeeProm> proms = promoteService.getEmployeePromsByEid(id);
		List<EmployeeReward> rewards = rewardService.getRewardsByEid(id);
		EmployeeGasoline gasoline = employeeService.getGasolineByEid(id);
		model.addAttribute("employee", employee);
		model.addAttribute("proms", proms);
		model.addAttribute("rewards", rewards);
		model.addAttribute("gasoline", gasoline);
		model.addAttribute("simageUrl", PublicConfig.getImageUrl() + "employees/small");
		model.addAttribute("bimageUrl", PublicConfig.getImageUrl() + "employees/big");		
		return "employees/profile";
	}
	
	@RequestMapping(value = "/employees/bash", method = RequestMethod.POST)
	@ResponseBody
	public String secondbashImport(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException, ServletException {
		String imageName = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mFile = entity.getValue();
			String imagename = mFile.getOriginalFilename();
			String format = imagename.substring(imagename.lastIndexOf(".") + 1);
			imageName = PublicHelper.saveImage1(mFile.getInputStream(), format, "employees");
		}
		return imageName;
	}
	
	@RequestMapping(value = "/employees/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) String id) {
		employeeService.delete(id);
		return "true";
	}
	
	@RequestMapping(value = "/employees/deleteprom", method = RequestMethod.POST)
	@ResponseBody
	public String deleteprom(@RequestParam(required = true) int id) {
		promoteService.delete(id);
		return "true";
	}
	
	@RequestMapping(value = "/employees/deletereward", method = RequestMethod.POST)
	@ResponseBody
	public String deletereward(@RequestParam(required = true) int id) {
		rewardService.delete(id);
		return "true";
	}
	
	@RequestMapping(value = "/employees/editgaso", method = RequestMethod.POST)
	public String editgaso(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		
		String exs = multipartRequest.getParameter("exs");
		String gid = multipartRequest.getParameter("gid");
		String eid = multipartRequest.getParameter("eid");
		Employee employee = employeeService.getById(eid);
		EmployeeGasoline gasoline = employeeService.getGasolineByEid(eid);
		if(gasoline!=null){
			employeeService.deleteGasoline(gasoline.getId());
		}
		gasoline = new EmployeeGasoline();
		gasoline.setExs(Double.parseDouble(exs));
		gasoline.setEmployee(employee);
		gasoline.setDate(new Date());
		employeeService.addGasoline(gasoline);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+eid;		
	}
	
	@RequestMapping("/employees/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)
    {
		List<Integer> widths = new ArrayList<Integer>();
		List<List> datas = new ArrayList<List>();
		List<Employee> employees = employeeService.getEmployees(new PageRequest(0, 10000));
		List<String> titles = new ArrayList<String>();
		titles.add("序号");
		widths.add(2000);
		titles.add("姓名");
		widths.add(2000);
		titles.add("编码");
		widths.add(2000);
		titles.add("性别");
		widths.add(2000);
		titles.add("地址");
		widths.add(20000);
		titles.add("手机");
		widths.add(10000);
		titles.add("邮编");
		widths.add(3000);
		titles.add("座机");
		widths.add(10000);
		titles.add("领导");
		widths.add(2000);
		titles.add("部门");
		widths.add(3000);
		titles.add("职位");
		widths.add(3000);
		datas.add(titles);
		if(employees!=null && employees.size()>0){
			for (int i=0;i<employees.size();i++) {
				Employee employee = employees.get(i);
				List<String> values = new ArrayList<String>();
				values.add((i+1)+"");
				values.add(employee.getFullname()==null?"":employee.getFullname());
				values.add(employee.getCode()==null?"":employee.getCode());
				values.add(employee.getSex()==null?"":employee.getSex());
				values.add(employee.getAddress()==null?"":employee.getAddress());
				values.add(employee.getMobile()==null?"":employee.getMobile());
				values.add(employee.getPostcode()==null?"":employee.getPostcode());
				values.add(employee.getPhone()==null?"":employee.getPhone());
				values.add(employee.getSname()==null?"":employee.getSname());
				values.add(employee.getDept()==null?"":employee.getDept().getName());
				values.add(employee.getPosition()==null?"":employee.getPosition().getName());
				datas.add(values);
			}
		}
        HttpSession session = request.getSession();
        session.setAttribute("state", null);
        // 生成提示信息，
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
            codedFileName = java.net.URLEncoder.encode("员工表", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = ExcelUtil.exportExcel(datas, widths, "所有员工",null);
            fOut = response.getOutputStream();
            workbook.write(fOut);
        }
        catch (UnsupportedEncodingException e1)
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
