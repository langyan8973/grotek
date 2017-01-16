package com.grotek.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.EmployeeFeeMapper;
import com.grotek.dao.PackTypeMapper;
import com.grotek.dao.ProductPackMapper;
import com.grotek.dao.ProductPack_InMapper;
import com.grotek.dao.ProductPack_OutMapper;
import com.grotek.dao.ProductPack_StoreMapper;
import com.grotek.dao.ReferencePackWeightMapper;
import com.grotek.dto.DealersNote;
import com.grotek.dto.PackNote;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.PackType;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPack_In;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductRaw_Store;
import com.grotek.pojo.ReferencePackWeight;
import com.grotek.service.ProductPackService;
@Service("productPackService")
@org.springframework.transaction.annotation.Transactional
public class ProductPackServiceImp implements ProductPackService {

	@Autowired
	private ProductPackMapper productPackDao;
	
	@Autowired
	private PackTypeMapper packTypeDao;
	
	@Autowired
	private ReferencePackWeightMapper referenceDao;
	
	@Autowired
	private ProductPack_StoreMapper storeDao;
	
	@Autowired
	private ProductPack_InMapper inDao;
	
	@Autowired
	private ProductPack_OutMapper outDao;
	
	@Autowired
	private EmployeeFeeMapper feeDao;
	
	@Override
	public List<ProductPack> findProductPacks(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return productPackDao.findProductPacks(text, pageable);
	}

	@Override
	public List<ProductPack> getProductPacks(PageRequest pageable) {
		// TODO Auto-generated method stub
		return productPackDao.getProductPacks(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return productPackDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return productPackDao.allCount();
	}

	@Override
	public int addProductPack(ProductPack productPack) {
		// TODO Auto-generated method stub
		productPack.setStatus(0);
		return productPackDao.insert(productPack);
	}

	@Override
	public int check(String name, String code) {
		// TODO Auto-generated method stub
		return productPackDao.checkNameAndCode(name, code);
	}

	@Override
	public ProductPack getById(int id) {
		// TODO Auto-generated method stub
		return productPackDao.selectByPrimaryKey(id);
	}

	@Override
	public int editProductPack(ProductPack productPack) {
		// TODO Auto-generated method stub
		return productPackDao.updateByPrimaryKey(productPack);
	}

	@Override
	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return productPackDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<PackType> getTypes() {
		// TODO Auto-generated method stub
		return packTypeDao.getAllPackTypes();
	}

	@Override
	public int checkTypeName(String name) {
		// TODO Auto-generated method stub
		return packTypeDao.checkName(name);
	}

	@Override
	public int addType(PackType type) {
		// TODO Auto-generated method stub
		type.setStatus(0);
		return packTypeDao.insert(type);
	}

	@Override
	public int editType(PackType type) {
		// TODO Auto-generated method stub
		return packTypeDao.updateByPrimaryKey(type);
	}

	@Override
	public int deleteType(int id) {
		// TODO Auto-generated method stub
		return packTypeDao.deleteByPrimaryKey(id);
	}

	@Override
	public PackType getTypeById(int id) {
		// TODO Auto-generated method stub
		return packTypeDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ReferencePackWeight> findReferencePackWeights(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return referenceDao.findReferencePackWeights(text, pageable);
	}

	@Override
	public List<ReferencePackWeight> getReferencePackWeights(PageRequest pageable) {
		// TODO Auto-generated method stub
		return referenceDao.getReferencePackWeights(pageable);
	}

	@Override
	public int searchReferencePackWeightCount(String text) {
		// TODO Auto-generated method stub
		return referenceDao.searchCount(text);
	}

	@Override
	public int referencePackWeightallcount() {
		// TODO Auto-generated method stub
		return referenceDao.allCount();
	}

	@Override
	public ReferencePackWeight getReferencePackWeightByPid(int pid) {
		// TODO Auto-generated method stub
		return referenceDao.getByPid(pid);
	}

	@Override
	public int deleteReferencePackWeight(int id) {
		// TODO Auto-generated method stub
		return referenceDao.deleteByPrimaryKey(id);
	}

	@Override
	public int createReferencePackWeight(ReferencePackWeight referencePackWeight) {
		// TODO Auto-generated method stub
		referencePackWeight.setStatus(0);
		return referenceDao.insert(referencePackWeight);
	}

	@Override
	public int editReferencePackWeight(ReferencePackWeight referencePackWeight) {
		// TODO Auto-generated method stub
		return referenceDao.updateByPrimaryKey(referencePackWeight);
	}

	@Override
	public List<ProductPack_Store> getStores(PageRequest pageable) {
		// TODO Auto-generated method stub
		return storeDao.getProductPackStores(pageable);
	}

	@Override
	public int storesAllCount() {
		// TODO Auto-generated method stub
		return storeDao.allCount();
	}

	@Override
	public ProductPack_Store getStoreByPid(int pid) {
		// TODO Auto-generated method stub
		return storeDao.getByPid(pid);
	}

	@Override
	public int changeStore(ProductPack_Store store) {
		// TODO Auto-generated method stub
		ProductPack_Store store2 = storeDao.getByPid(store.getPack().getId());
		if(store2==null){
			store.setStatus(0);
			return storeDao.insert(store);
		}
		else{
			store2.setCount(store.getCount());
			return storeDao.updateByPrimaryKey(store2);
		}
	}

	@Override
	public List<ProductPack_In> findIns(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return inDao.findProductPackIns(text, pageable);
	}

	@Override
	public List<ProductPack_In> getIns(PageRequest pageable) {
		// TODO Auto-generated method stub
		return inDao.getProductPackIns(pageable);
	}

	@Override
	public int searchInCount(String text) {
		// TODO Auto-generated method stub
		return inDao.searchCount(text);
	}

	@Override
	public int inallcount() {
		// TODO Auto-generated method stub
		return inDao.allCount();
	}

	@Override
	public List<ProductPack_In> getInByPid(int pid) {
		// TODO Auto-generated method stub
		return inDao.getByPid(pid);
	}

	@Override
	public int createIn(ProductPack_In in) {
		// TODO Auto-generated method stub
		in.setStatus(0);
		return inDao.insert(in);
	}

	@Override
	public List<ProductPack_Out> findOuts(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return outDao.findProductPackOuts(text, pageable);
	}

	@Override
	public List<ProductPack_Out> getOuts(PageRequest pageable) {
		// TODO Auto-generated method stub
		return outDao.getProductPackOuts(pageable);
	}

	@Override
	public int searchOutCount(String text) {
		// TODO Auto-generated method stub
		return outDao.searchCount(text);
	}

	@Override
	public int outallcount() {
		// TODO Auto-generated method stub
		return outDao.allCount();
	}

	@Override
	public List<ProductPack_Out> getOutByPid(int pid) {
		// TODO Auto-generated method stub
		return outDao.getByPid(pid);
	}

	@Override
	public int createOut(ProductPack_Out out) {
		// TODO Auto-generated method stub
		out.setStatus(0);
		return outDao.insert(out);
	}

	@Override
	public int addEmployeeFee(ProductPack_Out out, Employee employee) {
		// TODO Auto-generated method stub
		double total = 0.0;
		EmployeeFee employeeFee = new EmployeeFee();
		employeeFee.setDate(out.getDate());
		employeeFee.setDealer(out.getDealer());
		employeeFee.setEmployee(employee);
		employeeFee.setBccount(out.getCount());
		ProductPack pack = productPackDao.selectByPrimaryKey(out.getPack().getId());
		employeeFee.setBcamount(out.getCount()*pack.getPrice());
		total+=out.getCount()*pack.getPrice();
		employeeFee.setRemarks("包材名称："+pack.getName());
		employeeFee.setTotal(total);
		employeeFee.setStatus(0);
		feeDao.insert(employeeFee);
		return feeDao.insert(employeeFee);
	}

	@Override
	public List<PackNote> getPackNotes() {
		// TODO Auto-generated method stub
		List<PackNote> packNotes = new ArrayList<PackNote>();
		List<ProductPack> packs = productPackDao.getProductPacks(new PageRequest(0, 10000));
		PackNote sel = null;
		for (ProductPack pack : packs) {
			boolean b = false;
			for (Iterator iterator = packNotes.iterator(); iterator.hasNext();) {
				PackNote packNote = (PackNote) iterator.next();
				if(packNote.getTypeid()==pack.getType().getId()){
					b=true;
					sel = packNote;
					break;
				}
			}
			if(!b){
				PackNote sdn = new PackNote(pack.getType().getId(), pack.getType().getName());
				sdn.getPacks().add(pack);
				packNotes.add(sdn);
			}
			else{
				sel.getPacks().add(pack);
			}
		}
		return packNotes;
	}

	@Override
	public List<ProductPack_Store> findProductKcun(String text, PageRequest pageable) {
			
			return storeDao.findProductKcun(text, pageable);
		}

	@Override
	public int searchkuncCount(String text) {
		return storeDao.searchCount(text);
	}
	}


