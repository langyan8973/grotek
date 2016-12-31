package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.DealerSale;


public interface DealerSaleService {

	public List<DealerSale> findDealerSales(String text,PageRequest pageable);
	
	public List<DealerSale> getDealerSales(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public DealerSale getById(int id);
	
	public DealerSale getByOutId(int outid);
	
	public int editDealerSale(DealerSale dealerSale);
	
}
