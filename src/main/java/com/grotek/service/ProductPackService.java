package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.dto.PackNote;
import com.grotek.pojo.Employee;
import com.grotek.pojo.PackType;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPack_In;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductRaw_In;
import com.grotek.pojo.ProductRaw_Out;
import com.grotek.pojo.ProductRaw_Store;
import com.grotek.pojo.ReferencePackWeight;

public interface ProductPackService {

	public List<ProductPack> findProductPacks(String text,PageRequest pageable);
	
	public List<ProductPack> getProductPacks(PageRequest pageable);
	
	public int searchCount(String text);
	
	public int allCount();

	public int addProductPack(ProductPack productPack);
	
	public int check(String name,String code);
	
	public ProductPack getById(int id);
	
	public int editProductPack(ProductPack productPack);
	
	public int deleteOne(int id);
	
	
	public List<PackType> getTypes();
	
	public int checkTypeName(String name);
	
	public int addType(PackType type);
	
	public int editType(PackType type);
	
	public int deleteType(int id);
	
	public PackType getTypeById(int id);
	
	
	public List<ReferencePackWeight> findReferencePackWeights(String text,PageRequest pageable);
	
	public List<ReferencePackWeight> getReferencePackWeights(PageRequest pageable);
	
	public int searchReferencePackWeightCount(String text);
	
	public int referencePackWeightallcount();
	
	public ReferencePackWeight getReferencePackWeightByPid(int pid);
	
	public int deleteReferencePackWeight(int id);
	
	public int createReferencePackWeight(ReferencePackWeight referencePackWeight);
	
	public int editReferencePackWeight(ReferencePackWeight referencePackWeight);
	
	
	//LWX
	public List<ProductPack_Store> findProductKcun(String text,PageRequest pageable);
	
	public List<ProductPack_Store> getStores(PageRequest pageable);
	
	public int storesAllCount();
	
	//LWX
	public int searchkuncCount(String text);
	
	public ProductPack_Store getStoreByPid(int pid);
	
	public int changeStore(ProductPack_Store store);
	
	
	public List<ProductPack_In> findIns(String text,PageRequest pageable);
	
	public List<ProductPack_In> getIns(PageRequest pageable);
	
	public int searchInCount(String text);
	
	public int inallcount();
	
	public List<ProductPack_In> getInByPid(int pid);
	
	public int createIn(ProductPack_In in);
	
	
	public List<ProductPack_Out> findOuts(String text,PageRequest pageable);
	
	public List<ProductPack_Out> getOuts(PageRequest pageable);
	
	public int searchOutCount(String text);
	
	public int outallcount();
	
	public List<ProductPack_Out> getOutByPid(int pid);
	
	public int createOut(ProductPack_Out out);
	
	public int addEmployeeFee(ProductPack_Out out,Employee employee);
	
	public List<PackNote> getPackNotes();
	
}
