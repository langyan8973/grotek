package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import com.grotek.pojo.ProductRaw;
import com.grotek.pojo.ProductRaw_In;
import com.grotek.pojo.ProductRaw_Out;
import com.grotek.pojo.ProductRaw_Price;
import com.grotek.pojo.ProductRaw_Store;

public interface ProductRawService {

	public List<ProductRaw> findProductRaws(String text,PageRequest pageable);
	
	public List<ProductRaw> getProductRaws(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public int addProductRaw(ProductRaw productRaw);
	
	public int check(String name,String code);
	
	public ProductRaw getById(int id);
	
	public int editProductRaw(ProductRaw productRaw);
	
	public int deleteOne(int id);
	
	
	public List<ProductRaw_Price> findprices(String text,PageRequest pageable);
	
	public List<ProductRaw_Price> getPrices(PageRequest pageable);
	
	public int searchPriceCount(String text);
	
	public int priceallcount();
	
	public ProductRaw_Price getPriceByPid(int pid);
	
	public int deletePrice(int id);
	
	public int createPrice(ProductRaw_Price price);
	
	
	public List<ProductRaw_Store> getStores(PageRequest pageable);
	
	public int storesAllCount();
	
	public ProductRaw_Store getStoreByPid(int pid);
	
	public int changeStore(ProductRaw_Store store);
	
	
	public List<ProductRaw_In> findIns(String text,PageRequest pageable);
	
	public List<ProductRaw_In> getIns(PageRequest pageable);
	
	public int searchInCount(String text);
	
	public int inallcount();
	
	public List<ProductRaw_In> getInByPid(int pid);
	
	public int createIn(ProductRaw_In in);
	
	
	public List<ProductRaw_Out> findOuts(String text,PageRequest pageable);
	
	public List<ProductRaw_Out> getOuts(PageRequest pageable);
	
	public int searchOutCount(String text);
	
	public int outallcount();
	
	public List<ProductRaw_Out> getOutByPid(int pid);
	
	public int createOut(ProductRaw_Out out);
}
