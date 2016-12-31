package com.grotek.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grotek.dto.BoxNote;
import com.grotek.dto.DealersNote;
import com.grotek.dto.DistrictDto;
import com.grotek.dto.PackNote;
import com.grotek.dto.PageNote;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerPrice;
import com.grotek.pojo.District;
import com.grotek.pojo.ExsType;
import com.grotek.pojo.JobtypeDic;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductBox_Price;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPage;
import com.grotek.pojo.TravelExsType;
import com.grotek.service.DealerPriceService;
import com.grotek.service.DealerService;
import com.grotek.service.DistrictService;
import com.grotek.service.ExsTypeService;
import com.grotek.service.JobTypeService;
import com.grotek.service.ProductBoxService;
import com.grotek.service.ProductPackService;
import com.grotek.service.ProductPageService;
import com.grotek.service.TravelExsTypeService;

@Controller
@RequestMapping("/work")
public class SelectsController {
	
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private ProductBoxService boxService;
	
	@Autowired
	private ProductPackService packService;
	
	@Autowired
	private ProductPageService pageService;
	
	@Autowired
	private DealerPriceService dealerPriceService; 
	
	@Autowired
	private JobTypeService jobtypeService;
	
	@Autowired
	private TravelExsTypeService travelexstypeService;
	
	@Autowired
	private ExsTypeService exstypeService;
	
	@Autowired
	private DistrictService districtService;

	@RequestMapping(value = "/selects/dealers",method=RequestMethod.GET)
	public String dealers(Model model){
		
		List<DealersNote> dealersNotes = dealerService.getHzDealerNotes();
		
		model.addAttribute("notes", dealersNotes);
		return "selects/dealers";
	}
	
	@RequestMapping(value = "/selects/boxes",method=RequestMethod.GET)
	public String boxes(Model model){
		
		List<BoxNote> notes = boxService.getBoxNotes();
		
		model.addAttribute("notes", notes);
		return "selects/boxes";
	}
	
	@RequestMapping(value = "/selects/samples",method=RequestMethod.GET)
	public String samples(Model model){
		
		List<BoxNote> notes = boxService.getBoxNotes();
		
		model.addAttribute("notes", notes);
		return "selects/samples";
	}
	
	@RequestMapping(value = "/selects/pages",method=RequestMethod.GET)
	public String pages(Model model){
		
		List<PageNote> notes = pageService.getPageNotes();
		
		model.addAttribute("notes", notes);
		return "selects/pages";
	}
	
	@RequestMapping(value = "/selects/packs",method=RequestMethod.GET)
	public String packs(Model model){
		
		List<PackNote> notes = packService.getPackNotes();
		
		model.addAttribute("notes", notes);
		return "selects/packs";
	}
	
	@RequestMapping(value = "/selects/jobtypes",method=RequestMethod.GET)
	public String jobtypes(Model model){
		
		List<JobtypeDic> types = jobtypeService.getAll();
		
		model.addAttribute("types", types);
		return "selects/jobtypes";
	}
	
	@RequestMapping(value = "/selects/traveltypes",method=RequestMethod.GET)
	public String traveltypes(Model model){
		
		List<TravelExsType> types = travelexstypeService.getAll();
		
		model.addAttribute("types", types);
		return "selects/traveltypes";
	}
	
	@RequestMapping(value = "/selects/exstypes",method=RequestMethod.GET)
	public String exstypes(Model model){
		
		List<ExsType> types = exstypeService.getAll();
		
		model.addAttribute("types", types);
		return "selects/exstypes";
	}
	
	@RequestMapping(value = "/selects/dealerprice", method = RequestMethod.POST)
	@ResponseBody
	public String dealerprice(@RequestParam(required = true) Integer did,@RequestParam(required = true) Integer bid){
		String price="";
		DealerPrice dealerPrice = dealerPriceService.getByPidAndDid(bid, did);
		if(dealerPrice==null){
			ProductBox_Price box_Price = boxService.getPriceByPid(bid);
			if(box_Price!=null){
				price = box_Price.getPieceprice().toString();
			}	
		}
		else{
			price = dealerPrice.getPrice().toString();
		}
		
		return price;
	}
	
	@RequestMapping(value = "/selects/dealerinfo", method = RequestMethod.POST)
	@ResponseBody
	public Dealer dealerinfo(@RequestParam(required = true) Integer id){
		Dealer dealer = dealerService.getById(id);
		return dealer;
	}
	
	@RequestMapping(value = "/selects/districts",method=RequestMethod.GET)
	public String districts(Model model){
		
		List<DistrictDto> districts = districtService.getDistrictTree();
		model.addAttribute("districts", districts);
		return "selects/districts";
	}
}
