package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.TravelExsTypeMapper;
import com.grotek.pojo.TravelExsType;
import com.grotek.service.TravelExsTypeService;
@Service("travelexstypeService")
@org.springframework.transaction.annotation.Transactional
public class TravelExsTypeServiceImp implements TravelExsTypeService {
	
	@Autowired
	private TravelExsTypeMapper typeDao;

	@Override
	public List<TravelExsType> getAll() {
		// TODO Auto-generated method stub
		return typeDao.getAll();
	}

	@Override
	public TravelExsType getById(int id) {
		// TODO Auto-generated method stub
		return typeDao.selectByPrimaryKey(id);
	}

	@Override
	public int addType(TravelExsType type) {
		// TODO Auto-generated method stub
		type.setStatus(0);
		return typeDao.insert(type);
	}

	@Override
	public int editType(TravelExsType type) {
		// TODO Auto-generated method stub
		return typeDao.updateByPrimaryKey(type);
	}

	@Override
	public int check(String name) {
		// TODO Auto-generated method stub
		return typeDao.checkName(name);
	}

	@Override
	public int deleteByid(int id) {
		// TODO Auto-generated method stub
		return typeDao.deleteByPrimaryKey(id);
	}

}
