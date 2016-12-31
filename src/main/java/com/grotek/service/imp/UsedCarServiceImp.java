package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.grotek.dao.UsedCarMapper;
import com.grotek.pojo.UsedCar;
import com.grotek.service.UsedCarService;
@Service("usedcarService")
@org.springframework.transaction.annotation.Transactional
public class UsedCarServiceImp implements UsedCarService {
	
	@Autowired
	private UsedCarMapper dao;

	@Override
	public List<UsedCar> findByFullname(String name, PageRequest pageable) {
		// TODO Auto-generated method stub
		return dao.findByName(name, pageable);
	}

	@Override
	public List<UsedCar> getUsedCars(PageRequest pageable) {
		// TODO Auto-generated method stub
		return dao.getUsedCars(pageable);
	}

	@Override
	public int searchCount(String name) {
		// TODO Auto-generated method stub
		return dao.searchCount(name);
	}

	@Override
	public int allCount() {
		// TODO Auto-generated method stub
		return dao.allCount();
	}

	@Override
	public UsedCar getById(int id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<UsedCar> getByTiid(int tiid) {
		// TODO Auto-generated method stub
		return dao.getByTiid(tiid);
	}

	@Override
	public int addUsedCar(UsedCar usedCar) {
		// TODO Auto-generated method stub
		usedCar.setStatus(0);
		return dao.insert(usedCar);
	}

	@Override
	public int editUsedCar(UsedCar usedCar) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(usedCar);
	}

	@Override
	public int deleteOne(int id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int passOne(int id) {
		// TODO Auto-generated method stub
		return dao.passByid(id);
	}

}
