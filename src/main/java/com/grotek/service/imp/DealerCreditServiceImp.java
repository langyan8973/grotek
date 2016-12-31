package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.DealerCreditDicMapper;
import com.grotek.pojo.DealerCreditDic;
import com.grotek.service.DealerCreditService;
@Service("dealercreditService")
@org.springframework.transaction.annotation.Transactional
public class DealerCreditServiceImp implements DealerCreditService {

	@Autowired
	private DealerCreditDicMapper creditDao;
	
	@Override
	public List<DealerCreditDic> findDealerCreditDics(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return creditDao.findDealerCreditDics(text, pageable);
	}

	@Override
	public List<DealerCreditDic> getDealerCreditDics(PageRequest pageable) {
		// TODO Auto-generated method stub
		return creditDao.getDealerCreditDics(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return creditDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return creditDao.allCount();
	}

	@Override
	public DealerCreditDic getById(int id) {
		// TODO Auto-generated method stub
		return creditDao.selectByPrimaryKey(id);
	}

	@Override
	public int addDealerCreditDic(DealerCreditDic dealerCreditDic) {
		// TODO Auto-generated method stub
		DealerCreditDic dealerCreditDic2 = creditDao.getBySid(dealerCreditDic.getType().getId());
		if(dealerCreditDic2!=null){
			creditDao.deleteByPrimaryKey(dealerCreditDic2.getId());
		}
		dealerCreditDic.setStatus(0);
		return creditDao.insert(dealerCreditDic);
	}

	@Override
	public int editDealerCreditDic(DealerCreditDic dealerCreditDic) {
		// TODO Auto-generated method stub
		return creditDao.updateByPrimaryKey(dealerCreditDic);
	}

	@Override
	public int deleteDealerCreditDic(int id) {
		// TODO Auto-generated method stub
		return creditDao.deleteByPrimaryKey(id);
	}

}
