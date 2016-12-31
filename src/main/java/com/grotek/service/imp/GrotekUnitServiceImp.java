package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.GrotekUnitMapper;
import com.grotek.pojo.GrotekUnit;
import com.grotek.service.GrotekUnitService;
@Service("grotekunitService")
@org.springframework.transaction.annotation.Transactional
public class GrotekUnitServiceImp implements GrotekUnitService {
	
	@Autowired
	private GrotekUnitMapper dao;

	@Override
	public GrotekUnit getById(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<GrotekUnit> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public int addOne(GrotekUnit unit) {
		// TODO Auto-generated method stub
		unit.setStatus(0);
		return dao.insert(unit);
	}

	@Override
	public int editOne(GrotekUnit unit) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(unit);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int check(String name) {
		// TODO Auto-generated method stub
		return dao.checkName(name);
	}

}
