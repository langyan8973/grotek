package com.grotek.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ProductLabor_Time;

public interface ProductLaborTimeService {

	public List<ProductLabor_Time> findProductLabor_Times(String text,PageRequest pageable);
	
	public List<ProductLabor_Time> getProductLabor_Times(PageRequest pageable);
	
	public int searchProductLabor_TimeCount(String text);
	
	public int allProductLabor_TimeCount();

	public int addProductLabor_Time(ProductLabor_Time productLabor_Time);
	
	public List<ProductLabor_Time> getProductLabor_TimeByPid(int pid,PageRequest pageable);
	
	public ProductLabor_Time getProductLabor_TimeById(int id);
	
	public int editProductLabor_Time(ProductLabor_Time productLabor_Time);
	
	public int deleteProductLabor_Time(int id);
	
	public List<List> staticByMonth(Date start,Date end);
	
}
