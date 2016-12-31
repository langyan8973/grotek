package com.grotek.service.imp;

import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.taglibs.standard.lang.jstl.DivideOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.DealerGoalMapper;
import com.grotek.dao.DealerMapper;
import com.grotek.dao.DealerValueMapper;
import com.grotek.dao.DeliveryOrderMapper;
import com.grotek.dao.EmployeeFeeMapper;
import com.grotek.dto.DealersNote;
import com.grotek.dto.SuperiorsNote;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerGoal;
import com.grotek.pojo.DealerValue;
import com.grotek.pojo.DeliveryOrder;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeFee;
import com.grotek.pojo.ProductboxApItem;
import com.grotek.service.DealerService;
import com.grotek.util.DateTools;
import com.grotek.util.PublicHelper;

@Service("dealerService")
@org.springframework.transaction.annotation.Transactional
public class DealerServiceImp implements DealerService {
	
	@Autowired
	private DealerMapper dealerDao;
	
	@Autowired
	private DealerGoalMapper goalDao;
	
	@Autowired
	private DeliveryOrderMapper orderDao;
	
	@Autowired
	private EmployeeFeeMapper feeDao;
	
	@Autowired
	private DealerValueMapper valueDao;

	@Override
	public List<Dealer> findDealers(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return dealerDao.findDealer(text, pageable);
	}

	@Override
	public List<Dealer> getDealers(PageRequest pageable) {
		// TODO Auto-generated method stub
		return dealerDao.getDealers(pageable);
	}

	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return dealerDao.searchCount(text);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return dealerDao.allCount();
	}

	@Override
	public int addDealer(Dealer dealer) {
		// TODO Auto-generated method stub
		dealer.setStatus(0);
		return dealerDao.insert(dealer);
	}

	@Override
	public Dealer getById(int id) {
		// TODO Auto-generated method stub
		return dealerDao.selectByPrimaryKey(id);
	}

	@Override
	public int editDealer(Dealer dealer) {
		// TODO Auto-generated method stub
		return dealerDao.updateByPrimaryKey(dealer);
	}

	@Override
	public List<DealersNote> getDealerNotes() {
		// TODO Auto-generated method stub
		List<DealersNote> dealersNotes = new ArrayList<DealersNote>();
		List<Dealer> dealers = dealerDao.getDealers(new PageRequest(0, 10000));
		DealersNote sel = null;
		for (Dealer dealer : dealers) {
			boolean b = false;
			for (Iterator iterator = dealersNotes.iterator(); iterator.hasNext();) {
				DealersNote dealersNote = (DealersNote) iterator.next();
				if(dealersNote.getSid()==dealer.getShengfen().getId()){
					b=true;
					sel = dealersNote;
					break;
				}
			}
			if(!b){
				DealersNote sdn = new DealersNote(dealer.getShengfen().getId(), dealer.getShengfen().getName());
				sdn.getDealers().add(dealer);
				dealersNotes.add(sdn);
			}
			else{
				sel.getDealers().add(dealer);
			}
		}
		return dealersNotes;
	}

	@Override
	public DealerGoal getGoalByDid(int did) {
		// TODO Auto-generated method stub
		return goalDao.getByDid(did);
	}

	@Override
	public int createGoal(DealerGoal dealerGoal) {
		// TODO Auto-generated method stub
		DealerGoal goal = goalDao.getByDid(dealerGoal.getDealer().getId());
		if(goal!=null){
			goalDao.deleteByPrimaryKey(goal.getId());
		}
		dealerGoal.setStatus(0);
		int i = goalDao.insert(dealerGoal);
		return i;
	}

	@Override
	public List<List> staticBoxOut(int did, Date stime, Date etime) {
		// TODO Auto-generated method stub
		List<Integer> counts = new ArrayList<Integer>();
		List<Double> amounts = new ArrayList<Double>();
		List<String> labels = new ArrayList<String>();
		List<DeliveryOrder> orders = orderDao.getByDealerAndTime(did, stime, etime);
		if (orders!=null && orders.size()>0) {
			for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
				DeliveryOrder deliveryOrder = (DeliveryOrder) iterator.next();
				if(deliveryOrder.getBoxitems()!=null && deliveryOrder.getBoxitems().size()>0){
					for (Iterator iterator2 = deliveryOrder.getBoxitems().iterator(); iterator2.hasNext();) {
						ProductboxApItem item = (ProductboxApItem) iterator2.next();
						int index = -1;
						for(int i=0;i<labels.size();i++){
							String label = labels.get(i);
							if(label.equals(item.getBox().getName())){
								int count = counts.get(i);
								counts.set(i, count+item.getCount());
								double amount = amounts.get(i);
								amounts.set(i, PublicHelper.correctTo(amount+item.getTotal()/10000));
								index = i;
								break;
							}
						}
						if(index==-1){
							labels.add(item.getBox().getName());
							counts.add(item.getCount());
							amounts.add(PublicHelper.correctTo(item.getTotal()/10000));
						}
					}
				}
			}
		}
		List<List> result = new ArrayList<List>();
		result.add(labels);
		result.add(counts);
		result.add(amounts);
		return result;
	}

	@Override
	public List<EmployeeFee> getFeesByDid(int did, PageRequest pageable) {
		// TODO Auto-generated method stub
		return feeDao.getByDid(did, pageable);
	}

	@Override
	public EmployeeFee getFeeById(int id) {
		// TODO Auto-generated method stub
		return feeDao.selectByPrimaryKey(id);
	}

	@Override
	public List<List> staticExpenses(int did, Date start, Date end) {
		// TODO Auto-generated method stub
		List<String> labels1=new ArrayList<String>();
		labels1.add("让利金额");
		labels1.add("返点金额");
		labels1.add("奖励金额");
		List<Double> values = new ArrayList<Double>();
		values.add(0.0);
		values.add(0.0);
		values.add(0.0);
		List<String> labels2 = new ArrayList<String>();
		List<Double> fees = new ArrayList<Double>();
		List<List> result = new ArrayList<List>();
		List<DealerValue> dealerValues = valueDao.getByDateAndDid(did, start, end);
		if(dealerValues!=null && dealerValues.size()>0){
			for (Iterator iterator = dealerValues.iterator(); iterator.hasNext();) {
				DealerValue dealerValue = (DealerValue) iterator.next();
				if(dealerValue.getPromote()!=null){
					values.set(0,values.get(0)+dealerValue.getPromote());
				}
				if(dealerValue.getRebate()!=null){
					values.set(1, values.get(1)+dealerValue.getRebate());
				}
				if(dealerValue.getReward()!=null){
					values.set(2, values.get(2)+dealerValue.getReward());
				}
			}
		}
		do {
			Date stime = DateTools.getFirstOfMonth(start);
			Date etime = DateTools.getLastOfMonth(start);
			Double mtotal = 0.0;
			List<EmployeeFee> employeeFees = feeDao.getByDateAndDid(did, stime, etime);
			if(employeeFees!=null && employeeFees.size()>0){
				for (Iterator iterator = employeeFees.iterator(); iterator.hasNext();) {
					EmployeeFee employeeFee = (EmployeeFee) iterator.next();
					if(employeeFee.getTotal()!=null){
						mtotal+=employeeFee.getTotal();
					}
				}
			}
			String datestr = DateTools.FormateDateYearMonth(start);
			labels2.add(datestr);
			fees.add(mtotal);
			start=DateTools.getFirstOfNextMonth(start);
		} while (start.getTime()<DateTools.getFirstOfNextMonth(end).getTime());
		result.add(labels1);
		result.add(values);
		result.add(labels2);
		result.add(fees);
		return result;
	}

	@Override
	public List<Dealer> findDealersByEid(String eid, String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return dealerDao.findDealerByEid(eid, text, pageable);
	}

	@Override
	public List<Dealer> getDealersByEid(String eid, PageRequest pageable) {
		// TODO Auto-generated method stub
		return dealerDao.getDealersByEid(eid, pageable);
	}

	@Override
	public int searchCountByEid(String eid, String text) {
		// TODO Auto-generated method stub
		return dealerDao.searchCountByEid(eid, text);
	}

	@Override
	public int allCountByEid(String eid) {
		// TODO Auto-generated method stub
		return dealerDao.allCountByEid(eid);
	}

	@Override
	public List<Dealer> allDealers() {
		// TODO Auto-generated method stub
		return dealerDao.allDealers();
	}

	@Override
	public int feeCountBydid(int did) {
		// TODO Auto-generated method stub
		return feeDao.allCountByDid(did);
	}

	@Override
	public List<DealersNote> getHzDealerNotes() {
		// TODO Auto-generated method stub
		List<DealersNote> dealersNotes = new ArrayList<DealersNote>();
		List<Dealer> dealers = dealerDao.hzDealers();
		DealersNote sel = null;
		for (Dealer dealer : dealers) {
			boolean b = false;
			for (Iterator iterator = dealersNotes.iterator(); iterator.hasNext();) {
				DealersNote dealersNote = (DealersNote) iterator.next();
				if(dealersNote.getSid()==dealer.getShengfen().getId()){
					b=true;
					sel = dealersNote;
					break;
				}
			}
			if(!b){
				DealersNote sdn = new DealersNote(dealer.getShengfen().getId(), dealer.getShengfen().getName());
				sdn.getDealers().add(dealer);
				dealersNotes.add(sdn);
			}
			else{
				sel.getDealers().add(dealer);
			}
		}
		return dealersNotes;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub		
		return dealerDao.deleteByPrimaryKey(id);
	}

}
