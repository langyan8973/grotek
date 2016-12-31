package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.JobtypeDicMapper;
import com.grotek.pojo.JobtypeDic;
import com.grotek.service.JobTypeService;
@Service("jobtypeService")
@org.springframework.transaction.annotation.Transactional
public class JobTypeServiceImp implements JobTypeService {

	@Autowired
	private JobtypeDicMapper typeDao;
	
	@Override
	public List<JobtypeDic> getAll() {
		// TODO Auto-generated method stub
		return typeDao.getAll();
	}

	@Override
	public JobtypeDic getById(int id) {
		// TODO Auto-generated method stub
		return typeDao.selectByPrimaryKey(id);
	}

	@Override
	public int addType(JobtypeDic type) {
		// TODO Auto-generated method stub
		type.setStatus(0);
		return typeDao.insert(type);
	}

	@Override
	public int editType(JobtypeDic type) {
		// TODO Auto-generated method stub
		return typeDao.updateByPrimaryKey(type);
	}

	@Override
	public int check(String name, String code) {
		// TODO Auto-generated method stub
		return typeDao.checkNameAndCode(name, code);
	}

	@Override
	public int deleteByid(int id) {
		// TODO Auto-generated method stub
		return typeDao.deleteByPrimaryKey(id);
	}

}
