package com.grotek.service.imp;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grotek.dao.ApplyForSendMapper;
import com.grotek.dao.DealerPriceMapper;
import com.grotek.dao.DealerSaleMapper;
import com.grotek.dao.DeliveryOrderMapper;
import com.grotek.dao.EmployeeFeeMapper;
import com.grotek.dao.ProductBox_PriceMapper;
import com.grotek.dao.ProductBox_StoreMapper;
import com.grotek.dao.ProductBox_outMapper;
import com.grotek.dao.ProductPackApItemMapper;
import com.grotek.dao.ProductPackMapper;
import com.grotek.dao.ProductPack_OutMapper;
import com.grotek.dao.ProductPack_StoreMapper;
import com.grotek.dao.ProductPageApItemMapper;
import com.grotek.dao.ProductPageMapper;
import com.grotek.dao.ProductPage_OutMapper;
import com.grotek.dao.ProductPage_StoreMapper;
import com.grotek.dao.ProductboxApItemMapper;
import com.grotek.dao.SampleApItemMapper;
import com.grotek.pojo.ApplyForSend;
import com.grotek.pojo.DealerPrice;
import com.grotek.pojo.DealerSale;
import com.grotek.pojo.DeliveryOrder;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.ProductBox_Price;
import com.grotek.pojo.ProductBox_Store;
import com.grotek.pojo.ProductBox_out;
import com.grotek.pojo.ProductPack;
import com.grotek.pojo.ProductPackApItem;
import com.grotek.pojo.ProductPack_Out;
import com.grotek.pojo.ProductPack_Store;
import com.grotek.pojo.ProductPage;
import com.grotek.pojo.ProductPageApItem;
import com.grotek.pojo.ProductPage_Out;
import com.grotek.pojo.ProductPage_Store;
import com.grotek.pojo.ProductboxApItem;
import com.grotek.pojo.SampleApItem;
import com.grotek.service.DeliveryOrderService;
import com.grotek.util.PublicHelper;

@Service("deliveryorderService")
@org.springframework.transaction.annotation.Transactional
public class DeliveryOrderServiceImp implements DeliveryOrderService {

	@Autowired
	private DeliveryOrderMapper dao;
	@Autowired
	private ApplyForSendMapper applyDao;
	@Autowired
	private ProductboxApItemMapper boxitemDao;
	@Autowired
	private ProductBox_StoreMapper storeDao;
	@Autowired
	private ProductBox_outMapper outDao;
	@Autowired
	private DealerPriceMapper dealerPriceDao;
	@Autowired
	private ProductBox_PriceMapper priceDao;
	@Autowired
	private DealerSaleMapper dealerSaleDao;
	@Autowired
	private SampleApItemMapper sampleitemDao;
	@Autowired
	private EmployeeFeeMapper feeDao;
	@Autowired
	private ProductPackApItemMapper packItemDao;
	@Autowired
	private ProductPageApItemMapper pageItemDao;
	@Autowired
	private ProductPack_StoreMapper packStoreDao;
	@Autowired
	private ProductPack_OutMapper packOutDao;
	@Autowired
	private ProductPackMapper packDao;
	@Autowired
	private ProductPage_StoreMapper pageStoreDao;
	@Autowired
	private ProductPage_OutMapper pageOutDao;
	@Autowired
	private ProductPageMapper pageDao;
	
	@Override
	public DeliveryOrder getById(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public DeliveryOrder getByAid(int aid) {
		// TODO Auto-generated method stub
		return dao.getByAid(aid);
	}

	@Override
	public int addDeliveryOrder(DeliveryOrder deliveryOrder) {
		// TODO Auto-generated method stub
		deliveryOrder.setStatus(0);
		return dao.insert(deliveryOrder);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int editDeliveryOrder(DeliveryOrder deliveryOrder) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(deliveryOrder);
	}
	
	@Override
	public int saveDeliveryOrder(DeliveryOrder deliveryOrder) {
		// TODO Auto-generated method stub
		if (deliveryOrder != null) {
			ApplyForSend applyForSend = applyDao.selectByPrimaryKey(deliveryOrder.getAid());
			DeliveryOrder dOrder = getByAid(deliveryOrder.getAid());
			if (dOrder != null) {
				dOrder.setAid(deliveryOrder.getAid());
				dOrder.setBoxitems(deliveryOrder.getBoxitems());
				dOrder.setDate(deliveryOrder.getDate());
				dOrder.setDealer(deliveryOrder.getDealer());
				dOrder.setEmployee(deliveryOrder.getEmployee());
				dOrder.setGdnum(deliveryOrder.getGdnum());
				dOrder.setHtnum(deliveryOrder.getHtnum());
				dOrder.setMobile(deliveryOrder.getMobile());
				dOrder.setPackitems(deliveryOrder.getPackitems());
				dOrder.setPageitems(deliveryOrder.getPageitems());
				dOrder.setPayAddress(deliveryOrder.getPayAddress());
				dOrder.setPayContact(deliveryOrder.getPayContact());
				dOrder.setPayName(deliveryOrder.getPayName());
				dOrder.setPayPhone(deliveryOrder.getPayPhone());
				dOrder.setPhone(deliveryOrder.getPhone());
				dOrder.setReceiveAddress(deliveryOrder.getReceiveAddress());
				dOrder.setReceiveContact(deliveryOrder.getReceiveContact());
				dOrder.setReceiveName(deliveryOrder.getReceiveName());
				dOrder.setReceivePhone(deliveryOrder.getReceivePhone());
				dOrder.setSampleitems(deliveryOrder.getSampleitems());
				dOrder.setStatus(deliveryOrder.getStatus());
				int k = editDeliveryOrder(dOrder);
				if (k < 0) {
					return 0;
				}
			} else {
				int row = addDeliveryOrder(deliveryOrder);
				if (row < 0) {
					return 0;
				}
			}
			for (Iterator iterator = applyForSend.getBoxitems().iterator(); iterator.hasNext();) {
				ProductboxApItem productboxApItem = (ProductboxApItem) iterator.next();
				productboxApItem.setPid(deliveryOrder.getId());
				productboxApItem.setOpstatus(1);
				boxitemDao.updateByPrimaryKey(productboxApItem);
				//出库
				ProductBox_Store box_Store = storeDao.getByPid(productboxApItem.getBox().getId());
				ProductBox_out box_Out = new ProductBox_out();
				box_Out.setCount(productboxApItem.getCount());
				box_Out.setBox(productboxApItem.getBox());
				box_Out.setDealer(applyForSend.getDealer());
				box_Out.setDate(deliveryOrder.getDate());
				box_Out.setAid(applyForSend.getId());
				box_Out.setPsid(deliveryOrder.getId());
				createOut(box_Out, "", productboxApItem.getTotal());

				box_Store.setCount(box_Store.getCount() - productboxApItem.getCount());
				boxChangeStore(box_Store);
			}
			if (applyForSend.getSampleitems() != null && applyForSend.getSampleitems().size() > 0) {
				for (Iterator iterator = applyForSend.getSampleitems().iterator(); iterator.hasNext();) {
					SampleApItem sampleApItem = (SampleApItem) iterator.next();
					sampleApItem.setPid(deliveryOrder.getId());
					sampleApItem.setOpstatus(1);
					sampleitemDao.updateByPrimaryKey(sampleApItem);
					//出库
					ProductBox_Store box_Store = storeDao.getByPid(sampleApItem.getBox().getId());
					ProductBox_out box_Out = new ProductBox_out();
					box_Out.setCount(sampleApItem.getCount());
					box_Out.setBox(sampleApItem.getBox());
					box_Out.setDealer(applyForSend.getDealer());
					box_Out.setDate(deliveryOrder.getDate());
					box_Out.setAid(applyForSend.getId());
					box_Out.setPsid(deliveryOrder.getId());
					box_Out.setStatus(0);
					outDao.insert(box_Out);

					box_Store.setCount(box_Store.getCount() - sampleApItem.getCount());
					boxChangeStore(box_Store);
					//写入市场开拓费用
					addBoxEmployeeFee(box_Out, applyForSend.getEmployee());
				}
			}

			if (applyForSend.getPackitems() != null && applyForSend.getPackitems().size() > 0) {
				for (Iterator iterator = applyForSend.getPackitems().iterator(); iterator.hasNext();) {
					ProductPackApItem packApItem = (ProductPackApItem) iterator.next();
					packApItem.setPid(deliveryOrder.getId());
					packApItem.setOpstatus(1);
					packItemDao.updateByPrimaryKey(packApItem);
					//出库
					ProductPack_Store pack_Store = packStoreDao.getByPid(packApItem.getPack().getId());
					ProductPack_Out pack_Out = new ProductPack_Out();
					pack_Out.setCount(packApItem.getCount());
					pack_Out.setPack(packApItem.getPack());
					pack_Out.setDealer(applyForSend.getDealer());
					pack_Out.setDate(deliveryOrder.getDate());
					pack_Out.setAid(applyForSend.getId());
					pack_Out.setPsid(deliveryOrder.getId());
					pack_Out.setStatus(0);
					packOutDao.insert(pack_Out);
					pack_Store.setCount(pack_Store.getCount() - packApItem.getCount());
					packChangeStore(pack_Store);
					//写入市场开拓费用
					addPackEmployeeFee(pack_Out, applyForSend.getEmployee());
				}
			}
			if (applyForSend.getPageitems() != null && applyForSend.getPageitems().size() > 0) {
				for (Iterator iterator = applyForSend.getPageitems().iterator(); iterator.hasNext();) {
					ProductPageApItem pageApItem = (ProductPageApItem) iterator.next();
					pageApItem.setPid(deliveryOrder.getId());
					pageApItem.setOpstatus(1);
					pageItemDao.updateByPrimaryKey(pageApItem);
					//出库
					ProductPage_Store page_Store = pageStoreDao.getByPid(pageApItem.getPage().getId());
					ProductPage_Out page_Out = new ProductPage_Out();
					page_Out.setCount(pageApItem.getCount());
					page_Out.setPage(pageApItem.getPage());
					page_Out.setDealer(applyForSend.getDealer());
					page_Out.setDate(deliveryOrder.getDate());
					page_Out.setAid(applyForSend.getId());
					page_Out.setPsid(deliveryOrder.getId());
					page_Out.setStatus(0);
					pageOutDao.insert(page_Out);
					page_Store.setCount(page_Store.getCount() - pageApItem.getCount());
					pageChangeStore(page_Store);
					//写入市场开拓费用
					addPageEmployeeFee(page_Out, applyForSend.getEmployee());
				}
			}
			applyForSend.setOpstatus(1);
			applyDao.updateByPrimaryKey(applyForSend);
			return applyForSend.getId();
		} else {
			return 0;
		} 
	}

	@Override
	public int createOut(ProductBox_out out, String paystatus, double amount) {
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
	public int boxChangeStore(ProductBox_Store store) {
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
	public int addBoxEmployeeFee(ProductBox_out out, Employee employee) {
		// TODO Auto-generated method stub
		double total = 0.0;
		EmployeeFee employeeFee = new EmployeeFee();
		employeeFee.setDate(out.getDate());
		employeeFee.setDealer(out.getDealer());
		employeeFee.setEmployee(employee);
		employeeFee.setBccount(out.getCount());
		ProductBox_Price price = priceDao.getByPid(out.getBox().getId());
		if(price!=null){
			employeeFee.setYpamount(PublicHelper.correctTo(price.getPieceprice()*out.getCount()));
			total+=PublicHelper.correctTo(price.getPieceprice()*out.getCount());
		}
		else{
			employeeFee.setYpamount(0.0);
			total+=0;
		}	
		employeeFee.setRemarks("样品名称："+out.getBox().getName());
		employeeFee.setTotal(total);
		employeeFee.setStatus(0);
		return feeDao.insert(employeeFee);
	}

	@Override
	public int packChangeStore(ProductPack_Store store) {
		// TODO Auto-generated method stub
		ProductPack_Store store2 = packStoreDao.getByPid(store.getPack().getId());
		if(store2==null){
			store.setStatus(0);
			return packStoreDao.insert(store);
		}
		else{
			store2.setCount(store.getCount());
			return packStoreDao.updateByPrimaryKey(store2);
		}
	}

	@Override
	public int addPackEmployeeFee(ProductPack_Out out, Employee employee) {
		// TODO Auto-generated method stub
		double total = 0.0;
		EmployeeFee employeeFee = new EmployeeFee();
		employeeFee.setDate(out.getDate());
		employeeFee.setDealer(out.getDealer());
		employeeFee.setEmployee(employee);
		employeeFee.setBccount(out.getCount());
		ProductPack pack = packDao.selectByPrimaryKey(out.getPack().getId());
		employeeFee.setBcamount(out.getCount()*pack.getPrice());
		total+=out.getCount()*pack.getPrice();
		employeeFee.setRemarks("包材名称："+pack.getName());
		employeeFee.setTotal(total);
		employeeFee.setStatus(0);
		return feeDao.insert(employeeFee);
	}

	@Override
	public int pageChangeStore(ProductPage_Store store) {
		// TODO Auto-generated method stub
		ProductPage_Store store2 = pageStoreDao.getByPid(store.getPage().getId());
		if(store2==null){
			store.setStatus(0);
			return pageStoreDao.insert(store);
		}
		else{
			store2.setCount(store.getCount());
			return pageStoreDao.updateByPrimaryKey(store2);
		}
	}

	@Override
	public int addPageEmployeeFee(ProductPage_Out out, Employee employee) {
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
		return feeDao.insert(employeeFee);
	}

	
}
