package com.grotek.service;

import java.util.List;

import com.grotek.pojo.CreditRatingDic;
import com.grotek.pojo.ReputationDic;
import com.grotek.pojo.ShopTypeDic;
import com.grotek.pojo.StrengthDic;

public interface DicsService {

	public List<CreditRatingDic> getCreditRatingDics();
	
	public CreditRatingDic getCreditRatingDicById(int id);
	
	public List<ReputationDic> getReputationDics();
	
	public ReputationDic getReputationDicById(int id);
	
	public List<ShopTypeDic> getShopTypeDics();
	
	public ShopTypeDic getShopTypeDicById(int id);
	
	public List<StrengthDic> getStrengthDics();
	
	public StrengthDic getStrengthDicById(int id);
	
}
