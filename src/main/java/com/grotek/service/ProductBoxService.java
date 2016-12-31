package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.dto.BoxNote;
import com.grotek.dto.ServiceResult;
import com.grotek.pojo.Employee;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductBoxInScheme;
import com.grotek.pojo.ProductBox_In;
import com.grotek.pojo.ProductBox_Price;
import com.grotek.pojo.ProductBox_Store;
import com.grotek.pojo.ProductBox_out;
import com.grotek.pojo.ProductLabor_Cost;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductRaw_Out;


public interface ProductBoxService {
	
	public List<ProductBox> findProductBoxs(String text,PageRequest pageable);
	
	public List<ProductBox> getProductBoxs(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public int addProductBox(ProductBox productBox);
	
	public int check(String code);
	
	public ProductBox getById(int id);
	
	public int editProductBox(ProductBox productBox);
	
	public int deleteOne(int id);
	
	
	public List<ProductBox_Price> findprices(String text,PageRequest pageable);
	
	public List<ProductBox_Price> getPrices(PageRequest pageable);
	
	public int searchPriceCount(String text);
	
	public int priceallcount();
	
	public ProductBox_Price getPriceByPid(int pid);
	
	public int deletePrice(int id);
	
	public int createPrice(ProductBox_Price price);
	
	
	public ProductBoxInScheme getScheme(int pid);
	
	public int deleteScheme(int id);
	
	public int createScheme(ProductBoxInScheme inScheme);
	
	
	public List<ProductBox_Store> getStores(PageRequest pageable);
	
	public int storesAllCount();
	
	public ProductBox_Store getStoreByPid(int pid);
	
	public int changeStore(ProductBox_Store store);
	
	
	public List<ProductBox_In> findIns(String text,PageRequest pageable);
	
	public List<ProductBox_In> getIns(PageRequest pageable);
	
	public int searchInCount(String text);
	
	public int inallcount();
	
	public List<ProductBox_In> getInByPid(int pid);
	
	public ServiceResult createIn(ProductBox_In in);
	
	public ProductBox_In getInByid(int id);
	
	public List<ProductRaw_Out> getRawOutsByBoxinId(int boxinid);
	
	public List<ProductPack_Out> getPackOutsByBoxinId(int boxinid);
	
	public List<ProductPage_Out> getPageOutsByBoxinId(int boxinid);
	
	
	public List<ProductBox_out> findOuts(String text,PageRequest pageable);
	
	public List<ProductBox_out> getOuts(PageRequest pageable);
	
	public int searchOutCount(String text);
	
	public int outallcount();
	
	public List<ProductBox_out> findOutsBydid(int did,String text,PageRequest pageable);
	
	public List<ProductBox_out> getOutsBydid(int did,PageRequest pageable);
	
	public int searchOutCountBydid(int did,String text);
	
	public int outallcountBydid(int did);
	
	public List<ProductBox_out> getOutByPid(int pid);
	
	public int createOut(ProductBox_out out,String paystatus,double amount);
	
	public int createSampleOut(ProductBox_out out,String paystatus);
	
	public ProductBox_out getOutById(int id);
	
	
	public List<ProductLabor_Cost> findLaborCosts(String text,PageRequest pageable);
	
	public List<ProductLabor_Cost> getLaborCosts(PageRequest pageable);
	
	public int searchLaborCostCount(String text);
	
	public int laborCostallcount();
	
	public ProductLabor_Cost getLaborCostByPid(int pid);
	
	public int deleteLaborCost(int id);
	
	public int createLaborCost(ProductLabor_Cost laborcost);
	
	public List<BoxNote> getBoxNotes();
	
	public int addEmployeeFee(ProductBox_out out,Employee employee);
}
