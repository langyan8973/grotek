package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ProductLabor;
import com.grotek.pojo.ProductLabor_Time;


public interface ProductLaborService {

	public List<ProductLabor> findProductLabors(String text,PageRequest pageable);
	
	public List<ProductLabor> getProductLabors(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public int addProductLabor(ProductLabor productLabor);
	
	public int check(String code);
	
	public ProductLabor getById(int id);
	
	public int editProductLabor(ProductLabor productLabor);
	
	public int deleteOne(int id);
	
}
