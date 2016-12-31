package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.DealerValue;

public interface DealerValueService {

	public List<DealerValue> findDealerValues(String text,PageRequest pageable);
	
	public List<DealerValue> getDealerValues(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public DealerValue getById(int id);
	
	public List<DealerValue> getByDid(int did,PageRequest pageable);
	
	public int allCountByDid(int did);
	
	public int addDealerValue(DealerValue dealerValue);
	
	public int editDealerValue(DealerValue dealerValue);
	
	public int deleteDealerValue(int id);
	
}
