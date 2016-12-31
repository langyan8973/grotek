package com.grotek.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.DealerPriceMapper;
import com.grotek.dao.DealerSaleMapper;
import com.grotek.dao.EmployeeFeeMapper;
import com.grotek.dao.InSchemePacksMapper;
import com.grotek.dao.InSchemePagesMapper;
import com.grotek.dao.InSchemeRawsMapper;
import com.grotek.dao.ProductBoxInSchemeMapper;
import com.grotek.dao.ProductBoxMapper;
import com.grotek.dao.ProductBox_InMapper;
import com.grotek.dao.ProductBox_PriceMapper;
import com.grotek.dao.ProductBox_StoreMapper;
import com.grotek.dao.ProductBox_outMapper;
import com.grotek.dao.ProductLabor_CostMapper;
import com.grotek.dao.ProductPack_InMapper;
import com.grotek.dao.ProductPack_OutMapper;
import com.grotek.dao.ProductPack_StoreMapper;
import com.grotek.dao.ProductPage_OutMapper;
import com.grotek.dao.ProductPage_StoreMapper;
import com.grotek.dao.ProductRaw_InMapper;
import com.grotek.dao.ProductRaw_OutMapper;
import com.grotek.dao.ProductRaw_StoreMapper;
import com.grotek.dto.BoxNote;
import com.grotek.dto.PackNote;
import com.grotek.dto.ServiceResult;
import com.grotek.pojo.DealerPrice;
import com.grotek.pojo.DealerSale;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.InSchemePacks;
import com.grotek.pojo.InSchemePages;
import com.grotek.pojo.InSchemeRaws;
import com.grotek.pojo.ProductBox;
import com.grotek.pojo.ProductBoxInScheme;
import com.grotek.pojo.ProductBox_In;
import com.grotek.pojo.ProductBox_Price;
import com.grotek.pojo.ProductBox_Store;
import com.grotek.pojo.ProductBox_out;
import com.grotek.pojo.ProductLabor_Cost;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductPage_Store;
import com.grotek.pojo.ProductRaw_Out;
import com.grotek.pojo.ProductRaw_Store;
import com.grotek.service.ProductBoxService;
import com.grotek.util.PublicHelper;
@Service("productBoxService")
public class ProductBoxServiceImp implements ProductBoxService {

	@Autowired
	private ProductBoxMapper boxDao;
	
	@Autowired
	private ProductBox_PriceMapper priceDao;
	
	@Autowired
	private ProductBoxInSchemeMapper schemeDao;
	
	@Autowired
	private InSchemeRawsMapper schemeRawsDao;
	
	@Autowired
	private InSchemePacksMapper schemePachsDao;
	
	@Autowired
	private InSchemePagesMapper schemePagesDao;
	
	@Autowired
	private ProductBox_StoreMapper storeDao;
	
	@Autowired
	private ProductBox_InMapper inDao;
	
	@Autowired
	private ProductRaw_StoreMapper rawStoreDao;
	
	@Autowired
	private ProductRaw_OutMapper rawOutDao;
	
	@Autowired
	private ProductPack_StoreMapper packStoreDao;
	
	@Autowired
	private ProductPack_OutMapper packOutDao;
	
	@Autowired
	private ProductPage_StoreMapper pageStoreDao;
	
	@Autowired
	private ProductPage_OutMapper pageOutDao;
	
	@Autowired
	private ProductBox_outMapper outDao;
	
	@Autowired
	private DealerSaleMapper dealerSaleDao;
	
	@Autowired
	private ProductLabor_CostMapper laborDao;
	
	@Autowired
	private DealerPriceMapper dealerPriceDao;
	
	@Autowired
	private EmployeeFeeMapper feeDao;
	
	
	@Override
	public List<ProductBox> findProductBoxs(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return boxDao.findProductBoxs(text, pageable);
	}

	@Override
	public List<ProductBox> getProductBoxs(PageRequest pageable) {
		// TODO Auto-generated method stub
		return boxDao.getProductBoxs(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return boxDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return boxDao.allCount();
	}

	@Override
	public int addProductBox(ProductBox productBox) {
		// TODO Auto-generated method stub
		productBox.setStatus(0);
		return boxDao.insert(productBox);
	}

	@Override
	public int check(String code) {
		// TODO Auto-generated method stub
		return boxDao.checkCode(code);
	}

	@Override
	public ProductBox getById(int id) {
		// TODO Auto-generated method stub
		return boxDao.selectByPrimaryKey(id);
	}

	@Override
	public int editProductBox(ProductBox productBox) {
		// TODO Auto-generated method stub
		return boxDao.updateByPrimaryKey(productBox);
	}

	@Override
	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return boxDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<ProductBox_Price> findprices(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return priceDao.findProductBoxPrices(text, pageable);
	}

	@Override
	public List<ProductBox_Price> getPrices(PageRequest pageable) {
		// TODO Auto-generated method stub
		return priceDao.getProductBoxPrices(pageable);
	}

	@Override
	public int searchPriceCount(String text) {
		// TODO Auto-generated method stub
		return priceDao.searchCount(text);
	}

	@Override
	public int priceallcount() {
		// TODO Auto-generated method stub
		return priceDao.allCount();
	}

	@Override
	public ProductBox_Price getPriceByPid(int pid) {
		// TODO Auto-generated method stub
		return priceDao.getByPid(pid);
	}

	@Override
	public int deletePrice(int id) {
		// TODO Auto-generated method stub
		return priceDao.deleteByPrimaryKey(id);
	}

	@Override
	public int createPrice(ProductBox_Price price) {
		// TODO Auto-generated method stub
		price.setStatus(0);
		return priceDao.insert(price);
	}

	@Override
	public ProductBoxInScheme getScheme(int pid) {
		// TODO Auto-generated method stub
		return schemeDao.getByPid(pid);
	}

	@Override
	public int deleteScheme(int id) {
		// TODO Auto-generated method stub
		return schemeDao.deleteByPrimaryKey(id);
	}

	@Override
	public int createScheme(ProductBoxInScheme inScheme) {
		// TODO Auto-generated method stub
		inScheme.setStatus(0);
		int row = schemeDao.addone(inScheme);
		if(row<0){
			return row;
		}
		if(inScheme.getSchemeRaws()!=null && inScheme.getSchemeRaws().size()>0){
			for (Iterator iterator = inScheme.getSchemeRaws().iterator(); iterator.hasNext();) {
				InSchemeRaws raw = (InSchemeRaws) iterator.next();
				if(raw ==null || raw.getRaw()==null || raw.getCount()<=0){
					continue;
				}
				raw.setSid(inScheme.getId());
				raw.setStatus(0);
				schemeRawsDao.insert(raw);
			}
		}
		if(inScheme.getSchemePacks()!=null && inScheme.getSchemePacks().size()>0){
			for (Iterator iterator = inScheme.getSchemePacks().iterator(); iterator.hasNext();) {
				InSchemePacks pack = (InSchemePacks) iterator.next();
				if(pack==null || pack.getPack()==null || pack.getCount()<=0){
					continue;
				}
				pack.setSid(inScheme.getId());
				pack.setStatus(0);
				schemePachsDao.insert(pack);
			}
		}
		if(inScheme.getSchemePages()!=null && inScheme.getSchemePages().size()>0){
			for (Iterator iterator = inScheme.getSchemePages().iterator(); iterator.hasNext();) {
				InSchemePages page = (InSchemePages) iterator.next();
				if(page==null || page.getPage()==null || page.getCount()<=0){
					continue;
				}
				page.setSid(inScheme.getId());
				page.setStatus(0);
				schemePagesDao.insert(page);
			}
		}		
		return row;
	}

	@Override
	public List<ProductBox_Store> getStores(PageRequest pageable) {
		// TODO Auto-generated method stub
		return storeDao.getProductBoxStores(pageable);
	}

	@Override
	public int storesAllCount() {
		// TODO Auto-generated method stub
		return storeDao.allCount();
	}

	@Override
	public ProductBox_Store getStoreByPid(int pid) {
		// TODO Auto-generated method stub
		return storeDao.getByPid(pid);
	}

	@Override
	public int changeStore(ProductBox_Store store) {
		// TODO Auto-generated method stub
		ProductBox_Store store2 = storeDao.getByPid(store.getBox().getId());
		if(store2==null){
			store.setStatus(0);
			storeDao.insert(store);
		}
		else{
			store2.setCount(store.getCount());
			storeDao.updateByPrimaryKey(store2);
		}
		return 0;
	}

	@Override
	public List<ProductBox_In> findIns(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return inDao.findProductBoxIns(text, pageable);
	}

	@Override
	public List<ProductBox_In> getIns(PageRequest pageable) {
		// TODO Auto-generated method stub
		return inDao.getProductBoxIns(pageable);
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
	public List<ProductBox_In> getInByPid(int pid) {
		// TODO Auto-generated method stub
		return inDao.getByPid(pid);
	}

	@Override
	public ServiceResult createIn(ProductBox_In in) {
		// TODO Auto-generated method stub
		ServiceResult result = new ServiceResult();
		in.setStatus(0);
		ProductBoxInScheme inScheme = schemeDao.getByPid(in.getBox().getId());
		if(inScheme.getSchemeRaws()!=null && inScheme.getSchemeRaws().size()>0){
			for (Iterator iterator = inScheme.getSchemeRaws().iterator(); iterator.hasNext();) {
				InSchemeRaws schemeRaws = (InSchemeRaws) iterator.next();
				ProductRaw_Store raw_Store = rawStoreDao.getByPid(schemeRaws.getRaw().getId());
				if(raw_Store==null || raw_Store.getCount()<in.getCount()*schemeRaws.getCount())
				{
					result.setStatus(-1);
					result.setMessage("所需的"+schemeRaws.getRaw().getName()+"库存不足，入库失败");
					return result;
				}
			}
		}
		if(inScheme.getSchemePacks()!=null && inScheme.getSchemePacks().size()>0){
			for (Iterator iterator = inScheme.getSchemePacks().iterator(); iterator.hasNext();) {
				InSchemePacks schemePacks = (InSchemePacks) iterator.next();
				ProductPack_Store pack_Store = packStoreDao.getByPid(schemePacks.getPack().getId());
				if(pack_Store==null || pack_Store.getCount()<in.getCount()*schemePacks.getCount())
				{
					result.setStatus(-1);
					result.setMessage("所需的"+schemePacks.getPack().getName()+"库存不足，入库失败");
					return result;
				}
			}
		}
		if(inScheme.getSchemePages()!=null && inScheme.getSchemePages().size()>0){
			for (Iterator iterator = inScheme.getSchemePages().iterator(); iterator.hasNext();) {
				InSchemePages schemePages = (InSchemePages) iterator.next();
				ProductPage_Store page_Store = pageStoreDao.getByPid(schemePages.getPage().getId());
				if(page_Store==null || page_Store.getCount()<in.getCount()*schemePages.getCount())
				{
					result.setStatus(-1);
					result.setMessage("所需的"+schemePages.getPage().getName()+"库存不足，入库失败");
					return result;
				}
			}
		}
		int num = inDao.insert(in);
		if(num<0){
			result.setStatus(-1);
			result.setMessage("入库失败");
			return result;
		}
		ProductBox_Store store = storeDao.getByPid(in.getBox().getId());
		if(store==null){
			store = new ProductBox_Store();
			store.setBox(in.getBox());
			store.setCount(in.getCount());
			store.setStatus(0);
		}
		else{
			store.setCount(store.getCount()+in.getCount());
			store.setStatus(0);
		}
		changeStore(store);
		if(inScheme.getSchemeRaws()!=null && inScheme.getSchemeRaws().size()>0){
			for (Iterator iterator = inScheme.getSchemeRaws().iterator(); iterator.hasNext();) {
				InSchemeRaws schemeRaws = (InSchemeRaws) iterator.next();
				ProductRaw_Store raw_Store = rawStoreDao.getByPid(schemeRaws.getRaw().getId());
				ProductRaw_Out raw_Out = new ProductRaw_Out();
				raw_Out.setBoxinid(in.getId());
				raw_Out.setCount(in.getCount()*schemeRaws.getCount());
				raw_Out.setDate(in.getDate());
				raw_Out.setProductRaw(schemeRaws.getRaw());
				raw_Out.setStatus(0);
				rawOutDao.insert(raw_Out);
				
				raw_Store.setCount(raw_Store.getCount()-raw_Out.getCount());
				rawStoreDao.updateByPrimaryKey(raw_Store);
			}
		}
		if(inScheme.getSchemePacks()!=null && inScheme.getSchemePacks().size()>0){
			for (Iterator iterator = inScheme.getSchemePacks().iterator(); iterator.hasNext();) {
				InSchemePacks schemePacks = (InSchemePacks) iterator.next();
				ProductPack_Store pack_Store = packStoreDao.getByPid(schemePacks.getPack().getId());
				ProductPack_Out pack_Out = new ProductPack_Out();
				pack_Out.setBoxinid(in.getId());
				pack_Out.setCount(in.getCount()*schemePacks.getCount());
				pack_Out.setDate(in.getDate());
				pack_Out.setPack(schemePacks.getPack());
				pack_Out.setStatus(0);
				packOutDao.insert(pack_Out);
				
				pack_Store.setCount(pack_Store.getCount()-pack_Out.getCount());
				packStoreDao.updateByPrimaryKey(pack_Store);
			}
		}
		if(inScheme.getSchemePages()!=null && inScheme.getSchemePages().size()>0){
			for (Iterator iterator = inScheme.getSchemePages().iterator(); iterator.hasNext();) {
				InSchemePages schemePages = (InSchemePages) iterator.next();
				ProductPage_Store page_Store = pageStoreDao.getByPid(schemePages.getPage().getId());
				ProductPage_Out page_Out = new ProductPage_Out();
				page_Out.setBoxinid(in.getId());
				page_Out.setCount(in.getCount()*schemePages.getCount());
				page_Out.setDate(in.getDate());
				page_Out.setPage(schemePages.getPage());
				page_Out.setStatus(0);
				pageOutDao.insert(page_Out);
				
				page_Store.setCount(page_Store.getCount()-page_Out.getCount());
				pageStoreDao.updateByPrimaryKey(page_Store);
			}
		}
		result.setStatus(0);
		return result;
	}

	@Override
	public ProductBox_In getInByid(int id) {
		// TODO Auto-generated method stub
		return inDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductRaw_Out> getRawOutsByBoxinId(int boxinid) {
		// TODO Auto-generated method stub
		return rawOutDao.getByBoxinId(boxinid);
	}

	@Override
	public List<ProductPack_Out> getPackOutsByBoxinId(int boxinid) {
		// TODO Auto-generated method stub
		return packOutDao.getByBoxinId(boxinid);
	}

	@Override
	public List<ProductPage_Out> getPageOutsByBoxinId(int boxinid) {
		// TODO Auto-generated method stub
		return pageOutDao.getByBoxinId(boxinid);
	}

	@Override
	public List<ProductBox_out> findOuts(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return outDao.findProductBox_outs(text, pageable);
	}

	@Override
	public List<ProductBox_out> getOuts(PageRequest pageable) {
		// TODO Auto-generated method stub
		return outDao.getProductBox_outs(pageable);
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
	public List<ProductBox_out> getOutByPid(int pid) {
		// TODO Auto-generated method stub
		return outDao.getByPid(pid);
	}

	@Override
	public int createOut(ProductBox_out out,String paystatus,double amount) {
		// TODO Auto-generated method stub
		out.setStatus(0);
		int i = outDao.insert(out);
		DealerSale dealerSale = new DealerSale();
		dealerSale.setBox(out.getBox());
		dealerSale.setDealer(out.getDealer());
		dealerSale.setCount(out.getCount());
		dealerSale.setOutid(out.getId());
		dealerSale.setPosttime(out.getDate());
		dealerSale.setPaystatus(paystatus);
		dealerSale.setAmountpaid(amount);
		DealerPrice dealerPrice = dealerPriceDao.getByPidAndDid(out.getDealer().getId(), out.getBox().getId());
		if(dealerPrice!=null){
			dealerSale.setPrice(dealerPrice.getPrice());
			dealerSale.setTotal(out.getCount()*dealerPrice.getPrice());
		}
		else{
			ProductBox_Price price = priceDao.getByPid(out.getBox().getId());
			dealerSale.setPrice(price.getDealerPieceprice());
			dealerSale.setTotal(out.getCount()*price.getDealerPieceprice());
		}
		
		dealerSale.setStatus(0);
		dealerSaleDao.insert(dealerSale);
		return i;
	}
	
	@Override
	public int createSampleOut(ProductBox_out out,String paystatus){
		out.setStatus(0);
		int i = outDao.insert(out);
//		DealerSale dealerSale = new DealerSale();
//		dealerSale.setBox(out.getBox());
//		dealerSale.setDealer(out.getDealer());
//		dealerSale.setCount(out.getCount());
//		dealerSale.setOutid(out.getId());
//		dealerSale.setPosttime(out.getDate());
//		dealerSale.setPaystatus(paystatus);
//		ProductBox_Price price1 = priceDao.getByPid(out.getBox().getId());
//		dealerSale.setAmountpaid(PublicHelper.correctTo(out.getCount()*price1.getDealerPieceprice()));
//		DealerPrice dealerPrice = dealerPriceDao.getByPidAndDid(out.getDealer().getId(), out.getBox().getId());
//		if(dealerPrice!=null){
//			dealerSale.setPrice(dealerPrice.getPrice());
//			dealerSale.setTotal(out.getCount()*dealerPrice.getPrice());
//		}
//		else{
//			ProductBox_Price price = priceDao.getByPid(out.getBox().getId());
//			dealerSale.setPrice(price.getDealerPieceprice());
//			dealerSale.setTotal(PublicHelper.correctTo(out.getCount()*price.getDealerPieceprice()));
//		}
//		
//		dealerSale.setStatus(0);
//		dealerSaleDao.insert(dealerSale);
		return i;
	}

	@Override
	public ProductBox_out getOutById(int id) {
		// TODO Auto-generated method stub
		return outDao.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductLabor_Cost> findLaborCosts(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return laborDao.findProductLabor_Costs(text, pageable);
	}

	@Override
	public List<ProductLabor_Cost> getLaborCosts(PageRequest pageable) {
		// TODO Auto-generated method stub
		return laborDao.getProductLabor_Costs(pageable);
	}

	@Override
	public int searchLaborCostCount(String text) {
		// TODO Auto-generated method stub
		return laborDao.searchCount(text);
	}

	@Override
	public int laborCostallcount() {
		// TODO Auto-generated method stub
		return laborDao.allCount();
	}

	@Override
	public ProductLabor_Cost getLaborCostByPid(int pid) {
		// TODO Auto-generated method stub
		return laborDao.getByPid(pid);
	}

	@Override
	public int deleteLaborCost(int id) {
		// TODO Auto-generated method stub
		return laborDao.deleteByPrimaryKey(id);
	}

	@Override
	public int createLaborCost(ProductLabor_Cost laborcost) {
		// TODO Auto-generated method stub
		laborcost.setStatus(0);
		return laborDao.insert(laborcost);
	}

	@Override
	public List<ProductBox_out> findOutsBydid(int did, String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return outDao.findProductBox_outsByDid(did, text, pageable);
	}

	@Override
	public List<ProductBox_out> getOutsBydid(int did, PageRequest pageable) {
		// TODO Auto-generated method stub
		return outDao.getProductBox_outsByDid(did, pageable);
	}

	@Override
	public int searchOutCountBydid(int did, String text) {
		// TODO Auto-generated method stub
		return outDao.searchCountByDid(did, text);
	}

	@Override
	public int outallcountBydid(int did) {
		// TODO Auto-generated method stub
		return outDao.allCountByDid(did);
	}

	@Override
	public List<BoxNote> getBoxNotes() {
		// TODO Auto-generated method stub
		List<BoxNote> boxNotes = new ArrayList<BoxNote>();
		List<ProductBox> boxs = boxDao.getBoxsOrderBy();
		BoxNote sel = null;
		for (ProductBox box : boxs) {
			boolean b = false;
			for (Iterator iterator = boxNotes.iterator(); iterator.hasNext();) {
				BoxNote boxNote = (BoxNote) iterator.next();
				if(boxNote.getTypeid()==box.getType().getId()){
					b=true;
					sel = boxNote;
					break;
				}
			}
			if(!b){
				BoxNote sdn = new BoxNote(box.getType().getId(), box.getType().getName());
				sdn.getBoxs().add(box);
				boxNotes.add(sdn);
			}
			else{
				sel.getBoxs().add(box);
			}
		}
		return boxNotes;
	}

	@Override
	public int addEmployeeFee(ProductBox_out out, Employee employee) {
		// TODO Auto-generated method stub
		double total = 0.0;
		EmployeeFee employeeFee = new EmployeeFee();
		employeeFee.setDate(out.getDate());
		employeeFee.setDealer(out.getDealer());
		employeeFee.setEmployee(employee);
		employeeFee.setBccount(out.getCount());
		ProductBox_Price price = priceDao.getByPid(out.getBox().getId());
//		if(price==null){
//			return 0;
//		}
		employeeFee.setYpamount(PublicHelper.correctTo(price.getPieceprice()*out.getCount()));
		total+=PublicHelper.correctTo(price.getDealerPieceprice()*out.getCount());
		employeeFee.setRemarks("样品名称："+out.getBox().getName());
		employeeFee.setTotal(total);
		employeeFee.setStatus(0);
		feeDao.insert(employeeFee);
		return feeDao.insert(employeeFee);
	}

}
