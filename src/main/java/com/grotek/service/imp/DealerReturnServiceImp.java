package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.DealerReturnDicMapper;
import com.grotek.pojo.DealerReturnDic;
import com.grotek.service.DealerReturnService;
@Service("dealerreturnService")
@org.springframework.transaction.annotation.Transactional
public class DealerReturnServiceImp implements DealerReturnService {

	@Autowired
	private DealerReturnDicMapper returnDao;
	
	@Override
	public List<DealerReturnDic> getDealerReturnDics(PageRequest pageable) {
		// TODO Auto-generated method stub
		return returnDao.getDealerReturnDics(pageable);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return returnDao.allCount();
	}

	@Override
	public DealerReturnDic getById(int id) {
		// TODO Auto-generated method stub
		return returnDao.selectByPrimaryKey(id);
	}

	@Override
	public int addDealerReturnDic(DealerReturnDic dealerReturnDic) {
		// TODO Auto-generated method stub
		DealerReturnDic dealerReturnDic2 = returnDao.getByPurchases(dealerReturnDic.getPurchases());
		if(dealerReturnDic2!=null){
			returnDao.deleteByPrimaryKey(dealerReturnDic2.getId());
		}
		dealerReturnDic.setStatus(0);
		
		return returnDao.insert(dealerReturnDic);
	}

	@Override
	public int editDealerReturnDic(DealerReturnDic dealerReturnDic) {
		// TODO Auto-generated method stub
		return returnDao.updateByPrimaryKey(dealerReturnDic);
	}

	@Override
	public int deleteDealerReturnDic(int id) {
		// TODO Auto-generated method stub
		return returnDao.deleteByPrimaryKey(id);
	}

}
