package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.dto.PageNote;
import com.grotek.pojo.Employee;
import com.grotek.pojo.PageType;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPage;
import com.grotek.pojo.ProductPage_In;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductPage_Store;

public interface ProductPageService {
	
	public List<ProductPage> findProductPages(String text,PageRequest pageable);
	
	public List<ProductPage> getProductPages(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public int addProductPage(ProductPage productPage);
	
	public int check(String name,String code);
	
	public ProductPage getById(int id);
	
	public int editProductPage(ProductPage productPage);
	
	public int deleteOne(int id);
	

	public List<PageType> getTypes();
	
	public int checkTypeName(String name);
	
	public int addType(PageType type);
	
	public int editType(PageType type);
	
	public int deleteType(int id);
	
	public PageType getTypeById(int id);
	
	
	public List<ProductPage_Store> getStores(PageRequest pageable);
	
	public int storesAllCount();
	
	public ProductPage_Store getStoreByPid(int pid);
	
	public int changeStore(ProductPage_Store store);
	
	
	public List<ProductPage_In> findIns(String text,PageRequest pageable);
	
	public List<ProductPage_In> getIns(PageRequest pageable);
	
	public int searchInCount(String text);
	
	public int inallcount();
	
	public List<ProductPage_In> getInByPid(int pid);
	
	public int createIn(ProductPage_In in);
	
	
	public List<ProductPage_Out> findOuts(String text,PageRequest pageable);
	
	public List<ProductPage_Out> getOuts(PageRequest pageable);
	
	public int searchOutCount(String text);
	
	public int outallcount();
	
	public List<ProductPage_Out> getOutByPid(int pid);
	
	public int createOut(ProductPage_Out out);
	
	public int addEmployeeFee(ProductPage_Out out,Employee employee);
	
	public List<PageNote> getPageNotes();
}
