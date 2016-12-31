package com.grotek.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grotek.dto.IncomeDto;
import com.grotek.dto.LaborHourDto;
import com.grotek.dto.SalesDto;
import com.grotek.dto.TotalCostDto;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.Employee;
import com.grotek.pojo.Weekly;
import com.grotek.pojo.WeeklyItem;
import com.grotek.service.ApplyForSendService;
import com.grotek.service.DealerService;
import com.grotek.service.EmployeeService;
import com.grotek.service.MonthlyIncomeService;
import com.grotek.service.ProductLaborTimeService;
import com.grotek.service.WeekliesService;
import com.grotek.util.DateTools;
import com.grotek.util.ExcelUtil;

@Controller
@RequestMapping("/manager")
public class StaticController {
	
	@Autowired
	private EmployeeService emService;
	
	@Autowired
	private WeekliesService weeklyService;
	
	@Autowired
	private ApplyForSendService sendService;
	
	@Autowired
	private MonthlyIncomeService monthlyincomeService;
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private ProductLaborTimeService labortimesService;
	
	@RequestMapping(value = "/statics/laborhours", method = RequestMethod.GET)
	public String laborhours(Model model,@RequestParam(required = true) String eid) {
		Employee employee = emService.getById(eid);
		model.addAttribute("employee", employee);
		return "statics/laborhours";
	}

	@RequestMapping(value = "/statics/laborhours.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> laborhoursjson(LaborHourDto dto) {
		List<List> result = weeklyService.staticLaborHours(dto.getEid(),dto.getStarttime(), dto.getEndtime());
		return result;
	}
	
	@RequestMapping(value = "/statics/sales", method = RequestMethod.GET)
	public String sales(Model model,@RequestParam(required = true) String eid) {
		Employee employee = emService.getById(eid);
		model.addAttribute("employee", employee);
		return "statics/sales";
	}
	
	@RequestMapping(value = "/statics/sales.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> salesjson(SalesDto dto) {
		List<List> result = sendService.staticSales(dto.getEid(),dto.getStarttime(), dto.getEndtime());
		return result;
	}
	
	@RequestMapping(value = "/statics/totalincome", method = RequestMethod.GET)
	public String totalincome(Model model,@RequestParam(required = true) String eid) {
		Employee employee = emService.getById(eid);
		model.addAttribute("employee", employee);
		return "statics/totalincome";
	}
	
	@RequestMapping(value = "/statics/totalincome.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> incomejson(SalesDto dto) {
		
		List<List> result = monthlyincomeService.getByYear(dto.getEid(), dto.getStarttime(), dto.getEndtime());
		return result;
	}
	
	@RequestMapping(value = "/statics/service", method = RequestMethod.GET)
	public String service(Model model,@RequestParam(required = true) String eid) {
		Employee employee = emService.getById(eid);
		model.addAttribute("employee", employee);
		return "statics/service";
	}

	@RequestMapping(value = "/statics/service.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> servicejson(LaborHourDto dto) {
		Employee employee = emService.getById(dto.getEid());		
		List<List> result = weeklyService.staticDealerService(dto.getEid(), dto.getStarttime(), dto.getEndtime(), employee.getPosition().getCosthour());
		return result;
	}
	
	@RequestMapping(value = "/statics/totalcost", method = RequestMethod.GET)
	public String totalcost(Model model,@RequestParam(required = true) String eid) {
		Employee employee = emService.getById(eid);
		List<Dealer> dealers = dealerService.getDealersByEid(eid, new PageRequest(0, 1000));
		model.addAttribute("employee", employee);
		model.addAttribute("dealers", dealers);
		return "statics/totalcost";
	}
	
	@RequestMapping(value = "/statics/totalcost.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> totalcostJson(TotalCostDto dto) {
		Employee employee = emService.getById(dto.getEid());
		Dealer dealer = null;
		if(dto.getDid()!=0){
			dealer = dealerService.getById(dto.getDid());
		}
		List<List> result = emService.totalCost(employee, dto.getStarttime(), dto.getEndtime(), dealer);
		return result;
	}
	
	@RequestMapping(value = "/statics/works",method=RequestMethod.GET)
	public String index(Model model,@RequestParam(required = true) String eid,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "1") int size){
		List<Weekly> weeklies = null;
		Employee employee = emService.getById(eid);
		int sumcount = 0;
		Weekly weekly = null;
		List<String> shortdates = new ArrayList<String>();
		weeklies = weeklyService.allByEid(eid, new PageRequest(page, size));
		sumcount = weeklyService.checkedCount(eid);
		if(weeklies!=null && weeklies.size()>0){
			weekly = weeklies.get(0);
			shortdates.add(DateTools.FormateDateOnlyMonth(weekly.getStime()));
			for (int i = 1; i < 7; i++) {
				shortdates.add(DateTools.FormateDateOnlyMonth(DateTools.getNextday(weekly.getStime(), i)));
			}
			double mon=0,tue=0,wed=0,thu=0,fri=0,sat=0,sun=0,sum=0;
			if(weekly.getItems()!=null && weekly.getItems().size()>0){
				for (Iterator iterator = weekly.getItems().iterator(); iterator.hasNext();) {
					WeeklyItem item = (WeeklyItem) iterator.next();
					if(item.getMon()!=null)
						mon=mon+item.getMon();
					if(item.getTue()!=null)
						tue=tue+item.getTue();
					if(item.getWed()!=null)
						wed=wed+item.getWed();
					if(item.getThu()!=null)
						thu=thu+item.getThu();
					if(item.getFri()!=null)
						fri=fri+item.getFri();
					if(item.getSat()!=null)
						sat=sat+item.getSat();
					if(item.getSun()!=null)
						sun=sun+item.getSun();
					if(item.getTotal()!=null)
						sum = sum+item.getTotal();
				}
				WeeklyItem weeklyItem = new WeeklyItem();
				weeklyItem.setContent("合计");
				weeklyItem.setMon(mon);
				weeklyItem.setTue(tue);
				weeklyItem.setWed(wed);
				weeklyItem.setThu(thu);
				weeklyItem.setFri(fri);
				weeklyItem.setSat(sat);
				weeklyItem.setSun(sun);
				weeklyItem.setTotal(sum);
				weekly.getItems().add(weeklyItem);
				weekly.setCount(weekly.getItems().size());
			}
		}
		model.addAttribute("weekly", weekly);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("shortdates", shortdates);
		model.addAttribute("employee", employee);
		return "statics/works";
	}
	
	@RequestMapping(value = "/statics/labortimes", method = RequestMethod.GET)
	public String labortimes() {
		return "statics/labortimes";
	}
	
	@RequestMapping(value = "/statics/labortimes.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> labortimesjson(SalesDto dto) {
		
		List<List> result = labortimesService.staticByMonth(dto.getStarttime(), dto.getEndtime());
		return result;
	}
	
	@RequestMapping(value = "/statics/jobtype", method = RequestMethod.GET)
	public String jobtype(Model model,@RequestParam(required = true) String eid) {
		Employee employee = emService.getById(eid);
		List<Dealer> dealers = dealerService.getDealersByEid(eid, new PageRequest(0, 1000));
		model.addAttribute("employee", employee);
		model.addAttribute("dealers", dealers);
		return "statics/jobtype";
	}
	
	@RequestMapping(value = "/statics/jobtype.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> jobtypeJson(TotalCostDto dto) {
		Employee employee = emService.getById(dto.getEid());
		Dealer dealer = null;
		if(dto.getDid()!=0){
			dealer = dealerService.getById(dto.getDid());
		}
		List<List> result = emService.jobtype(employee, dto.getStarttime(), dto.getEndtime(), dealer);
		return result;
	}
	
	@RequestMapping("/statics/exportworks")
    public void exportExcel(@RequestParam(required = true) String eid,HttpServletRequest request, HttpServletResponse response)
    {
		List<Integer> widths = new ArrayList<Integer>();
		List<List> datas = new ArrayList<List>();
		List<Weekly> weeklies = weeklyService.allByEid(eid, new PageRequest(0, 10000));
		List<String> titles = new ArrayList<String>();
		List<Integer> counts = new ArrayList<Integer>();
		titles.add("时间段");
		widths.add(10000);
		titles.add("客户");
		widths.add(5000);
		titles.add("工作类型");
		widths.add(3000);
		titles.add("工作内容");
		widths.add(10000);
		titles.add("星期一");
		widths.add(2000);
		titles.add("星期二");
		widths.add(2000);
		titles.add("星期三");
		widths.add(2000);
		titles.add("星期四");
		widths.add(2000);
		titles.add("星期五");
		widths.add(2000);
		titles.add("星期六");
		widths.add(2000);
		titles.add("星期日");
		widths.add(2000);
		datas.add(titles);
		if(weeklies!=null && weeklies.size()>0){
			for (int i=0;i<weeklies.size();i++) {
				Weekly weekly = weeklies.get(i);
				if(weekly.getItems()==null || weekly.getItems().size()==0){
					continue;
				}
				counts.add(weekly.getItems().size());
				for (int j = 0; j < weekly.getItems().size(); j++) {
					WeeklyItem item = weekly.getItems().get(j);
					List<String> values = new ArrayList<String>();
					if(j==0){
						values.add(DateTools.FormateDateShort(weekly.getStime())+"--"+DateTools.FormateDateShort(weekly.getEtime()));
					}
					else{
						values.add("");
					}
					values.add(item.getDealer()==null?"":item.getDealer().getName());
					values.add(item.getType()==null?"":item.getType().getName());
					values.add(item.getContent());
					values.add(item.getMon()==null?"":item.getMon().toString());
					values.add(item.getTue()==null?"":item.getTue().toString());
					values.add(item.getWed()==null?"":item.getWed().toString());
					values.add(item.getThu()==null?"":item.getThu().toString());
					values.add(item.getFri()==null?"":item.getFri().toString());
					values.add(item.getSat()==null?"":item.getSat().toString());
					values.add(item.getSun()==null?"":item.getSun().toString());
					datas.add(values);
				}
				
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
            codedFileName = java.net.URLEncoder.encode("周项目工作", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = ExcelUtil.exportExcel(datas, widths, "周项目工作",counts);
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
