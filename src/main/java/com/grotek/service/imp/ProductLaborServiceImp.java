package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.ProductLaborMapper;
import com.grotek.dao.ProductLabor_TimeMapper;
import com.grotek.pojo.ProductLabor;
import com.grotek.pojo.ProductLabor_Time;
import com.grotek.service.ProductLaborService;
@Service("productlaborService")
@org.springframework.transaction.annotation.Transactional
public class ProductLaborServiceImp implements ProductLaborService {

	@Autowired
	private ProductLaborMapper laborDao;
	
	@Override
	public List<ProductLabor> findProductLabors(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return laborDao.findProductLabors(text, pageable);
	}

	@Override
	public List<ProductLabor> getProductLabors(PageRequest pageable) {
		// TODO Auto-generated method stub
		return laborDao.getProductLabors(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return laborDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return laborDao.allCount();
	}

	@Override
	public int addProductLabor(ProductLabor productLabor) {
		// TODO Auto-generated method stub
		productLabor.setStatus(0);
		return laborDao.insert(productLabor);
	}

	@Override
	public int check(String code) {
		// TODO Auto-generated method stub
		return laborDao.checkCode(code);
	}

	@Override
	public ProductLabor getById(int id) {
		// TODO Auto-generated method stub
		return laborDao.selectByPrimaryKey(id);
	}

	@Override
	public int editProductLabor(ProductLabor productLabor) {
		// TODO Auto-generated method stub
		return laborDao.updateByPrimaryKey(productLabor);
	}

	@Override
	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return laborDao.deleteByPrimaryKey(id);
	}

}
