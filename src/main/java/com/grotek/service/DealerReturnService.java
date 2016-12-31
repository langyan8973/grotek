package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.DealerReturnDic;


public interface DealerReturnService {

	public List<DealerReturnDic> getDealerReturnDics(PageRequest pageable);
	
	public int allCount();

	public DealerReturnDic getById(int id);
	
	public int addDealerReturnDic(DealerReturnDic dealerReturnDic);
	
	public int editDealerReturnDic(DealerReturnDic dealerReturnDic);
	
	public int deleteDealerReturnDic(int id);
	
}
