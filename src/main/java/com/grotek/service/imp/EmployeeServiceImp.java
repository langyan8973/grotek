package com.grotek.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.DealerValueMapper;
import com.grotek.dao.EmployeeGasolineMapper;
import com.grotek.dao.EmployeeMapper;
import com.grotek.dao.EmployeePromMapper;
import com.grotek.dao.EmployeeRewardMapper;
import com.grotek.dao.ExsRbsmMapper;
import com.grotek.dao.JobtypeDicMapper;
import com.grotek.dao.TravelRbsmMapper;
import com.grotek.dao.WeeklyMapper;
import com.grotek.dto.EmployeeNote;
import com.grotek.dto.PieDto;
import com.grotek.dto.SuperiorsNote;
import com.grotek.pojo.Dealer;
import com.grotek.pojo.DealerValue;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeGasoline;
import com.grotek.pojo.EmployeeProm;
import com.grotek.pojo.ExsRbsm;
import com.grotek.pojo.JobtypeDic;
import com.grotek.pojo.TravelRbsm;
import com.grotek.pojo.Weekly;
import com.grotek.pojo.WeeklyItem;
import com.grotek.service.EmployeeService;
import com.grotek.util.PublicHelper;
@Service("employeeService")
@org.springframework.transaction.annotation.Transactional
public class EmployeeServiceImp implements EmployeeService {
	
	@Autowired
	private EmployeeMapper employeeDao;
	@Autowired
	private EmployeePromMapper employeePromDao;
	@Autowired
	private EmployeeRewardMapper employeeRewardDao;
	@Autowired
	private EmployeeGasolineMapper gasolineDao;
	@Autowired
	private WeeklyMapper weeklyDao;
	@Autowired
	private TravelRbsmMapper travelDao;
	@Autowired
	private DealerValueMapper valueDao;
	@Autowired
	private ExsRbsmMapper exsDao;
	@Autowired
	private JobtypeDicMapper typeDao;

	@Override
	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString();
		employee.setStatus(0);
		employee.setId(id);
		employee.setPassword("123456");
		int i = employeeDao.insert(employee);
		EmployeeProm employeeProm = new EmployeeProm();
		employeeProm.setEmployee(employee);
		employeeProm.setDate(new Date());
		employeeProm.setEvaluate("登记");
		if(employee.getPosition()!=null){
			employeeProm.setPosition(employee.getPosition());
		}
		if(employee.getSuperior()!=null){
			employeeProm.setSuperior(employee.getSname());
		}
		employeeProm.setStatus(0);
		employeePromDao.insert(employeeProm);
		return i;
	}



	@Override
	public List<Employee> findEmployees(String text, PageRequest pageable) {
		// TODO Auto-generated method stub
		return employeeDao.findEmployee(text, pageable);
	}



	@Override
	public List<Employee> getEmployees(PageRequest pageable) {
		// TODO Auto-generated method stub
		return employeeDao.getEmployees(pageable);
	}



	@Override
	public int searchCount(String text) {
		// TODO Auto-generated method stub
		return employeeDao.searchCount(text);
	}



	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return employeeDao.allCount();
	}



	@Override
	public int check(String code) {
		// TODO Auto-generated method stub
		return employeeDao.checkCode(code);
	}



	@Override
	public Employee getById(String id) {
		// TODO Auto-generated method stub
		return employeeDao.selectByPrimaryKey(id);
	}



	@Override
	public List<SuperiorsNote> getSuperiors(PageRequest pageable) {
		// TODO Auto-generated method stub
		List<SuperiorsNote> superiorsNotes = new ArrayList<SuperiorsNote>();
		List<Employee> employees = employeeDao.getEmployees(pageable);
		SuperiorsNote sel = null;
		for (Employee employee : employees) {
			boolean b = false;
			for (Iterator iterator = superiorsNotes.iterator(); iterator.hasNext();) {
				SuperiorsNote superiorsNote = (SuperiorsNote) iterator.next();
				if(superiorsNote.getPositionid()==employee.getPosition().getId()){
					b=true;
					sel = superiorsNote;
					break;
				}
			}
			if(!b){
				SuperiorsNote sn = new SuperiorsNote(employee.getPosition().getId(), employee.getPosition().getName());
				sn.getItems().add(employee);
				superiorsNotes.add(sn);
			}
			else{
				sel.getItems().add(employee);
			}
		}
		return superiorsNotes;
	}


	@Override
	public int editEmployee(Employee employee) {
		// TODO Auto-generated method stub
		
		return employeeDao.updateByPrimaryKey(employee);
	}



	@Override
	public Employee getByMobile(String mobile) {
		// TODO Auto-generated method stub
		return employeeDao.getByMobile(mobile);
	}



	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return employeeDao.deleteByPrimaryKey(id);
	}



	@Override
	public EmployeeGasoline getGasolineByEid(String eid) {
		// TODO Auto-generated method stub
		return gasolineDao.getByEid(eid);
	}



	@Override
	public int deleteGasoline(int id) {
		// TODO Auto-generated method stub
		return gasolineDao.deleteByPrimaryKey(id);
	}



	@Override
	public int addGasoline(EmployeeGasoline employeeGasoline) {
		// TODO Auto-generated method stub
		employeeGasoline.setStatus(0);
		return gasolineDao.insert(employeeGasoline);
	}



	@Override
	public List<List> totalCost(Employee employee, Date start, Date end, Dealer dealer) {
		// TODO Auto-generated method stub	
		List<List> result = new ArrayList<List>();
		List<Weekly> weeklies = weeklyDao.getByStartAndEnd(employee.getId(), start, end);
		List<TravelRbsm> travelRbsms;
		List<DealerValue> values;
		List<ExsRbsm> exsRbsms;
		double total=0.0;
		List<PieDto> pies = new ArrayList<PieDto>();
		PieDto weeklyDto = new PieDto();
		weeklyDto.setLabel("工作费用");
		String color = PublicHelper.randomColor();
		weeklyDto.setColor(color);
		weeklyDto.setHighlight(color);
		weeklyDto.setValue(0);
		if(dealer==null){
			travelRbsms = travelDao.getByEidAndDate(employee.getId(), start, end);
			values = valueDao.getByDateAndEid(employee.getId(), start, end);
			exsRbsms = exsDao.getByEidAndDate(employee.getId(), start, end);
			if(weeklies!=null && weeklies.size()>0){
				for (Iterator iterator = weeklies.iterator(); iterator.hasNext();) {
					Weekly weekly = (Weekly) iterator.next();
					for (Iterator iterator2 = weekly.getItems().iterator(); iterator2.hasNext();) {
						WeeklyItem item = (WeeklyItem) iterator2.next();
						weeklyDto.setValue(weeklyDto.getValue()
								+PublicHelper.correctTo(item.getTotal()*employee.getPosition().getCosthour()));
					}
				}
			}
		}
		else{
			travelRbsms = travelDao.getByEDAndDate(employee.getId(), dealer.getId(), start, end);
			values = valueDao.getByDateAndDid(dealer.getId(), start, end);
			exsRbsms = exsDao.getByDidAndDate(dealer.getId(), start, end);
			if(weeklies!=null && weeklies.size()>0){
				for (Iterator iterator = weeklies.iterator(); iterator.hasNext();) {
					Weekly weekly = (Weekly) iterator.next();
					for (Iterator iterator2 = weekly.getItems().iterator(); iterator2.hasNext();) {
						WeeklyItem item = (WeeklyItem) iterator2.next();
						if(item.getDealer().getId()==dealer.getId()){
							weeklyDto.setValue(weeklyDto.getValue()
									+PublicHelper.correctTo(item.getTotal()*employee.getPosition().getCosthour()));
						}
					}
				}
			}
		}
		PieDto travelDto = new PieDto();
		travelDto.setLabel("出差报销费用");
		color = PublicHelper.randomColor();
		travelDto.setColor(color);
		travelDto.setHighlight(color);
		travelDto.setValue(0);
		if(travelRbsms!=null && travelRbsms.size()>0){
			for (Iterator iterator = travelRbsms.iterator(); iterator.hasNext();) {
				TravelRbsm travelRbsm = (TravelRbsm) iterator.next();
				travelDto.setValue(travelDto.getValue()+travelRbsm.getSum());
			}
		}
		PieDto exsDto = new PieDto();
		exsDto.setLabel("市场开拓费用");
		color = PublicHelper.randomColor();
		exsDto.setColor(color);
		exsDto.setHighlight(color);
		exsDto.setValue(0);
		if(exsRbsms!=null && exsRbsms.size()>0){
			for (Iterator iterator = exsRbsms.iterator(); iterator.hasNext();) {
				ExsRbsm rbsm = (ExsRbsm) iterator.next();
				exsDto.setValue(exsDto.getValue()+rbsm.getSum());
			}
		}
		PieDto valueDto = new PieDto();
		valueDto.setLabel("支持费用");
		color = PublicHelper.randomColor();
		valueDto.setColor(color);
		valueDto.setHighlight(color);
		valueDto.setValue(0);
		if(values!=null && values.size()>0){
			for (Iterator iterator = values.iterator(); iterator.hasNext();) {
				DealerValue dealerValue = (DealerValue) iterator.next();
				double sum = 0;
				if(dealerValue.getGift()!=null){
					sum+=dealerValue.getGift();
				}
				if(dealerValue.getPromote()!=null){
					sum+=dealerValue.getPromote();
				}
				if(dealerValue.getRebate()!=null){
					sum+=dealerValue.getRebate();
				}
				if(dealerValue.getReward()!=null){
					sum+=dealerValue.getReward();
				}
				valueDto.setValue(valueDto.getValue()+sum);
			}
		}
		pies.add(weeklyDto);
		total+=weeklyDto.getValue();
		pies.add(travelDto);
		total+=travelDto.getValue();
		pies.add(exsDto);
		total+=exsDto.getValue();
		pies.add(valueDto);
		total+=valueDto.getValue();
		List<Double> dList = new ArrayList<Double>();
		dList.add(PublicHelper.correctTo(total));
		result.add(pies);
		result.add(dList);
		return result;
	}



	@Override
	public List<List> jobtype(Employee employee, Date start, Date end, Dealer dealer) {
		// TODO Auto-generated method stub
		List<PieDto> hourdtos = new ArrayList<PieDto>();
		List<PieDto> costdtos = new ArrayList<PieDto>();
		List<List> result = new ArrayList<List>();
		List<Weekly> weeklies = weeklyDao.getByStartAndEnd(employee.getId(), start, end);
		List<JobtypeDic> types = typeDao.getAll();
		String color;
		for (Iterator iterator = types.iterator(); iterator.hasNext();) {
			JobtypeDic jobtypeDic = (JobtypeDic) iterator.next();
			PieDto hourdto = new PieDto();
			hourdto.setLabel(jobtypeDic.getName());
			color = PublicHelper.randomColor();
			hourdto.setColor(color);
			hourdto.setHighlight(color);
			hourdto.setValue(0.0);
			hourdtos.add(hourdto);
			
			PieDto costdto = new PieDto();
			costdto.setLabel(jobtypeDic.getName());
			color = PublicHelper.randomColor();
			costdto.setColor(color);
			costdto.setHighlight(color);
			costdto.setValue(0.0);
			costdtos.add(costdto);
		}
		if(dealer==null){
			if(weeklies!=null && weeklies.size()>0){
				for (Iterator iterator = weeklies.iterator(); iterator.hasNext();) {
					Weekly weekly = (Weekly) iterator.next();
					for (Iterator iterator2 = weekly.getItems().iterator(); iterator2.hasNext();) {
						WeeklyItem item = (WeeklyItem) iterator2.next();
						for(int i=0;i<hourdtos.size();i++){
							PieDto hourdto = hourdtos.get(i);
							if(hourdto!=null && item.getType()!=null && hourdto.getLabel().equals(item.getType().getName())){
								hourdto.setValue(hourdto.getValue()+item.getTotal());
								PieDto costdto = costdtos.get(i);
								costdto.setValue(costdto.getValue()+PublicHelper.correctTo(item.getTotal()*employee.getPosition().getCosthour()));
								break;
							}
						}
					}
				}
			}
		}
		else{
			if(weeklies!=null && weeklies.size()>0){
				for (Iterator iterator = weeklies.iterator(); iterator.hasNext();) {
					Weekly weekly = (Weekly) iterator.next();
					for (Iterator iterator2 = weekly.getItems().iterator(); iterator2.hasNext();) {
						WeeklyItem item = (WeeklyItem) iterator2.next();
						if(item.getDealer().getId()!=dealer.getId())
							continue;
						for(int i=0;i<hourdtos.size();i++){
							PieDto hourdto = hourdtos.get(i);
							if(hourdto!=null && hourdto.getLabel().equals(item.getType().getName())){
								hourdto.setValue(hourdto.getValue()+item.getTotal());
								PieDto costdto = costdtos.get(i);
								costdto.setValue(costdto.getValue()+PublicHelper.correctTo(item.getTotal()*employee.getPosition().getCosthour()));
								break;
							}
						}
					}
				}
			}
		}
		result.add(hourdtos);
		result.add(costdtos);
		return result;
	}



	@Override
	public List<Employee> findMembers(String text, String sid, PageRequest pageable) {
		// TODO Auto-generated method stub
		return employeeDao.findMembers(text, sid, pageable);
	}



	@Override
	public List<EmployeeNote> getMembers(String sid) {
		// TODO Auto-generated method stub
		List<EmployeeNote> notes = new ArrayList<EmployeeNote>();
		getchilds(sid, notes);
		return notes;
	}
	
	public void getchilds(String sid,List<EmployeeNote> notes){
		List<Employee> employees = employeeDao.getMembers(sid, new PageRequest(0, 10000));
		if(employees!=null && employees.size()>0){
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				EmployeeNote note = new EmployeeNote(employee);
				getchilds(note.getId(), note.getNotes());
				notes.add(note);
			}
		}
	}



	@Override
	public int searchCountMembers(String text, String sid) {
		// TODO Auto-generated method stub
		return employeeDao.searchCountMembers(text, sid);
	}



	@Override
	public int allCountMembers(String sid) {
		// TODO Auto-generated method stub
		return employeeDao.allCountMembers(sid);
	}

}
