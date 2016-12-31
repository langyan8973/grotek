package com.grotek.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

import com.grotek.dto.DealersNote;
import com.grotek.dto.IncomeDto;
import com.grotek.dto.LaborHourDto;
import com.grotek.dto.SalesDto;
import com.grotek.dto.SuperiorsNote;
import com.grotek.pojo.CreditRatingDic;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerGoal;
import com.grotek.pojo.DealerPrice;
import com.grotek.pojo.DealerValue;
import com.grotek.pojo.District;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductBox_In;
import com.grotek.pojo.ProductBox_Price;
import com.grotek.pojo.ProductBox_Store;
import com.grotek.pojo.ProductBox_out;
import com.grotek.pojo.ProductLabor_Cost;
import com.grotek.pojo.ReputationDic;
import com.grotek.pojo.ShopTypeDic;
import com.grotek.pojo.StrengthDic;
import com.grotek.service.DealerPriceService;
import com.grotek.service.DealerService;
import com.grotek.service.DealerValueService;
import com.grotek.service.DicsService;
import com.grotek.service.ProductBoxService;
import com.grotek.util.DateTools;
import com.grotek.util.ExcelUtil;

@Controller
@RequestMapping("/manager")
public class DealerController {
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private DicsService dicService;
	
	@Autowired
	private DealerPriceService dealerPriceService;
	
	@Autowired
	private DealerValueService dealerValueService;
	
	@Autowired
	private ProductBoxService boxService;
	
	@RequestMapping(value = "/dealers/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<Dealer> dealers = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			dealers = dealerService.findDealers(context, new PageRequest(page,size));
			sumcount = dealerService.searchCount(context);
		} else {
			dealers = dealerService.getDealers(new PageRequest(page, size));
			sumcount = dealerService.allCount();
		}
		model.addAttribute("dealers", dealers);
		model.addAttribute("sumcount", sumcount);
		return "dealers/index";
	}
	
	@RequestMapping(value = "/dealers/select",method=RequestMethod.GET)
	public String select(Model model){
		
		List<DealersNote> dealersNotes = dealerService.getDealerNotes();
		
		model.addAttribute("notes", dealersNotes);
		return "dealers/select";
	}
	
	@RequestMapping(value = "/dealers/new",method=RequestMethod.GET)
	public String newDealer(Model model){
		List<StrengthDic> strengthDics = dicService.getStrengthDics();
		List<ReputationDic> reputationDics = dicService.getReputationDics();
		List<ShopTypeDic> shopTypeDics = dicService.getShopTypeDics();
		List<CreditRatingDic> creditRatingDics = dicService.getCreditRatingDics();
		model.addAttribute("strengths", strengthDics);
		model.addAttribute("reputations", reputationDics);
		model.addAttribute("shoptypes", shopTypeDics);
		model.addAttribute("creditratings", creditRatingDics);
		return "dealers/new";
	}
	
	@RequestMapping(value = "/dealers/test",method=RequestMethod.GET)
	public String test(Model model){
		List<StrengthDic> strengthDics = dicService.getStrengthDics();
		List<ReputationDic> reputationDics = dicService.getReputationDics();
		List<ShopTypeDic> shopTypeDics = dicService.getShopTypeDics();
		List<CreditRatingDic> creditRatingDics = dicService.getCreditRatingDics();
		model.addAttribute("strengths", strengthDics);
		model.addAttribute("reputations", reputationDics);
		model.addAttribute("shoptypes", shopTypeDics);
		model.addAttribute("creditratings", creditRatingDics);
		return "dealers/test";
	}
	
	@RequestMapping(value = "/dealers/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
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
		String agentid = multipartRequest.getParameter("agentid");
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
		if(StringUtils.isNotBlank(agentid)){
			Employee agent = new Employee();
			agent.setId(agentid);
			dealer.setAgent(agent);
		}
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

	@RequestMapping(value = "/dealers/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true) String id){
		Dealer dealer = dealerService.getById(Integer.parseInt(id));
		DealerGoal goal = dealerService.getGoalByDid(Integer.parseInt(id));
		List<DealerPrice> dealerPrices = dealerPriceService.getByDid(Integer.parseInt(id), new PageRequest(0, 10));
		List<DealerValue> dealerValues = dealerValueService.getByDid(Integer.parseInt(id), new PageRequest(0, 10));
		model.addAttribute("goal", goal);
		model.addAttribute("dealer", dealer);
		model.addAttribute("dealerprices", dealerPrices);
		model.addAttribute("dealervalues", dealerValues);
		return "dealers/profile";
	}
	
	@RequestMapping(value = "/dealers/edit",method=RequestMethod.GET)
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
		return "dealers/edit";
	}
	
	@RequestMapping(value = "/dealers/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
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
		String agentid = multipartRequest.getParameter("agentid");
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
		if(StringUtils.isNotBlank(agentid)){
			Employee agent = new Employee();
			agent.setId(agentid);
			dealer.setAgent(agent);
		}
		else{
			dealer.setAgent(null);
		}
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
	
	@RequestMapping(value = "/dealers/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteOne(@RequestParam(required = true) int id) {
		dealerService.delete(id);
		return "true";
	}
	
	@RequestMapping(value = "/dealers/editgoal", method = RequestMethod.POST)
	public String editgoal(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String did = multipartRequest.getParameter("did");
		String objective = multipartRequest.getParameter("objective");
		String promote = multipartRequest.getParameter("promote");
		String rebate = multipartRequest.getParameter("rebate");
		String suportAmount = multipartRequest.getParameter("suportAmount");
		String other = multipartRequest.getParameter("other");
		String remarks = multipartRequest.getParameter("remarks");
		
		Dealer dealer = new Dealer();
		dealer.setId(Integer.parseInt(did));
		DealerGoal goal = new DealerGoal();
		goal.setDealer(dealer);
		goal.setObjective(Double.parseDouble(objective));
		goal.setPromote(Double.parseDouble(promote));
		goal.setRebate(Double.parseDouble(rebate));
		goal.setSuportAmount(Double.parseDouble(suportAmount));
		goal.setOther(Double.parseDouble(other));
		goal.setRemarks(remarks);
		goal.setDate(new Date());
		
		dealerService.createGoal(goal);
		
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+did;
	}
	
	@RequestMapping(value = "/dealers/boxin",method=RequestMethod.GET)
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
		return "dealers/boxin";
	}
	
	@RequestMapping(value = "/dealers/boxstatic", method = RequestMethod.GET)
	public String boxstatic(Model model,@RequestParam(required = true) int did) {
		Dealer dealer = dealerService.getById(did);
		model.addAttribute("dealer", dealer);
		return "dealers/boxstatic";
	}

	@RequestMapping(value = "/dealers/boxstatic.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> boxstatic(LaborHourDto dto) {
		List<List> result = dealerService.staticBoxOut(Integer.parseInt(dto.getEid()), dto.getStarttime(), dto.getEndtime());
		return result;
	}
	
	@RequestMapping(value = "/dealers/employeefees", method = RequestMethod.GET)
	public String employeefees(Model model,@RequestParam(required = true) int did,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size) {
		Dealer dealer = dealerService.getById(did);
		List<EmployeeFee> fees = dealerService.getFeesByDid(did, new PageRequest(page, size));
		int sumcount = dealerService.feeCountBydid(did);
		model.addAttribute("dealer", dealer);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("fees", fees);
		return "dealers/employeefees";
	}
	
	@RequestMapping(value = "/dealers/feeinfo",method=RequestMethod.GET)
	public String feeinfo(Model model,@RequestParam(required = true) int fid){
		EmployeeFee fee = dealerService.getFeeById(fid);
		model.addAttribute("fee", fee);
		return "dealers/feeinfo";
	}
	
	@RequestMapping(value = "/dealers/expenses", method = RequestMethod.GET)
	public String expenses(Model model,@RequestParam(required = true) int did) {
		Dealer dealer = dealerService.getById(did);
		model.addAttribute("dealer", dealer);
		return "dealers/expenses";
	}
	
	@RequestMapping(value = "/dealers/expenses.json", method = RequestMethod.GET)
	@ResponseBody
	public List<List> expensesjson(SalesDto dto) {
		List<List> result = dealerService.staticExpenses(Integer.parseInt(dto.getEid()), dto.getStarttime(), dto.getEndtime());
		return result;
	}
	
	@RequestMapping("/dealers/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)
    {
		List<Integer> widths = new ArrayList<Integer>();
		List<List> datas = new ArrayList<List>();
		List<Dealer> dealers = dealerService.allDealers();
		List<String> titles = new ArrayList<String>();
		titles.add("序号");
		widths.add(2000);
		titles.add("名称");
		widths.add(4000);
		titles.add("地址");
		widths.add(10000);
		titles.add("省（自治区）");
		widths.add(3000);
		titles.add("地区（市）");
		widths.add(3000);
		titles.add("县（旗）");
		widths.add(3000);
		titles.add("乡（镇）");
		widths.add(3000);
		titles.add("邮编");
		widths.add(3000);
		titles.add("电话");
		widths.add(4000);
		titles.add("所有者");
		widths.add(3000);
		titles.add("公司");
		widths.add(5000);
		titles.add("联系人");
		widths.add(2000);
		titles.add("手机");
		widths.add(5000);
		titles.add("年营业额");
		widths.add(2000);
		titles.add("店铺面积");
		widths.add(2000);
		titles.add("员工总数");
		widths.add(2000);
		titles.add("分销店数");
		widths.add(2000);
		titles.add("主要经营范围");
		widths.add(8000);
		titles.add("主要销售区域");
		widths.add(8000);
		titles.add("是否从经销商进货");
		widths.add(3000);
		titles.add("进货经销商");
		widths.add(4000);
		titles.add("是否合作");
		widths.add(2000);
		titles.add("付款单位");
		widths.add(5000);
		titles.add("付款单位地址");
		widths.add(5000);
		titles.add("付款单位联系人");
		widths.add(3000);
		titles.add("付款单位电话");
		widths.add(5000);
		titles.add("竞争实力");
		widths.add(3000);
		titles.add("商业信誉");
		widths.add(3000);
		titles.add("店铺类型");
		widths.add(3000);
		titles.add("信誉等级");
		widths.add(3000);
		titles.add("登记日期");
		widths.add(4000);
		titles.add("评语");
		widths.add(20000);
		datas.add(titles);
		if(dealers!=null && dealers.size()>0){
			for (int i=0;i<dealers.size();i++) {
				Dealer dealer = dealers.get(i);
				List<String> values = new ArrayList<String>();
				values.add((i+1)+"");
				values.add(dealer.getName()==null?"":dealer.getName());
				values.add(dealer.getAddress()==null?"":dealer.getAddress());
				values.add(dealer.getShengfen()==null?"":dealer.getShengfen().getName());
				values.add(dealer.getDiqu()==null?"":dealer.getDiqu().getName());
				values.add(dealer.getXian()==null?"":dealer.getXian().getName());
				values.add(dealer.getZhen()==null?"":dealer.getZhen().getName());
				values.add(dealer.getPostcode()==null?"":dealer.getPostcode());
				values.add(dealer.getPhone()==null?"":dealer.getPhone());
				values.add(dealer.getOwner()==null?"":dealer.getOwner());
				values.add(dealer.getCompany()==null?"":dealer.getCompany());
				values.add(dealer.getContact()==null?"":dealer.getContact());
				values.add(dealer.getMobile()==null?"":dealer.getMobile());
				values.add(dealer.getTurnover()==null?"":dealer.getTurnover().toString());
				values.add(dealer.getArea()==null?"":dealer.getArea().toString());
				values.add(dealer.getPopulation()==null?"":dealer.getPopulation().toString());
				values.add(dealer.getFxdCount()==null?"":dealer.getFxdCount().toString());
				values.add(dealer.getBusiness()==null?"":dealer.getBusiness());
				values.add(dealer.getTerritory()==null?"":dealer.getTerritory());
				values.add(dealer.getSfcjxsjh()==null?"":(dealer.getSfcjxsjh()==0?"否":"是"));
				values.add(dealer.getJhdealer()==null?"":dealer.getJhdealer().getName());
				values.add(dealer.getSfhz()==null?"":(dealer.getSfhz()==0?"否":"是"));
				values.add(dealer.getPname()==null?"":dealer.getPname());
				values.add(dealer.getPaddress()==null?"":dealer.getPaddress());
				values.add(dealer.getPcontact()==null?"":dealer.getPcontact());
				values.add(dealer.getPphone()==null?"":dealer.getPphone());
				values.add(dealer.getStrength()==null?"":dealer.getStrength().getName());
				values.add(dealer.getReputation()==null?"":dealer.getReputation().getName());
				values.add(dealer.getShoptype()==null?"":dealer.getShoptype().getName());
				values.add(dealer.getCreditrating()==null?"":dealer.getCreditrating().getName());
				values.add(dealer.getDjdate()==null?"":DateTools.FormateDate(dealer.getDjdate()));
				values.add(dealer.getDescription()==null?"":dealer.getDescription());
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
            codedFileName = java.net.URLEncoder.encode("经销商表", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = ExcelUtil.exportExcel(datas, widths, "经销商",null);
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
