package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.ExsTypeMapper;
import com.grotek.pojo.ExsType;
import com.grotek.service.ExsTypeService;
@Service("exstypeService")
@org.springframework.transaction.annotation.Transactional
public class ExsTypeServiceImp implements ExsTypeService {

	@Autowired
	private ExsTypeMapper dao;
	
	@Override
	public List<ExsType> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public ExsType getById(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int addType(ExsType type) {
		// TODO Auto-generated method stub
		type.setStatus(0);
		return dao.insert(type);
	}

	@Override
	public int editType(ExsType type) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(type);
	}

	@Override
	public int check(String name) {
		// TODO Auto-generated method stub
		return dao.checkName(name);
	}

	@Override
	public int deleteByid(int id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

}
