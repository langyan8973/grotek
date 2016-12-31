package com.grotek.service.imp;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.PositionDicMapper;
import com.grotek.pojo.PositionDic;
import com.grotek.service.PositionService;
@Service("positionService")
@org.springframework.transaction.annotation.Transactional
public class PositionServiceImp implements PositionService {

	@Autowired
	private PositionDicMapper positiondicDao;
	
	@Override
	public List<PositionDic> getDicstAllPositions() {
		// TODO Auto-generated method stub
		return positiondicDao.getAllPositionDics();
	}

	@Override
	public int check(String name) {
		// TODO Auto-generated method stub
		return positiondicDao.checkName(name);
	}

	@Override
	public int createPosition(String name,int salestargets, String objective, double amortization, double salary, double travelAllow,
			double mobileAllow, String description,double costhour) {
		// TODO Auto-generated method stub
		PositionDic positionDic = new PositionDic();
		positionDic.setName(name);;
		positionDic.setObjective(objective);
		positionDic.setSalestargets(salestargets);
		positionDic.setAmortization(amortization);
		positionDic.setSalary(salary);
		positionDic.setTravelAllow(travelAllow);
		positionDic.setMobileAllow(mobileAllow);
		positionDic.setCosthour(costhour);
		positionDic.setDescription(description);
		positionDic.setDate(new Date());
		positionDic.setStatus(0);
		return positiondicDao.insert(positionDic);
	}

	@Override
	public PositionDic getById(int id) {
		// TODO Auto-generated method stub
		return positiondicDao.selectByPrimaryKey(id);
	}

	@Override
	public int editPositionDic(PositionDic positionDic) {
		// TODO Auto-generated method stub
		positionDic.setDate(new Date());
		positionDic.setStatus(0);
		return positiondicDao.updateByPrimaryKey(positionDic);
	}

	@Override
	public int deletePosition(int id) {
		// TODO Auto-generated method stub
		return positiondicDao.deleteByPrimaryKey(id);
	}

}
