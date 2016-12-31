package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.DealerValueMapper;
import com.grotek.pojo.DealerValue;
import com.grotek.service.DealerValueService;
@Service("dealervalueService")
@org.springframework.transaction.annotation.Transactional
public class DealerValueServiceImp implements DealerValueService {
	
	@Autowired
	private DealerValueMapper valueDao;

	@Override
	public List<DealerValue> findDealerValues(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return valueDao.findDealerValues(text, pageable);
	}

	@Override
	public List<DealerValue> getDealerValues(PageRequest pageable) {
		// TODO Auto-generated method stub
		return valueDao.getDealerValues(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return valueDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return valueDao.allCount();
	}

	@Override
	public DealerValue getById(int id) {
		// TODO Auto-generated method stub
		return valueDao.selectByPrimaryKey(id);
	}

	@Override
	public List<DealerValue> getByDid(int did, PageRequest pageable) {
		// TODO Auto-generated method stub
		return valueDao.getByDid(did, pageable);
	}

	@Override
	public int addDealerValue(DealerValue dealerValue) {
		// TODO Auto-generated method stub
		dealerValue.setStatus(0);
		return valueDao.insert(dealerValue);
	}

	@Override
	public int editDealerValue(DealerValue dealerValue) {
		// TODO Auto-generated method stub
		return valueDao.updateByPrimaryKey(dealerValue);
	}

	@Override
	public int deleteDealerValue(int id) {
		// TODO Auto-generated method stub
		return valueDao.deleteByPrimaryKey(id);
	}

	@Override
	public int allCountByDid(int did) {
		// TODO Auto-generated method stub
		return valueDao.allCountByDid(did);
	}

}
