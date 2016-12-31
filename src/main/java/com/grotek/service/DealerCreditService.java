package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.DealerCreditDic;


public interface DealerCreditService {

	public List<DealerCreditDic> findDealerCreditDics(String text,PageRequest pageable);
	
	public List<DealerCreditDic> getDealerCreditDics(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public DealerCreditDic getById(int id);
	
	public int addDealerCreditDic(DealerCreditDic dealerCreditDic);
	
	public int editDealerCreditDic(DealerCreditDic dealerCreditDic);
	
	public int deleteDealerCreditDic(int id);
	
}
