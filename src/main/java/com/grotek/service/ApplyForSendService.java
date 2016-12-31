package com.grotek.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.ProductPackApItem;
import com.grotek.pojo.ProductPageApItem;
import com.grotek.pojo.ProductboxApItem;
import com.grotek.pojo.SampleApItem;

public interface ApplyForSendService {

	public List<ApplyForSend> findByEid(String eid,String key,PageRequest pageRequest);
	
	public int searchCountByEid(String eid,String key);
	
	public List<ApplyForSend> getByEid(String eid,PageRequest pageRequest);
	
	public int allCountByEid(String eid);
	
	public List<ApplyForSend> find(String key,PageRequest pageRequest);
	
	public int searchCount(String key);
	
	public List<ApplyForSend> getAll(PageRequest pageRequest);
	
	public int allCount();
	
	public ApplyForSend getById(int id);
	
	public int createOne(ApplyForSend applyForSend);
	
	public int updateOne(ApplyForSend applyForSend);
	
	public int deleteById(int id);
	
	public int addboxitem(ProductboxApItem item);
	
	public int editboxitem(ProductboxApItem item);
	
	public int deleteboxitem(int id);
	
	public int addpageitem(ProductPageApItem item);
	
	public int editpackitem(ProductPackApItem item);
	
	public int deletepageitem(int id);
	
	public int addpackitem(ProductPackApItem item);
	
	public int editpageitem(ProductPageApItem item);
	
	public int deletepackitem(int id);
	
	public int addsampleitem(SampleApItem item);
	
	public int editsampleitem(SampleApItem item);
	
	public int deletesampleitem(int id);
	
	public List<List> staticSales(String eid,Date statr,Date end);
	
	public List<ApplyForSend> findCheckpending(String key,PageRequest pageRequest);
	
	public int searchCountCheckpending(String key);
	
	public List<ApplyForSend> getAllCheckpending(PageRequest pageRequest);
	
	public int allCountCheckpending();
	
	public List<ApplyForSend> findDelivered(String key,PageRequest pageRequest);
	
	public int searchCountDelivered(String key);
	
	public List<ApplyForSend> getAllDelivered(PageRequest pageRequest);
	
	public int allCountDelivered();
	
}
