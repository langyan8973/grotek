package com.grotek.service.imp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.WeeklyItemMapper;
import com.grotek.dao.WeeklyMapper;
import com.grotek.dto.PieDto;
import com.grotek.dto.ServiceDealerDto;
import com.grotek.dto.WeeklyItemDto;
import com.grotek.pojo.Weekly;
import com.grotek.pojo.WeeklyItem;
import com.grotek.service.WeekliesService;
import com.grotek.util.DateTools;
import com.grotek.util.PublicHelper;
@Service("weekliesService")
@org.springframework.transaction.annotation.Transactional
public class WeekliesServiceImp implements WeekliesService {
	
	@Autowired
	private WeeklyMapper dao;
	
	@Autowired
	private WeeklyItemMapper itemDao;

	@Override
	public List<Weekly> getByEid(String eid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getWorksByEid(eid, pageRequest);
	}

	@Override
	public int allCountByEid(String eid) {
		// TODO Auto-generated method stub
		return dao.allCountByEid(eid);
	}

	@Override
	public List<Weekly> getBySid(String sid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getWorksByLeader(sid, pageRequest);
	}

	@Override
	public int allCountBySid(String sid) {
		// TODO Auto-generated method stub
		return dao.allCountBySid(sid);
	}

	@Override
	public Weekly getByid(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int createWeekly(Weekly weekly) {
		// TODO Auto-generated method stub
		weekly.setStatus(0);
		return dao.insert(weekly);
	}

	@Override
	public int editWeekly(Weekly weekly) {
		// TODO Auto-generated method stub
		weekly.setStatus(0);
		return dao.updateByPrimaryKey(weekly);
	}

	@Override
	public Weekly getByDate(String eid,Date date) {
		// TODO Auto-generated method stub
		return dao.getByDate(eid, date);
	}

	@Override
	public int deleteItem(int id) {
		// TODO Auto-generated method stub
		return itemDao.deleteByPrimaryKey(id);
	}

	@Override
	public int addItem(WeeklyItem item) {
		// TODO Auto-generated method stub
		item.setStatus(0);
		return itemDao.insert(item);
	}

	@Override
	public List<List> staticLaborHours(String eid, Date start, Date end) {
		// TODO Auto-generated method stub
		List<String> dates = new ArrayList<String>();
		List<Double> hours = new ArrayList<Double>();
		do {
			List<Weekly> weeklys = dao.getCheckedByDate(eid, start);
			Double hour = 0.0;
			if(weeklys!=null && weeklys.size()>0){
				for (Iterator iterator = weeklys.iterator(); iterator.hasNext();) {
					Weekly weekly = (Weekly) iterator.next();
					if(weekly.getItems()!=null && weekly.getItems().size()>0){						
						for (Iterator iterator1 = weekly.getItems().iterator(); iterator1.hasNext();) {
							WeeklyItem item = (WeeklyItem) iterator1.next();
							if(item.getTotal()!=null){
								hour+=item.getTotal();
							}
						}
					}
				}
				Date mon = DateTools.getMonday(start);
				Date sun = DateTools.getSunday(start);
				String datestr = DateTools.FormateDateOnlyMonth(mon)+"-"+DateTools.FormateDateOnlyMonth(sun);
				dates.add(datestr);
				hours.add(hour);
			}
			else{
				Date mon = DateTools.getMonday(start);
				Date sun = DateTools.getSunday(start);
				String datestr = DateTools.FormateDateOnlyMonth(mon)+"-"+DateTools.FormateDateOnlyMonth(sun);
				dates.add(datestr);
				hours.add(hour);
			}
			start = DateTools.getAfterWeek(start);
		} while (start.getTime()<end.getTime());
		List<List> result = new ArrayList<List>();
		result.add(dates);
		result.add(hours);
		return result;
	}

	@Override
	public List<List> staticDealerService(String eid, Date start, Date end, double consthour) {
		// TODO Auto-generated method stub
		List<PieDto> pieHour = new ArrayList<PieDto>();
		List<PieDto> pieConst = new ArrayList<PieDto>();
		List<ServiceDealerDto> services = new ArrayList<ServiceDealerDto>();
		do {
			List<Weekly> weeklys = dao.getCheckedByDate(eid, start);
			Double hour = 0.0;
			if(weeklys!=null && weeklys.size()>0){
				for (Iterator iterator = weeklys.iterator(); iterator.hasNext();) {
					Weekly weekly = (Weekly) iterator.next();
					if(weekly.getItems()!=null && weekly.getItems().size()>0){						
						for (Iterator iterator1 = weekly.getItems().iterator(); iterator1.hasNext();) {
							WeeklyItem item = (WeeklyItem) iterator1.next();
							boolean finded = false;
							for (Iterator iterator2 = services.iterator(); iterator2.hasNext();) {
								ServiceDealerDto serviceDealerDto = (ServiceDealerDto) iterator2.next();
								if(serviceDealerDto.getDid()==item.getDealer().getId()){
									finded=true;
									WeeklyItemDto wItemDto = new WeeklyItemDto(item);
									wItemDto.setStime(DateTools.FormateDateShort(weekly.getStime()));
									wItemDto.setEtime(DateTools.FormateDateShort(weekly.getStime()));
									serviceDealerDto.getItems().add(wItemDto);
									serviceDealerDto.setCount(serviceDealerDto.getCount()+1);
									break;
								}
							}
							if(!finded){
								ServiceDealerDto serviceDealerDto = new ServiceDealerDto();
								serviceDealerDto.setDid(item.getDealer().getId());
								serviceDealerDto.setDname(item.getDealer().getName());
								serviceDealerDto.setCount(1);
								WeeklyItemDto wItemDto = new WeeklyItemDto(item);
								wItemDto.setStime(DateTools.FormateDateShort(weekly.getStime()));
								wItemDto.setEtime(DateTools.FormateDateShort(weekly.getEtime()));
								serviceDealerDto.setItems(new ArrayList<WeeklyItemDto>());
								serviceDealerDto.getItems().add(wItemDto);
								services.add(serviceDealerDto);
								
								PieDto hourDto = new PieDto();
								hourDto.setLabel(item.getDealer().getName());
								String color = PublicHelper.randomColor();
								hourDto.setHighlight(color);
								hourDto.setColor(color);
								hourDto.setValue(item.getTotal());
								pieHour.add(hourDto);
								
								PieDto costDto = new PieDto();
								costDto.setLabel(item.getDealer().getName());
								String color1 = PublicHelper.randomColor();
								costDto.setHighlight(color1);
								costDto.setColor(color1);
								DecimalFormat df=new DecimalFormat(".##");
								double d=item.getTotal()*consthour;
								String st=df.format(d);
								costDto.setValue(Double.parseDouble(st));
								pieConst.add(costDto);
							}else{
								for (Iterator iterator2 = pieHour.iterator(); iterator2.hasNext();) {
									PieDto hourDto = (PieDto) iterator2.next();
									if(hourDto.getLabel().equals(item.getDealer().getName())){
										hourDto.setValue(hourDto.getValue()+item.getTotal());
										break;
									}									
								}
								for (Iterator iterator2 = pieConst.iterator(); iterator2.hasNext();) {
									PieDto costDto = (PieDto) iterator2.next();
									if(costDto.getLabel().equals(item.getDealer().getName())){
										DecimalFormat df=new DecimalFormat(".##");
										double d=costDto.getValue()+item.getTotal()*consthour;
										String st=df.format(d);
										costDto.setValue(Double.parseDouble(st));
										break;
									}
								}
							}
						}
					}
				}
				
			}
			start = DateTools.getAfterWeek(start);
		} while (start.getTime()<end.getTime());
		List<List> result = new ArrayList<List>();
		result.add(pieHour);
		result.add(pieConst);
		result.add(services);
		return result;
	}

	@Override
	public List<Weekly> allByEid(String eid,PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.allWorksByEid(eid,pageRequest);
	}

	@Override
	public int checkedCount(String eid) {
		// TODO Auto-generated method stub
		return dao.allCheckedCountByEid(eid);
	}

	@Override
	public List<Weekly> getCheckPending(String sid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getCheckPendingWorks(sid, pageRequest);
	}

	@Override
	public int allCountCheckPending(String sid) {
		// TODO Auto-generated method stub
		return dao.allCountCheckPending(sid);
	}

	@Override
	public List<Weekly> getChecked(String sid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getCheckedWorks(sid, pageRequest);
	}

	@Override
	public int allCountChecked(String sid) {
		// TODO Auto-generated method stub
		return dao.allCountChecked(sid);
	}

	@Override
	public List<Weekly> getACheckPending(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getACheckPendingWorks(pageRequest);
	}

	@Override
	public int allCountACheckPending() {
		// TODO Auto-generated method stub
		return dao.allCountACheckPending();
	}

	@Override
	public List<Weekly> getAChecked(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return dao.getACheckedWorks(pageRequest);
	}

	@Override
	public int allCountAChecked() {
		// TODO Auto-generated method stub
		return dao.allCountAChecked();
	}

}
