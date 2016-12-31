package com.grotek.service;

import com.grotek.pojo.DeliveryOrder;
import com.grotek.pojo.Employee;
import com.grotek.pojo.ProductBox_Store;
import com.grotek.pojo.ProductBox_out;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductPage_Store;

public interface DeliveryOrderService {

	public DeliveryOrder getById(int id);
	
	public DeliveryOrder getByAid(int aid);
	
	public int addDeliveryOrder(DeliveryOrder deliveryOrder);
	
	public int deleteById(int id);
	
	public int editDeliveryOrder(DeliveryOrder deliveryOrder);
	
	public int saveDeliveryOrder(DeliveryOrder deliveryOrder);
	
	public int createOut(ProductBox_out out,String paystatus,double amount);
	
	public int boxChangeStore(ProductBox_Store store);
	
	public int addBoxEmployeeFee(ProductBox_out out, Employee employee);
	
	public int packChangeStore(ProductPack_Store store);
	
	public int addPackEmployeeFee(ProductPack_Out out, Employee employee);
	
	public int pageChangeStore(ProductPage_Store store);
	
	public int addPageEmployeeFee(ProductPage_Out out, Employee employee);
	
}
