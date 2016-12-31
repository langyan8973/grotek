package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.DealerPriceMapper;
import com.grotek.pojo.DealerPrice;
import com.grotek.service.DealerPriceService;
@Service("dealerpriceService")
@org.springframework.transaction.annotation.Transactional
public class DealerPriceServiceImp implements DealerPriceService {
	
	@Autowired
	private DealerPriceMapper priceDao;

	@Override
	public List<DealerPrice> findDealerPrices(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return priceDao.findDealerPrices(text, pageable);
	}

	@Override
	public List<DealerPrice> getDealerPrices(PageRequest pageable) {
		// TODO Auto-generated method stub
		return priceDao.getDealerPrices(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return priceDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return priceDao.allCount();
	}

	@Override
	public DealerPrice getById(int id) {
		// TODO Auto-generated method stub
		return priceDao.selectByPrimaryKey(id);
	}

	@Override
	public List<DealerPrice> getByPid(int pid, PageRequest pageable) {
		// TODO Auto-generated method stub
		return priceDao.getByPid(pid, pageable);
	}

	@Override
	public List<DealerPrice> getByDid(int did, PageRequest pageable) {
		// TODO Auto-generated method stub
		return priceDao.getByDid(did, pageable);
	}

	@Override
	public DealerPrice getByPidAndDid(int pid, int did) {
		// TODO Auto-generated method stub
		return priceDao.getByPidAndDid(did, pid);
	}

	@Override
	public int createDealerPrice(DealerPrice dealerPrice) {
		// TODO Auto-generated method stub
		DealerPrice dealerPrice2 = priceDao.getByPidAndDid(dealerPrice.getDealer().getId(), dealerPrice.getBox().getId());
		if(dealerPrice2!=null){
			priceDao.deleteByPrimaryKey(dealerPrice2.getId());
		}
		dealerPrice.setStatus(0);
		int i = priceDao.insert(dealerPrice);
		return i;
	}

	@Override
	public List<DealerPrice> findByDid(int did, String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return priceDao.findByDid(did, text, pageable);
	}

	@Override
	public int searchCountByDid(int did, String text) {
		// TODO Auto-generated method stub
		return priceDao.searchCountByDid(did, text);
	}

	@Override
	public int allCountByDid(int did) {
		// TODO Auto-generated method stub
		return priceDao.allCountByDid(did);
	}

}
