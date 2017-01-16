package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.ProductRawMapper;
import com.grotek.dao.ProductRaw_InMapper;
import com.grotek.dao.ProductRaw_OutMapper;
import com.grotek.dao.ProductRaw_PriceMapper;
import com.grotek.dao.ProductRaw_StoreMapper;
import com.grotek.pojo.ProductRaw;
import com.grotek.pojo.ProductRaw_In;
import com.grotek.pojo.ProductRaw_Out;
import com.grotek.pojo.ProductRaw_Price;
import com.grotek.pojo.ProductRaw_Store;
import com.grotek.service.ProductRawService;
@Service("productRawService")
@org.springframework.transaction.annotation.Transactional
public class ProductRawServiceImp implements ProductRawService {

	@Autowired
	private ProductRawMapper productRawDao;
	
	@Autowired
	private ProductRaw_PriceMapper productRaw_PriceDao;
	
	@Autowired
	private ProductRaw_StoreMapper productRaw_StoreDao;
	
	@Autowired
	private ProductRaw_InMapper productRaw_InDao;
	
	@Autowired
	private ProductRaw_OutMapper productRaw_OutDao;
	
	@Override
	public List<ProductRaw> findProductRaws(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRawDao.findProductRaws(text, pageable);
	}

	@Override
	public List<ProductRaw> getProductRaws(PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRawDao.getProductRaws(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return productRawDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return productRawDao.allCount();
	}

	@Override
	public int addProductRaw(ProductRaw productRaw) {
		// TODO Auto-generated method stub
		productRaw.setStatus(0);
		return productRawDao.insert(productRaw);
	}

	@Override
	public int check(String name, String code) {
		// TODO Auto-generated method stub
		return productRawDao.checkNameAndCode(name, code);
	}

	@Override
	public ProductRaw getById(int id) {
		// TODO Auto-generated method stub
		return productRawDao.selectByPrimaryKey(id);
	}

	@Override
	public int editProductRaw(ProductRaw productRaw) {
		// TODO Auto-generated method stub
		return productRawDao.updateByPrimaryKey(productRaw);
	}

	@Override
	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return productRawDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<ProductRaw_Price> findprices(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRaw_PriceDao.findProductRawPrices(text, pageable);
	}

	@Override
	public List<ProductRaw_Price> getPrices(PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRaw_PriceDao.getProductRawPrices(pageable);
	}

	@Override
	public int searchPriceCount(String text) {
		// TODO Auto-generated method stub
		return productRaw_PriceDao.searchCount(text);
	}

	@Override
	public int priceallcount() {
		// TODO Auto-generated method stub
		return productRaw_PriceDao.allCount();
	}

	@Override
	public ProductRaw_Price getPriceByPid(int pid) {
		// TODO Auto-generated method stub
		return productRaw_PriceDao.getByPid(pid);
	}

	@Override
	public int deletePrice(int id) {
		// TODO Auto-generated method stub
		return productRaw_PriceDao.deleteByPrimaryKey(id);
	}

	@Override
	public int createPrice(ProductRaw_Price price) {
		// TODO Auto-generated method stub
		price.setStatus(0);
		return productRaw_PriceDao.insert(price);
	}

	@Override
	public List<ProductRaw_Store> getStores(PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRaw_StoreDao.getProductRawStores(pageable);
	}

	@Override
	public int storesAllCount() {
		// TODO Auto-generated method stub
		return productRaw_StoreDao.allCount();
	}

	@Override
	public ProductRaw_Store getStoreByPid(int pid) {
		// TODO Auto-generated method stub
		return productRaw_StoreDao.getByPid(pid);
	}

	@Override
	public int changeStore(ProductRaw_Store store) {
		// TODO Auto-generated method stub
		ProductRaw_Store store2 = productRaw_StoreDao.getByPid(store.getProductRaw().getId());
		if(store2==null){
			store.setStatus(0);
			productRaw_StoreDao.insert(store);
		}
		else{
			store2.setCount(store.getCount());
			productRaw_StoreDao.updateByPrimaryKey(store2);
		}
		return 0;
	}

	@Override
	public List<ProductRaw_In> findIns(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRaw_InDao.findProductRawIns(text, pageable);
	}

	@Override
	public List<ProductRaw_In> getIns(PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRaw_InDao.getProductRawIns(pageable);
	}

	@Override
	public int searchInCount(String text) {
		// TODO Auto-generated method stub
		return productRaw_InDao.searchCount(text);
	}

	@Override
	public int inallcount() {
		// TODO Auto-generated method stub
		return productRaw_InDao.allCount();
	}

	@Override
	public List<ProductRaw_In> getInByPid(int pid) {
		// TODO Auto-generated method stub
		return productRaw_InDao.getByPid(pid);
	}

	@Override
	public int createIn(ProductRaw_In in) {
		// TODO Auto-generated method stub
		in.setStatus(0);
		return productRaw_InDao.insert(in);
	}

	@Override
	public List<ProductRaw_Out> findOuts(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRaw_OutDao.findProductRawOuts(text, pageable);
	}

	@Override
	public List<ProductRaw_Out> getOuts(PageRequest pageable) {
		// TODO Auto-generated method stub
		return productRaw_OutDao.getProductRawOuts(pageable);
	}

	@Override
	public int searchOutCount(String text) {
		// TODO Auto-generated method stub
		return productRaw_OutDao.searchCount(text);
	}

	@Override
	public int outallcount() {
		// TODO Auto-generated method stub
		return productRaw_OutDao.allCount();
	}

	@Override
	public List<ProductRaw_Out> getOutByPid(int pid) {
		// TODO Auto-generated method stub
		return productRaw_OutDao.getByPid(pid);
	}

	@Override
	public int createOut(ProductRaw_Out out) {
		// TODO Auto-generated method stub
		out.setStatus(0);
		return productRaw_OutDao.insert(out);
	}

	@Override
	public List<ProductRaw_Store> findProductKcun(String text, PageRequest pageable) {
		
		return productRaw_StoreDao.findProductKcun(text, pageable);
	}

	@Override
	public int searchkuncCount(String text) {
		// TODO Auto-generated method stub
		return productRaw_StoreDao.searchCount(text);
	}


}
