package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.DealerPrice;

public interface DealerPriceService {

	public List<DealerPrice> findDealerPrices(String text,PageRequest pageable);
	
	public List<DealerPrice> getDealerPrices(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public DealerPrice getById(int id);
	
	public List<DealerPrice> getByPid(int pid,PageRequest pageable);
	
	public List<DealerPrice> findByDid(int did,String text,PageRequest pageable);
	
	public List<DealerPrice> getByDid(int did,PageRequest pageable);
	
	public int searchCountByDid(int did,String text);
	
	public int allCountByDid(int did);
	
	public DealerPrice getByPidAndDid(int pid,int did);
	
	public int createDealerPrice(DealerPrice dealerPrice);
	
}
