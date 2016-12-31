package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.ManureTypeMapper;
import com.grotek.pojo.ManureType;
import com.grotek.service.ManureTypeService;
@Service("manuretypeService")
@org.springframework.transaction.annotation.Transactional
public class ManureTypeServiceImp implements ManureTypeService {

	@Autowired
	private ManureTypeMapper dao;
	
	@Override
	public int insertOne(ManureType type) {
		// TODO Auto-generated method stub
		type.setStatus(0);
		return dao.insert(type);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateOne(ManureType type) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(type);
	}

	@Override
	public ManureType getById(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<ManureType> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public int check(String name) {
		// TODO Auto-generated method stub
		return dao.checkName(name);
	}

}
