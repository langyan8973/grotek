package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.DealerSaleMapper;
import com.grotek.pojo.DealerSale;
import com.grotek.service.DealerSaleService;
@Service("dealerSaleService")
@org.springframework.transaction.annotation.Transactional
public class DealerSaleServiceImp implements DealerSaleService {

	@Autowired
	private DealerSaleMapper saleDao;
	
	@Override
	public List<DealerSale> findDealerSales(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return saleDao.findDealerSales(text, pageable);
	}

	@Override
	public List<DealerSale> getDealerSales(PageRequest pageable) {
		// TODO Auto-generated method stub
		return saleDao.getDealerSales(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return saleDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return saleDao.allCount();
	}

	@Override
	public DealerSale getById(int id) {
		// TODO Auto-generated method stub
		return saleDao.selectByPrimaryKey(id);
	}

	@Override
	public DealerSale getByOutId(int outid) {
		// TODO Auto-generated method stub
		return saleDao.getByOutId(outid);
	}

	@Override
	public int editDealerSale(DealerSale dealerSale) {
		// TODO Auto-generated method stub
		return saleDao.updateByPrimaryKey(dealerSale);
	}

}
