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

import com.grotek.dto.IncomeDto;
import com.grotek.dto.LaborHourDto;
import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.CreditRatingDic;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerGoal;
import com.grotek.pojo.DealerPrice;
import com.grotek.pojo.DealerSale;
import com.grotek.pojo.DealerValue;
import com.grotek.pojo.DeliveryOrder;
import com.grotek.pojo.District;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.ProductBox_out;
import com.grotek.pojo.ReputationDic;
import com.grotek.pojo.ShopTypeDic;
import com.grotek.pojo.StrengthDic;
import com.grotek.service.ApplyForSendService;
import com.grotek.service.DealerPriceService;
import com.grotek.service.DealerSaleService;
import com.grotek.service.DealerService;
import com.grotek.service.DealerValueService;
import com.grotek.service.DeliveryOrderService;
import com.grotek.service.DicsService;
import com.grotek.service.EmployeeService;
import com.grotek.service.ProductBoxService;
import com.grotek.service.UserContext;
import com.grotek.util.DateTools;

@Controller
@RequestMapping("/work")
public class ClientController {
	
	@Autowired
	private UserContext userContext;
	@Autowired
	private DealerService dealerService;
	@Autowired
	private DealerPriceService dealerPriceService;
	@Autowired
	private DealerValueService dealerValueService;
	@Autowired
	private ProductBoxService boxService;
	@Autowired
	private DealerSaleService saleService;
	@Autowired
	private ApplyForSendService applyForSendService;
	@Autowired
	private EmployeeService emService;
	@Autowired
	private DeliveryOrderService deliveryService;
	@Autowired
	private DicsService dicService;

	@RequestMapping(value = "/clients/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		Employee employee = userContext.getCurrentUser();
		List<Dealer> dealers = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			dealers = dealerService.findDealersByEid(employee.getId(), context, new PageRequest(page,size));
			sumcount = dealerService.searchCountByEid(employee.getId(), context);
		} else {
			dealers = dealerService.getDealersByEid(employee.getId(), new PageRequest(page,size));
			sumcount = dealerService.allCountByEid(employee.getId());
		}
		model.addAttribute("dealers", dealers);
		model.addAttribute("sumcount", sumcount);
		return "clients/index";
	}
	
	@RequestMapping(value = "/clients/new",method=RequestMethod.GET)
	public String newDealer(Model model){
		//创建经销商
		List<StrengthDic> strengthDics = dicService.getStrengthDics();
		List<ReputationDic> reputationDics = dicService.getReputationDics();
		List<ShopTypeDic> shopTypeDics = dicService.getShopTypeDics();
		List<CreditRatingDic> creditRatingDics = dicService.getCreditRatingDics();
		model.addAttribute("strengths", strengthDics);
		model.addAttribute("reputations", reputationDics);
		model.addAttribute("shoptypes", shopTypeDics);
		model.addAttribute("creditratings", creditRatingDics);
		return "clients/new";
	}
	
	@RequestMapping(value = "/clients/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		Employee employee = userContext.getCurrentUser();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String address = multipartRequest.getParameter("address");
		String shengid = multipartRequest.getParameter("shengid");
		String diquid = multipartRequest.getParameter("diquid");
		String xianid = multipartRequest.getParameter("xianid");
		String zhenid = multipartRequest.getParameter("zhenid");
		String postcode = multipartRequest.getParameter("postcode");
		String phone = multipartRequest.getParameter("phone");
		String owner = multipartRequest.getParameter("owner");
		String company = multipartRequest.getParameter("company");
		String contact = multipartRequest.getParameter("contact");
		String mobile = multipartRequest.getParameter("mobile");
		String turnover = multipartRequest.getParameter("turnover");
		String area = multipartRequest.getParameter("area");
		String population = multipartRequest.getParameter("population");
		String fxd_count = multipartRequest.getParameter("fxd_count");
		String strength = multipartRequest.getParameter("strength");
		String reputation = multipartRequest.getParameter("reputation");
		String business = multipartRequest.getParameter("business");
		String territory = multipartRequest.getParameter("territory");
		String shoptype = multipartRequest.getParameter("shoptype");
		String creditrating = multipartRequest.getParameter("creditrating");
		String djdate = multipartRequest.getParameter("djdate");
		String description = multipartRequest.getParameter("description");
		String sfcjxsjh = multipartRequest.getParameter("sfcjxsjh");
		String jhdealerid = multipartRequest.getParameter("jhdealer");
		String sfhz = multipartRequest.getParameter("sfhz");
		String pname = multipartRequest.getParameter("pname");
		String paddress = multipartRequest.getParameter("paddress");
		String pcontact = multipartRequest.getParameter("pcontact");
		String pphone = multipartRequest.getParameter("pphone");
		
		Dealer dealer = new Dealer();
		dealer.setName(name);
		dealer.setAddress(address);
		District sheng = new District();
		sheng.setId(Integer.parseInt(shengid));
		dealer.setShengfen(sheng);
		dealer.setContact(contact);
		dealer.setMobile(mobile);
		if(StringUtils.isNotBlank(diquid)){
			District diqu = new District();
			diqu.setId(Integer.parseInt(diquid));
			dealer.setDiqu(diqu);
		}
		if(StringUtils.isNotBlank(xianid)){
			District xian = new District();
			xian.setId(Integer.parseInt(xianid));
			dealer.setXian(xian);
		}
		if(StringUtils.isNotBlank(zhenid)){
			District zhen = new District();
			zhen.setId(Integer.parseInt(zhenid));
			dealer.setZhen(zhen);
		}
		dealer.setPostcode(postcode);
		dealer.setPhone(phone);
		dealer.setCompany(company);
		dealer.setOwner(owner);
		if(StringUtils.isNotBlank(turnover)){
			dealer.setTurnover(Double.parseDouble(turnover));
		}
		if(StringUtils.isNotBlank(area)){
			dealer.setArea(Double.parseDouble(area));
		}
		if(StringUtils.isNotBlank(population)){
			dealer.setPopulation(Integer.parseInt(population));
		}
		if(StringUtils.isNotBlank(fxd_count)){
			dealer.setFxdCount(Integer.parseInt(fxd_count));
		}
		if(StringUtils.isNotBlank(strength)){
			StrengthDic strengthDic = new StrengthDic();
			strengthDic.setId(Integer.parseInt(strength));
			dealer.setStrength(strengthDic);
		}
		if(StringUtils.isNotBlank(reputation)){
			ReputationDic reputationDic = new ReputationDic();
			reputationDic.setId(Integer.parseInt(reputation));
			dealer.setReputation(reputationDic);
		}
		dealer.setBusiness(business);
		dealer.setTerritory(territory);
		if(StringUtils.isNotBlank(shoptype)){
			ShopTypeDic shopTypeDic = new ShopTypeDic();
			shopTypeDic.setId(Integer.parseInt(shoptype));
			dealer.setShoptype(shopTypeDic);
		}
		if(StringUtils.isNotBlank(creditrating)){
			CreditRatingDic creditRatingDic = new CreditRatingDic();
			creditRatingDic.setId(Integer.parseInt(creditrating));
			dealer.setCreditrating(creditRatingDic);
		}
		if(StringUtils.isNotBlank(djdate))
		{
			Date date = DateTools.ParseString(djdate);
			dealer.setDjdate(date);
		}
		dealer.setSfcjxsjh(Integer.parseInt(sfcjxsjh));
		if(StringUtils.isNotBlank(jhdealerid)){
			Dealer dealer2 = new Dealer();
			dealer2.setId(Integer.parseInt(jhdealerid));
			dealer.setJhdealer(dealer2);
		}
		
		dealer.setAgent(employee);
		dealer.setSfhz(Integer.parseInt(sfhz));
		dealer.setPname(pname);
		dealer.setPaddress(paddress);
		dealer.setPcontact(pcontact);
		dealer.setPphone(pphone);
		dealer.setDescription(description);
		
		dealerService.addDealer(dealer);
		
		redirectAttributes.addFlashAttribute("success", "创建成功");
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/clients/edit",method=RequestMethod.GET)
	public String edit(Model model,@RequestParam(required = true) String id){
		Dealer dealer = dealerService.getById(Integer.parseInt(id));
		List<StrengthDic> strengthDics = dicService.getStrengthDics();
		List<ReputationDic> reputationDics = dicService.getReputationDics();
		List<ShopTypeDic> shopTypeDics = dicService.getShopTypeDics();
		List<CreditRatingDic> creditRatingDics = dicService.getCreditRatingDics();
		model.addAttribute("strengths", strengthDics);
		model.addAttribute("reputations", reputationDics);
		model.addAttribute("shoptypes", shopTypeDics);
		model.addAttribute("creditratings", creditRatingDics);
		model.addAttribute("dealer", dealer);
		return "clients/edit";
	}
	
	@RequestMapping(value = "/clients/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Employee employee = userContext.getCurrentUser();
		String id = multipartRequest.getParameter("id");
		String name = multipartRequest.getParameter("name");
		String address = multipartRequest.getParameter("address");
		String shengid = multipartRequest.getParameter("shengid");
		String diquid = multipartRequest.getParameter("diquid");
		String xianid = multipartRequest.getParameter("xianid");
		String zhenid = multipartRequest.getParameter("zhenid");
		String postcode = multipartRequest.getParameter("postcode");
		String phone = multipartRequest.getParameter("phone");
		String owner = multipartRequest.getParameter("owner");
		String company = multipartRequest.getParameter("company");
		String contact = multipartRequest.getParameter("contact");
		String mobile = multipartRequest.getParameter("mobile");
		String turnover = multipartRequest.getParameter("turnover");
		String area = multipartRequest.getParameter("area");
		String population = multipartRequest.getParameter("population");
		String fxd_count = multipartRequest.getParameter("fxd_count");
		String strength = multipartRequest.getParameter("strength");
		String reputation = multipartRequest.getParameter("reputation");
		String business = multipartRequest.getParameter("business");
		String territory = multipartRequest.getParameter("territory");
		String shoptype = multipartRequest.getParameter("shoptype");
		String creditrating = multipartRequest.getParameter("creditrating");
		String djdate = multipartRequest.getParameter("djdate");
		String description = multipartRequest.getParameter("description");
		String sfcjxsjh = multipartRequest.getParameter("sfcjxsjh");
		String jhdealerid = multipartRequest.getParameter("jhdealer");
		String sfhz = multipartRequest.getParameter("sfhz");
		String pname = multipartRequest.getParameter("pname");
		String paddress = multipartRequest.getParameter("paddress");
		String pcontact = multipartRequest.getParameter("pcontact");
		String pphone = multipartRequest.getParameter("pphone");
		
		Dealer dealer = dealerService.getById(Integer.parseInt(id));
		dealer.setName(name);
		dealer.setAddress(address);
		District sheng = new District();
		sheng.setId(Integer.parseInt(shengid));
		dealer.setShengfen(sheng);
		dealer.setContact(contact);
		dealer.setMobile(mobile);
		if(StringUtils.isNotBlank(diquid)){
			District diqu = new District();
			diqu.setId(Integer.parseInt(diquid));
			dealer.setDiqu(diqu);
		}
		else{
			dealer.setDiqu(null);
		}
		if(StringUtils.isNotBlank(xianid)){
			District xian = new District();
			xian.setId(Integer.parseInt(xianid));
			dealer.setXian(xian);
		}
		else{
			dealer.setXian(null);
		}
		if(StringUtils.isNotBlank(zhenid)){
			District zhen = new District();
			zhen.setId(Integer.parseInt(zhenid));
			dealer.setZhen(zhen);
		}
		else{
			dealer.setZhen(null);
		}
		dealer.setPostcode(postcode);
		dealer.setPhone(phone);
		dealer.setCompany(company);
		dealer.setOwner(owner);
		if(StringUtils.isNotBlank(turnover)){
			dealer.setTurnover(Double.parseDouble(turnover));
		}
		else{
			dealer.setTurnover(null);
		}
		if(StringUtils.isNotBlank(area)){
			dealer.setArea(Double.parseDouble(area));
		}
		else{
			dealer.setArea(null);
		}
		if(StringUtils.isNotBlank(population)){
			dealer.setPopulation(Integer.parseInt(population));
		}
		else{
			dealer.setPopulation(null);
		}
		if(StringUtils.isNotBlank(fxd_count)){
			dealer.setFxdCount(Integer.parseInt(fxd_count));
		}
		else{
			dealer.setFxdCount(null);
		}
		if(StringUtils.isNotBlank(strength)){
			StrengthDic strengthDic = new StrengthDic();
			strengthDic.setId(Integer.parseInt(strength));
			dealer.setStrength(strengthDic);
		}
		else{
			dealer.setStrength(null);
		}
		if(StringUtils.isNotBlank(reputation)){
			ReputationDic reputationDic = new ReputationDic();
			reputationDic.setId(Integer.parseInt(reputation));
			dealer.setReputation(reputationDic);
		}
		else{
			dealer.setReputation(null);
		}
		dealer.setBusiness(business);
		dealer.setTerritory(territory);
		if(StringUtils.isNotBlank(shoptype)){
			ShopTypeDic shopTypeDic = new ShopTypeDic();
			shopTypeDic.setId(Integer.parseInt(shoptype));
			dealer.setShoptype(shopTypeDic);
		}
		else{
			dealer.setShoptype(null);
		}
		if(StringUtils.isNotBlank(creditrating)){
			CreditRatingDic creditRatingDic = new CreditRatingDic();
			creditRatingDic.setId(Integer.parseInt(creditrating));
			dealer.setCreditrating(creditRatingDic);
		}
		else{
			dealer.setCreditrating(null);
		}
		if(StringUtils.isNotBlank(djdate))
		{
			Date date = DateTools.ParseString(djdate);
			dealer.setDjdate(date);
		}
		else{
			dealer.setDjdate(null);
		}
		dealer.setSfcjxsjh(Integer.parseInt(sfcjxsjh));
		if(StringUtils.isNotBlank(jhdealerid)){
			Dealer dealer2 = new Dealer();
			dealer2.setId(Integer.parseInt(jhdealerid));
			dealer.setJhdea(dealer2);
		}
		else{
			dealer.setJhdea(dealer);
		}
		
		dealer.setAgent(employee);
		dealer.setSfhz(Integer.parseInt(sfhz));
		dealer.setPname(pname);
		dealer.setPaddress(paddress);
		dealer.setPcontact(pcontact);
		dealer.setPphone(pphone);
		dealer.setDescription(description);
		
		dealerService.editDealer(dealer);
		
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+id;
	}
	
	@RequestMapping(value = "/clients/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true) String id){
		Dealer dealer = dealerService.getById(Integer.parseInt(id));
		DealerGoal goal = dealerService.getGoalByDid(Integer.parseInt(id));
		List<DealerPrice> dealerPrices = dealerPriceService.getByDid(Integer.parseInt(id), new PageRequest(0, 10));
		List<DealerValue> dealerValues = dealerValueService.getByDid(Integer.parseInt(id), new PageRequest(0, 10));
		model.addAttribute("goal", goal);
		model.addAttribute("dealer", dealer);
		model.addAttribute("dealerprices", dealerPrices);
		model.addAttribute("dealervalues", dealerValues);
		return "clients/profile";
	}
	
	@RequestMapping(value = "/clients/prices",method=RequestMethod.GET)
	public String prices(Model model,@RequestParam(required = true) String did,
			@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size){
		Dealer dealer = dealerService.getById(Integer.parseInt(did));
		List<DealerPrice> dealerPrices;
		int sumcount = 0;
		if(StringUtils.isNotBlank(context)){
			dealerPrices = dealerPriceService.findByDid(Integer.parseInt(did), context, new PageRequest(page, size));
			sumcount = dealerPriceService.searchCountByDid(Integer.parseInt(did), context);
		}
		else{
			dealerPrices = dealerPriceService.getByDid(Integer.parseInt(did), new PageRequest(page, size));
			sumcount = dealerPriceService.allCountByDid(Integer.parseInt(did));
		}		
		model.addAttribute("dealer", dealer);
		model.addAttribute("dealerprices", dealerPrices);
		model.addAttribute("sumcount", sumcount);
		return "clients/prices";
	}
	
	@RequestMapping(value = "/clients/values",method=RequestMethod.GET)
	public String values(Model model,@RequestParam(required = true) String did,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size){
		Dealer dealer = dealerService.getById(Integer.parseInt(did));
		List<DealerValue> dealerValues;
		int sumcount = 0;
		dealerValues = dealerValueService.getByDid(Integer.parseInt(did), new PageRequest(page, size));
		sumcount = dealerValueService.allCountByDid(Integer.parseInt(did));	
		model.addAttribute("dealer", dealer);
		model.addAttribute("dealervalues", dealerValues);
		model.addAttribute("sumcount", sumcount);
		return "clients/values";
	}
	
	@RequestMapping(value = "/clients/boxin",method=RequestMethod.GET)
	public String boxin(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size,
			@RequestParam(required = true) int did){
		Dealer dealer = dealerService.getById(did);
		int sumcount = 0;
		List<ProductBox_out> outs;
		if (StringUtils.isNotBlank(context)) {
			outs = boxService.findOutsBydid(did, context, new PageRequest(page, size));
			sumcount = boxService.searchOutCountBydid(did, context);
		} else {
			outs = boxService.getOutsBydid(did, new PageRequest(page, size));
			sumcount = boxService.outallcountBydid(did);
		}
		model.addAttribute("outs", outs);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("dealer", dealer);
		return "clients/boxin";
	}
	
	@RequestMapping(value = "/clients/boxstatic", method = RequestMethod.GET)
	public String boxstatic(Model model,@RequestParam(required = true) int did) {
		Dealer dealer = dealerService.getById(did);
		model.addAttribute("dealer", dealer);
		return "clients/boxstatic";
	}

	@RequestMapping(value = "/clients/boxstatic.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> boxstatic(LaborHourDto dto) {
		List<List> result = dealerService.staticBoxOut(Integer.parseInt(dto.getEid()), dto.getStarttime(), dto.getEndtime());
		return result;
	}
	
	@RequestMapping(value = "/clients/employeefees", method = RequestMethod.GET)
	public String employeefees(Model model,@RequestParam(required = true) int did,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Dealer dealer = dealerService.getById(did);
		List<EmployeeFee> fees = dealerService.getFeesByDid(did, new PageRequest(page, size));
		model.addAttribute("dealer", dealer);
		model.addAttribute("fees", fees);
		return "clients/employeefees";
	}
	
	@RequestMapping(value = "/clients/feeinfo",method=RequestMethod.GET)
	public String feeinfo(Model model,@RequestParam(required = true) int fid){
		EmployeeFee fee = dealerService.getFeeById(fid);
		model.addAttribute("fee", fee);
		return "clients/feeinfo";
	}
	
	@RequestMapping(value = "/clients/expenses", method = RequestMethod.GET)
	public String expenses(Model model,@RequestParam(required = true) int did) {
		Dealer dealer = dealerService.getById(did);
		model.addAttribute("dealer", dealer);
		return "clients/expenses";
	}
	
	@RequestMapping(value = "/clients/expenses.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> expensesjson(IncomeDto dto) {
		Date start = DateTools.getCurrYearFirst(dto.getStarttime());
		Date end = DateTools.getCurrYearLast(dto.getStarttime());
		List<List> result = dealerService.staticExpenses(Integer.parseInt(dto.getEid()), start, end);
		return result;
	}
	
	@RequestMapping(value = "/clients/dealersale",method=RequestMethod.GET)
	public String dealersale(Model model,@RequestParam(required = true) String outid){
		DealerSale sale;
		sale = saleService.getByOutId(Integer.parseInt(outid));
		model.addAttribute("sale", sale);	
		
		return "clients/dealersale";
	}
	
	@RequestMapping(value = "/clients/sendapply",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true, defaultValue = "0") int id){
		ApplyForSend applyForSend = applyForSendService.getById(id);
		model.addAttribute("apply", applyForSend);
		return "clients/sendapply";
	}
	
	@RequestMapping(value = "/clients/postorder",method=RequestMethod.GET)
	public String order(Model model,@RequestParam(required = true, defaultValue = "0") int id){
		DeliveryOrder deliveryOrder;
		deliveryOrder = deliveryService.getById(id);
		Employee employee = emService.getById(deliveryOrder.getEmployee().getId());
		Dealer dealer = dealerService.getById(deliveryOrder.getDealer().getId());
		model.addAttribute("order", deliveryOrder);
		model.addAttribute("employee", employee);
		model.addAttribute("dealer", dealer);
		return "clients/postorder";
	}
}
