package com.grotek.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.grotek.dto.BoxNote;
import com.grotek.dto.ServiceResult;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerPrice;
import com.grotek.pojo.GrotekUnit;
import com.grotek.pojo.ManureType;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductBoxInScheme;
import com.grotek.pojo.ProductBox_In;
import com.grotek.pojo.ProductBox_Price;
import com.grotek.pojo.ProductBox_Store;
import com.grotek.pojo.ProductBox_out;
import com.grotek.pojo.ProductLabor_Cost;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductPage;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductPage_Store;
import com.grotek.pojo.ProductRaw_Out;
import com.grotek.service.DealerPriceService;
import com.grotek.service.GrotekUnitService;
import com.grotek.service.ManureTypeService;
import com.grotek.service.ProductBoxService;
import com.grotek.service.ProductRawService;
import com.grotek.util.DateTools;
import com.grotek.util.ExcelUtil;
import com.grotek.util.PingYinUtil;

@Controller
@RequestMapping("/manager")
public class ProductBoxController {
	
	@Autowired
	private ProductBoxService boxService;
	
	@Autowired
	private DealerPriceService dealerPriceService;
	@Autowired
	private GrotekUnitService unitService;
	@Autowired
	private ManureTypeService typeService;
	
	@RequestMapping(value = "/productboxes/index",method=RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductBox> productBoxes = null;
		List<GrotekUnit> units = unitService.getAll();
		List<ManureType> types = typeService.getAll();
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productBoxes = boxService.findProductBoxs(context, new PageRequest(page,size));
			sumcount = boxService.searchCount(context);
		} else {
			productBoxes = boxService.getProductBoxs(new PageRequest(page, size));
			sumcount = boxService.allCount();
		}
		model.addAttribute("boxes", productBoxes);
		model.addAttribute("sumcount", sumcount);
		model.addAttribute("units", units);
		model.addAttribute("types", types);
		return "productboxes/index";
	}
	
	@RequestMapping(value = "/productboxes/addone", method = RequestMethod.POST)
	public String addOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String specification = multipartRequest.getParameter("specification");
		String unit = multipartRequest.getParameter("unit");
		String type = multipartRequest.getParameter("type");
		String netcontent = multipartRequest.getParameter("netcontent");
		String piececount = multipartRequest.getParameter("piececount");
		String piececontent = multipartRequest.getParameter("piececontent");
		String kgstr = multipartRequest.getParameter("kg");
		int unitid = Integer.parseInt(unit);
		GrotekUnit grotekUnit = new GrotekUnit();
		grotekUnit.setId(unitid);
		int typeid = Integer.parseInt(type);
		ManureType manureType = new ManureType();
		manureType.setId(typeid);
		
		if(boxService.check(code)>0){
			redirectAttributes.addFlashAttribute("message", "产品编码重复");
		}
		else {
			ProductBox productBox = new ProductBox();
			productBox.setName(name);
			productBox.setCode(code);
			productBox.setSpecification(specification);
			productBox.setGu(grotekUnit);
			productBox.setType(manureType);
			productBox.setNetcontent(Double.parseDouble(netcontent));
			productBox.setPiececount(Integer.parseInt(piececount));
			productBox.setPiececontent(Double.parseDouble(piececontent));
			productBox.setKg(Double.parseDouble(kgstr));
			String pinyin = PingYinUtil.getPingYin(name);
			productBox.setPinyin(pinyin);
			boxService.addProductBox(productBox);
			redirectAttributes.addFlashAttribute("success", "创建成功");
		}
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/productboxes/profile",method=RequestMethod.GET)
	public String profile(Model model,@RequestParam(required = true) String id){
		ProductBox box = boxService.getById(Integer.parseInt(id));
		ProductBox_Price price = boxService.getPriceByPid(Integer.parseInt(id));
		ProductBox_Store store = boxService.getStoreByPid(Integer.parseInt(id));
		List<ProductBox_In> ins = boxService.getInByPid(Integer.parseInt(id));
		List<ProductBox_out> outs = boxService.getOutByPid(Integer.parseInt(id));
		ProductLabor_Cost labor_Cost = boxService.getLaborCostByPid(Integer.parseInt(id));
		List<DealerPrice> dealerPrices = dealerPriceService.getByPid(Integer.parseInt(id), new PageRequest(0, 10));
		List<GrotekUnit> units = unitService.getAll();
		List<ManureType> types = typeService.getAll();
		model.addAttribute("box", box);
		model.addAttribute("price", price);
		model.addAttribute("store", store);
		model.addAttribute("ins", ins);
		model.addAttribute("outs", outs);
		model.addAttribute("laborcost", labor_Cost);
		model.addAttribute("dealerprices", dealerPrices);
		model.addAttribute("units", units);
		model.addAttribute("types", types);
		return "productboxes/profile";
	}
	
	@RequestMapping(value = "/productboxes/editone", method = RequestMethod.POST)
	public String editOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String name = multipartRequest.getParameter("name");
		String code = multipartRequest.getParameter("code");
		String specification = multipartRequest.getParameter("specification");
		String unit = multipartRequest.getParameter("unit");
		String type = multipartRequest.getParameter("type");
		String netcontent = multipartRequest.getParameter("netcontent");
		String piececount = multipartRequest.getParameter("piececount");
		String piececontent = multipartRequest.getParameter("piececontent");
		String kgstr = multipartRequest.getParameter("kg");
		String bid = multipartRequest.getParameter("bid");
		int unitid = Integer.parseInt(unit);
		GrotekUnit grotekUnit = new GrotekUnit();
		grotekUnit.setId(unitid);
		int typeid = Integer.parseInt(type);
		ManureType manureType = new ManureType();
		manureType.setId(typeid);
		int id = Integer.parseInt(bid);
		ProductBox box= boxService.getById(id);
		if(box.getCode().equals(code)){
			box.setName(name);
			box.setSpecification(specification);
			box.setGu(grotekUnit);
			box.setType(manureType);
			box.setNetcontent(Double.parseDouble(netcontent));
			box.setPiececount(Integer.parseInt(piececount));
			box.setPiececontent(Double.parseDouble(piececontent));
			box.setKg(Double.parseDouble(kgstr));
			String pinyin = PingYinUtil.getPingYin(name);
			box.setPinyin(pinyin);
			boxService.editProductBox(box);
			redirectAttributes.addFlashAttribute("success", "编辑成功");
		}
		else{
			if(boxService.check(code)>0){
				redirectAttributes.addFlashAttribute("message", "产品编码重复");
			}
			else {
				box.setCode(code);
				box.setName(name);
				box.setSpecification(specification);
				box.setGu(grotekUnit);
				box.setType(manureType);
				box.setNetcontent(Double.parseDouble(netcontent));
				box.setPiececount(Integer.parseInt(piececount));
				box.setPiececontent(Double.parseDouble(piececontent));
				box.setKg(Double.parseDouble(kgstr));
				String pinyin = PingYinUtil.getPingYin(name);
				box.setPinyin(pinyin);
				boxService.editProductBox(box);
				redirectAttributes.addFlashAttribute("success", "编辑成功");
			}
		}	
		return "redirect:profile?id="+id;
	}
	
	@RequestMapping(value = "/productboxes/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDept(@RequestParam(required = true) int id) {
		boxService.deleteOne(id);
		return "true";
	}

	@RequestMapping(value = "/productboxes/price",method=RequestMethod.GET)
	public String prices(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductBox_Price> prices = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			prices = boxService.findprices(context, new PageRequest(page,size));
			sumcount = boxService.searchPriceCount(context);
		} else {
			prices = boxService.getPrices(new PageRequest(page, size));
			sumcount = boxService.priceallcount();
		}
		model.addAttribute("prices", prices);
		model.addAttribute("sumcount", sumcount);
		return "productboxes/price";
	}
	
	@RequestMapping(value = "/productboxes/editprice", method = RequestMethod.POST)
	public String editprice(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String boxid = multipartRequest.getParameter("boxid");
		String pid = multipartRequest.getParameter("pid");
		String unitprice= multipartRequest.getParameter("unitprice");
		String pieceprice= multipartRequest.getParameter("pieceprice");
		String dealerUnitprice= multipartRequest.getParameter("dealerUnitprice");
		String dealerPieceprice= multipartRequest.getParameter("dealerPieceprice");
		String retailUnitprice= multipartRequest.getParameter("retailUnitprice");
		int bid = Integer.parseInt(boxid);
		if(StringUtils.isNotBlank(pid)){
			boxService.deletePrice(Integer.parseInt(pid));
		}
		ProductBox_Price price = new ProductBox_Price();
		ProductBox box = boxService.getById(bid);
		price.setBox(box);
		price.setUpdateDate(new Date());
		price.setUnitprice(Double.parseDouble(unitprice));
		price.setPieceprice(Double.parseDouble(pieceprice));
		price.setDealerUnitprice(Double.parseDouble(dealerUnitprice));
		price.setDealerPieceprice(Double.parseDouble(dealerPieceprice));
		price.setRetailUnitprice(Double.parseDouble(retailUnitprice));
		boxService.createPrice(price);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+boxid;
	}
	
	@RequestMapping(value = "/productboxes/editstore", method = RequestMethod.POST)
	public String editstore(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String storecount = multipartRequest.getParameter("storecount");
		String proid = multipartRequest.getParameter("id");
		int propid = Integer.parseInt(proid);
		int count = Integer.parseInt(storecount);
		ProductBox_Store store = boxService.getStoreByPid(propid);
		if(store==null){
			store = new ProductBox_Store();
			store.setCount(count);
			ProductBox box = new ProductBox();
			box.setId(propid);
			store.setBox(box);
			store.setStatus(0);
			
		}
		else{
			store.setCount(count);
		}
		boxService.changeStore(store);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+proid;
	}
	
	@RequestMapping(value = "/productboxes/scheme",method=RequestMethod.GET)
	public String scheme(Model model,@RequestParam(required = true) int pid){
		ProductBoxInScheme scheme = boxService.getScheme(pid);
		model.addAttribute("scheme", scheme);
		model.addAttribute("pid", pid);
		return "productboxes/scheme";
	}
	
	@RequestMapping(value = "/productboxes/newscheme",method=RequestMethod.GET)
	public String newscheme(Model model,@RequestParam(required = true) int pid){
		ProductBox box = boxService.getById(pid);
		model.addAttribute("box", box);
		return "productboxes/newscheme";
	}
	
	@RequestMapping(value = "/productboxes/addscheme", method = RequestMethod.POST)
	public String addScheme(@ModelAttribute("scheme") ProductBoxInScheme scheme, RedirectAttributes redirectAttributes) {

		if (scheme != null) {
			ProductBox box = scheme.getBox();
			ProductBoxInScheme sel = boxService.getScheme(box.getId());
			if(sel!=null){
				boxService.deleteScheme(sel.getId());
			}
			scheme.setDate(new Date());
			int row = boxService.createScheme(scheme);
			if(row<0){
				return "productboxes/addscheme";
			}
			return "redirect:scheme?pid="+box.getId();
		} else {
			return "productboxes/addscheme";
		}

	}
	
	@RequestMapping(value = "/productboxes/store",method=RequestMethod.GET)
	public String stores(Model model,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductBox_Store> stores = null;
		int sumcount = 0;
		stores = boxService.getStores(new PageRequest(page, size));
		sumcount = boxService.storesAllCount();
		model.addAttribute("stores", stores);
		model.addAttribute("sumcount", sumcount);
		return "productboxes/store";
	}
	
	@RequestMapping(value = "/productboxes/in",method=RequestMethod.GET)
	public String productboxin(Model model,@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductBox_In> productBox_Ins = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			productBox_Ins = boxService.findIns(context, new PageRequest(page,size));
			sumcount = boxService.searchInCount(context);
		} else {
			productBox_Ins = boxService.getIns(new PageRequest(page, size));
			sumcount = boxService.inallcount();
		}
		model.addAttribute("ins", productBox_Ins);
		model.addAttribute("sumcount", sumcount);
		return "productboxes/in";
	}
	
	@RequestMapping(value = "/productboxes/newin",method=RequestMethod.GET)
	public String productboxNewin(Model model,@RequestParam(required = true) int pid, RedirectAttributes redirectAttributes){
		ProductBox box = boxService.getById(pid);
		ProductBoxInScheme scheme = boxService.getScheme(pid);
		if(scheme==null){
			redirectAttributes.addFlashAttribute("message", "没有产品入库方案");
			return "redirect:profile?id="+pid;
		}
		model.addAttribute("box", box);
		return "productboxes/newin";
	}
	
	@RequestMapping(value = "/productboxes/inaddone", method = RequestMethod.POST)
	public String inaddOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String count = multipartRequest.getParameter("count");
		String pid = multipartRequest.getParameter("pid");
		String datestr = multipartRequest.getParameter("date");
		
		ProductBox_In productBox_In = new ProductBox_In();
		productBox_In.setCount(Integer.parseInt(count));
		ProductBox box = new ProductBox();
		box.setId(Integer.parseInt(pid));
		productBox_In.setBox(box);
		productBox_In.setDate(DateTools.ParseString(datestr));
		ServiceResult result = boxService.createIn(productBox_In);
		
		if(result.getStatus()<0){
			redirectAttributes.addFlashAttribute("message", result.getMessage());
		}
		else{
			redirectAttributes.addFlashAttribute("success", "入库成功");
		}
		
		return "redirect:profile?id="+pid;
	}
	
	@RequestMapping(value = "/productboxes/boxin",method=RequestMethod.GET)
	public String boxin(Model model,@RequestParam(required = true) String id){
		ProductBox_In in = boxService.getInByid(Integer.parseInt(id));
		List<ProductRaw_Out> rawOuts = boxService.getRawOutsByBoxinId(Integer.parseInt(id));
		List<ProductPack_Out> pack_Outs = boxService.getPackOutsByBoxinId(Integer.parseInt(id));
		List<ProductPage_Out> page_Outs = boxService.getPageOutsByBoxinId(Integer.parseInt(id));
		model.addAttribute("in", in);
		model.addAttribute("rawouts", rawOuts);
		model.addAttribute("packouts", pack_Outs);
		model.addAttribute("pageouts", page_Outs);
		return "productboxes/boxin";
	}
	
	@RequestMapping(value = "/productboxes/out",method=RequestMethod.GET)
	public String productpackout(Model model,@RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductBox_out> box_outs = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			box_outs = boxService.findOuts(context, new PageRequest(page,size));
			sumcount = boxService.searchOutCount(context);
		} else {
			box_outs = boxService.getOuts(new PageRequest(page, size));
			sumcount = boxService.outallcount();
		}
		model.addAttribute("outs", box_outs);
		model.addAttribute("sumcount", sumcount);
		return "productboxes/out";
	}
	
	@RequestMapping(value = "/productboxes/newout",method=RequestMethod.GET)
	public String productboxNewout(Model model,@RequestParam(required = true) int pid, RedirectAttributes redirectAttributes){
		ProductBox box = boxService.getById(pid);
		ProductBox_Price price = boxService.getPriceByPid(pid);
		if(price==null){
			redirectAttributes.addFlashAttribute("message", "产品尚未设定价格，无法出库");
			return "redirect:profile?id="+pid;
		}
		model.addAttribute("box", box);
		return "productboxes/newout";
	}
	
	@RequestMapping(value = "/productboxes/outaddone", method = RequestMethod.POST)
	public String outaddOne(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String count = multipartRequest.getParameter("count");
		String did = multipartRequest.getParameter("did");
		String pid = multipartRequest.getParameter("pid");
		String datestr = multipartRequest.getParameter("date");
		String paystatus = multipartRequest.getParameter("paystatus");
		String amountpaid = multipartRequest.getParameter("amountpaid");
		
		ProductBox_Store box_Store = boxService.getStoreByPid(Integer.parseInt(pid));
		if(box_Store==null){
			redirectAttributes.addFlashAttribute("message", "库存为0，无法出库");
		}
		else{
			if(box_Store.getCount()<Integer.parseInt(count)){
				redirectAttributes.addFlashAttribute("message", "库存不足，无法出库");
			}
			else{
				ProductBox_out box_Out = new ProductBox_out();
				box_Out.setCount(Integer.parseInt(count));
				ProductBox box = new ProductBox();
				box.setId(Integer.parseInt(pid));
				box_Out.setBox(box);
				Dealer dealer = new Dealer();
				dealer.setId(Integer.parseInt(did));
				box_Out.setDealer(dealer);
				box_Out.setDate(DateTools.ParseString(datestr));
				boxService.createOut(box_Out,paystatus,Double.parseDouble(amountpaid));
				
				box_Store.setCount(box_Store.getCount()-Integer.parseInt(count));
				boxService.changeStore(box_Store);
				redirectAttributes.addFlashAttribute("success", "出库成功");
			}
		}
		
		return "redirect:profile?id="+pid;
	}
	
	@RequestMapping(value = "/productboxes/boxout",method=RequestMethod.GET)
	public String boxout(Model model,@RequestParam(required = true) String id){
		ProductBox_out out = boxService.getOutById(Integer.parseInt(id));
		model.addAttribute("out", out);
		return "productboxes/boxout";
	}
	
	@RequestMapping(value = "/productboxes/laborcost",method=RequestMethod.GET)
	public String laborcost(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "10") int size){
		List<ProductLabor_Cost> labor_Costs = null;
		int sumcount = 0;
		if (StringUtils.isNotBlank(context)) {
			labor_Costs = boxService.findLaborCosts(context, new PageRequest(page,size));
			sumcount = boxService.searchLaborCostCount(context);
		} else {
			labor_Costs = boxService.getLaborCosts(new PageRequest(page, size));
			sumcount = boxService.laborCostallcount();
		}
		model.addAttribute("laborcosts", labor_Costs);
		model.addAttribute("sumcount", sumcount);
		return "productboxes/laborcost";
	}
	
	@RequestMapping(value = "/productboxes/editlaborcost", method = RequestMethod.POST)
	public String editlaborcost(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IllegalStateException, IOException, ServletException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String boxid = multipartRequest.getParameter("boxid");
		String pid = multipartRequest.getParameter("pid");
		String pielaborPrice= multipartRequest.getParameter("pielaborPrice");
		String tonlaborPrice= multipartRequest.getParameter("tonlaborPrice");
		
		int bid = Integer.parseInt(boxid);
		if(StringUtils.isNotBlank(pid)){
			boxService.deleteLaborCost(Integer.parseInt(pid));
		}
		ProductLabor_Cost labor_Cost = new ProductLabor_Cost();
		ProductBox box = boxService.getById(bid);
		labor_Cost.setBox(box);
		labor_Cost.setDate(new Date());
		labor_Cost.setPielaborPrice(Double.parseDouble(pielaborPrice));
		labor_Cost.setTonlaborPrice(Double.parseDouble(tonlaborPrice));
		boxService.createLaborCost(labor_Cost);
		redirectAttributes.addFlashAttribute("success", "编辑成功");
		return "redirect:profile?id="+boxid;
	}
	
	@RequestMapping(value = "/productboxes/select",method=RequestMethod.GET)
	public String selectraws(Model model){
		List<BoxNote> notes = boxService.getBoxNotes();
		
		model.addAttribute("notes", notes);
		return "productboxes/select";
	}
	
	@RequestMapping("/productboxes/priceexport")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)
    {
		List<Integer> widths = new ArrayList<Integer>();
		List<List> datas = new ArrayList<List>();
		List<ProductBox_Price> prices = boxService.getPrices(new PageRequest(0, 10000));
		List<String> titles = new ArrayList<String>();
		titles.add("序号");
		widths.add(2000);
		titles.add("产品名称");
		widths.add(4000);
		titles.add("每单位产品出厂价格");
		widths.add(5000);
		titles.add("每件产品出厂价格");
		widths.add(5000);
		titles.add("每单位产品经销商价格");
		widths.add(5000);
		titles.add("每件产品经销商价格");
		widths.add(5000);
		titles.add("每单位产品零售价格");
		widths.add(5000);
		titles.add("更新日期");
		widths.add(5000);
		datas.add(titles);
		if(prices!=null && prices.size()>0){
			for (int i=0;i<prices.size();i++) {
				ProductBox_Price price = prices.get(i);
				List<String> values = new ArrayList<String>();
				values.add((i+1)+"");
				values.add(price.getBox()==null?"":price.getBox().getName());
				values.add(price.getUnitprice()==null?"":price.getUnitprice().toString());
				values.add(price.getPieceprice()==null?"":price.getPieceprice().toString());
				values.add(price.getDealerUnitprice()==null?"":price.getDealerUnitprice().toString());
				values.add(price.getDealerPieceprice()==null?"":price.getDealerPieceprice().toString());
				values.add(price.getRetailUnitprice()==null?"":price.getRetailUnitprice().toString());
				values.add(price.getUpdateDate()==null?"":DateTools.FormateDate(price.getUpdateDate()));
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
            codedFileName = java.net.URLEncoder.encode("产品价格表", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = ExcelUtil.exportExcel(datas, widths, "产品价格",null);
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
