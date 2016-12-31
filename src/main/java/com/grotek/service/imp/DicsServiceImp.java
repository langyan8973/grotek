package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.CreditRatingDicMapper;
import com.grotek.dao.ReputationDicMapper;
import com.grotek.dao.ShopTypeDicMapper;
import com.grotek.dao.StrengthDicMapper;
import com.grotek.pojo.CreditRatingDic;
import com.grotek.pojo.ReputationDic;
import com.grotek.pojo.ShopTypeDic;
import com.grotek.pojo.StrengthDic;
import com.grotek.service.DicsService;
@Service("dicsService")
@org.springframework.transaction.annotation.Transactional
public class DicsServiceImp implements DicsService {
	
	@Autowired
	private CreditRatingDicMapper creditRatingDicDao;
	
	@Autowired
	private ReputationDicMapper reputationDicDao;
	
	@Autowired
	private ShopTypeDicMapper shopTypeDicDao;
	
	@Autowired
	private StrengthDicMapper strengthDicDao;

	@Override
	public List<CreditRatingDic> getCreditRatingDics() {
		// TODO Auto-generated method stub
		return creditRatingDicDao.getAll();
	}

	@Override
	public CreditRatingDic getCreditRatingDicById(int id) {
		// TODO Auto-generated method stub
		return creditRatingDicDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ReputationDic> getReputationDics() {
		// TODO Auto-generated method stub
		return reputationDicDao.getAll();
	}

	@Override
	public ReputationDic getReputationDicById(int id) {
		// TODO Auto-generated method stub
		return reputationDicDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ShopTypeDic> getShopTypeDics() {
		// TODO Auto-generated method stub
		return shopTypeDicDao.getAll();
	}

	@Override
	public ShopTypeDic getShopTypeDicById(int id) {
		// TODO Auto-generated method stub
		return shopTypeDicDao.selectByPrimaryKey(id);
	}

	@Override
	public List<StrengthDic> getStrengthDics() {
		// TODO Auto-generated method stub
		return strengthDicDao.getAll();
	}

	@Override
	public StrengthDic getStrengthDicById(int id) {
		// TODO Auto-generated method stub
		return strengthDicDao.selectByPrimaryKey(id);
	}

}
