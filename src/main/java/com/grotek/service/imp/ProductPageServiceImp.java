package com.grotek.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.EmployeeFeeMapper;
import com.grotek.dao.PageTypeMapper;
import com.grotek.dao.ProductPageMapper;
import com.grotek.dao.ProductPage_InMapper;
import com.grotek.dao.ProductPage_OutMapper;
import com.grotek.dao.ProductPage_StoreMapper;
import com.grotek.dto.PackNote;
import com.grotek.dto.PageNote;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.PageType;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductPage;
import com.grotek.pojo.ProductPage_In;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductPage_Store;
import com.grotek.service.ProductPageService;
@Service("productPageService")
@org.springframework.transaction.annotation.Transactional
public class ProductPageServiceImp implements ProductPageService {
	
	@Autowired
	private ProductPageMapper pageDao;

	@Autowired
	private PageTypeMapper typeDao;
	
	@Autowired
	private ProductPage_StoreMapper storeDao;
	
	@Autowired
	private ProductPage_InMapper inDao;
	
	@Autowired
	private ProductPage_OutMapper outDao;
	
	@Autowired
	private EmployeeFeeMapper feeDao;
	
	@Override
	public List<ProductPage> findProductPages(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return pageDao.findProductPages(text, pageable);
	}

	@Override
	public List<ProductPage> getProductPages(PageRequest pageable) {
		// TODO Auto-generated method stub
		return pageDao.getProductPages(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return pageDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return pageDao.allCount();
	}

	@Override
	public int addProductPage(ProductPage productPage) {
		// TODO Auto-generated method stub
		productPage.setStatus(0);
		return pageDao.insert(productPage);
	}

	@Override
	public int check(String name, String code) {
		// TODO Auto-generated method stub
		return pageDao.checkNameAndCode(name, code);
	}

	@Override
	public ProductPage getById(int id) {
		// TODO Auto-generated method stub
		return pageDao.selectByPrimaryKey(id);
	}

	@Override
	public int editProductPage(ProductPage productPage) {
		// TODO Auto-generated method stub
		return pageDao.updateByPrimaryKey(productPage);
	}

	@Override
	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return pageDao.deleteByPrimaryKey(id);
	}
	
	
	@Override
	public List<PageType> getTypes() {
		// TODO Auto-generated method stub
		return typeDao.getAllPageTypes();
	}

	@Override
	public int checkTypeName(String name) {
		// TODO Auto-generated method stub
		return typeDao.checkName(name);
	}

	@Override
	public int addType(PageType type) {
		// TODO Auto-generated method stub
		type.setStatus(0);
		return typeDao.insert(type);
	}

	@Override
	public int editType(PageType type) {
		// TODO Auto-generated method stub
		return typeDao.updateByPrimaryKey(type);
	}

	@Override
	public int deleteType(int id) {
		// TODO Auto-generated method stub
		return typeDao.deleteByPrimaryKey(id);
	}

	@Override
	public PageType getTypeById(int id) {
		// TODO Auto-generated method stub
		return typeDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductPage_Store> getStores(PageRequest pageable) {
		// TODO Auto-generated method stub
		return storeDao.getProductPageStores(pageable);
	}

	@Override
	public int storesAllCount() {
		// TODO Auto-generated method stub
		return storeDao.allCount();
	}

	@Override
	public ProductPage_Store getStoreByPid(int pid) {
		// TODO Auto-generated method stub
		return storeDao.getByPid(pid);
	}

	@Override
	public int changeStore(ProductPage_Store store) {
		// TODO Auto-generated method stub
		ProductPage_Store store2 = storeDao.getByPid(store.getPage().getId());
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
	public List<ProductPage_In> findIns(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return inDao.findProductPageIns(text, pageable);
	}

	@Override
	public List<ProductPage_In> getIns(PageRequest pageable) {
		// TODO Auto-generated method stub
		return inDao.getProductPageIns(pageable);
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
	public List<ProductPage_In> getInByPid(int pid) {
		// TODO Auto-generated method stub
		return inDao.getByPid(pid);
	}

	@Override
	public int createIn(ProductPage_In in) {
		// TODO Auto-generated method stub
		in.setStatus(0);
		return inDao.insert(in);
	}

	@Override
	public List<ProductPage_Out> findOuts(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return outDao.findProductPageOuts(text, pageable);
	}

	@Override
	public List<ProductPage_Out> getOuts(PageRequest pageable) {
		// TODO Auto-generated method stub
		return outDao.getProductPageOuts(pageable);
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
	public List<ProductPage_Out> getOutByPid(int pid) {
		// TODO Auto-generated method stub
		return outDao.getByPid(pid);
	}

	@Override
	public int createOut(ProductPage_Out out) {
		// TODO Auto-generated method stub
		out.setStatus(0);
		return outDao.insert(out);
	}

	@Override
	public int addEmployeeFee(ProductPage_Out out, Employee employee) {
		// TODO Auto-generated method stub
		EmployeeFee employeeFee = new EmployeeFee();
		employeeFee.setDate(out.getDate());
		employeeFee.setDealer(out.getDealer());
		employeeFee.setEmployee(employee);
		double total = 0.0;
		ProductPage page = pageDao.selectByPrimaryKey(out.getPage().getId());
		employeeFee.setXcdycount(out.getCount());
		employeeFee.setXcdyamount(out.getCount()*page.getPrice());
		total+=out.getCount()*page.getPrice();
//		if(page.getType().getId()==1){
//			employeeFee.setXcdycount(out.getCount());
//			employeeFee.setXcdyamount(out.getCount()*page.getPrice());
//			total+=out.getCount()*page.getPrice();
//		}
//		else if(page.getType().getId()==2){
//			employeeFee.setHbcount(out.getCount());
//			employeeFee.setHbamount(out.getCount()*page.getPrice());
//			total+=out.getCount()*page.getPrice();
//		}
//		else{
//			employeeFee.setQtamount(out.getCount()*page.getPrice());
//			total+=out.getCount()*page.getPrice();
//			employeeFee.setRemarks(page.getName()+":"+out.getCount());
//		}
		employeeFee.setStatus(0);
		employeeFee.setTotal(total);
		feeDao.insert(employeeFee);
		return feeDao.insert(employeeFee);
	}

	@Override
	public List<PageNote> getPageNotes() {
		// TODO Auto-generated method stub
		List<PageNote> pageNotes = new ArrayList<PageNote>();
		List<ProductPage> pages = pageDao.getProductPages(new PageRequest(0, 10000));
		PageNote sel = null;
		for (ProductPage page : pages) {
			boolean b = false;
			for (Iterator iterator = pageNotes.iterator(); iterator.hasNext();) {
				PageNote pageNote = (PageNote) iterator.next();
				if(pageNote.getTypeid()==page.getType().getId()){
					b=true;
					sel = pageNote;
					break;
				}
			}
			if(!b){
				PageNote sdn = new PageNote(page.getType().getId(), page.getType().getName());
				sdn.getPages().add(page);
				pageNotes.add(sdn);
			}
			else{
				sel.getPages().add(page);
			}
		}
		return pageNotes;
	}

	

}
