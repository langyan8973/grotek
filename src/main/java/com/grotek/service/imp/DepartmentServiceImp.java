package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.DepartmentDicMapper;
import com.grotek.pojo.DepartmentDic;
import com.grotek.service.DepartmentService;
@Service("departmentService")
@org.springframework.transaction.annotation.Transactional
public class DepartmentServiceImp implements DepartmentService {

	@Autowired
	private DepartmentDicMapper departmentDicDao;
	
	@Override
	public List<DepartmentDic> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentDicDao.getAllDepartmentDics();
	}

	@Override
	public int createDept(String name, String code) {
		// TODO Auto-generated method stub
		DepartmentDic departmentDic = new DepartmentDic();
		departmentDic.setName(name);
		departmentDic.setCode(code);
		departmentDic.setStatus(0);
		return departmentDicDao.insert(departmentDic);
	}

	@Override
	public int check(String name, String code) {
		// TODO Auto-generated method stub
		return departmentDicDao.checkNameAndCode(name, code);
	}

	@Override
	public DepartmentDic getByid(int id) {
		// TODO Auto-generated method stub
		return departmentDicDao.selectByPrimaryKey(id);
	}

	@Override
	public int editDept(DepartmentDic departmentDic) {
		// TODO Auto-generated method stub
		return departmentDicDao.updateByPrimaryKey(departmentDic);
	}

	@Override
	public int deleteDept(int id) {
		// TODO Auto-generated method stub
		return departmentDicDao.deleteByPrimaryKey(id);
	}

}
