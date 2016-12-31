package com.grotek.service.imp;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.EmployeePromMapper;
import com.grotek.pojo.Employee;
import com.grotek.pojo.EmployeeProm;
import com.grotek.service.PromoteService;
@Service("promoteService")
@org.springframework.transaction.annotation.Transactional
public class PromoteServiceImp implements PromoteService {
	
	@Autowired
	private EmployeePromMapper employeePromDao;

	@Override
	public List<EmployeeProm> getPromotes(PageRequest pageable) {
		// TODO Auto-generated method stub
		return employeePromDao.getPromotes(pageable);
	}

	@Override
	public List<EmployeeProm> getEmployeePromsByEid(String eid) {
		// TODO Auto-generated method stub
		return employeePromDao.getEmployeePromsByEid(eid);
	}

	@Override
	public int addProm(Employee employee, String des) {
		// TODO Auto-generated method stub
		EmployeeProm employeeProm = new EmployeeProm();
		employeeProm.setEmployee(employee);
		employeeProm.setDate(new Date());
		employeeProm.setEvaluate(des);
		employeeProm.setPosition(employee.getPosition());
		employeeProm.setStatus(0);
		employeeProm.setSuperior(employee.getSname());
		return employeePromDao.insert(employeeProm);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return employeePromDao.allCount();
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return employeePromDao.deleteByPrimaryKey(id);
	}

}
