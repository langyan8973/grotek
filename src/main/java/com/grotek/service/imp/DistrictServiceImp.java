package com.grotek.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.DistrictMapper;
import com.grotek.dto.DistrictDto;
import com.grotek.pojo.District;
import com.grotek.service.DistrictService;
@Service("districtService")
@org.springframework.transaction.annotation.Transactional
public class DistrictServiceImp implements DistrictService {

	@Autowired
	private DistrictMapper districtDao;
	
	@Override
	public List<District> getDistricts() {
		// TODO Auto-generated method stub
		return districtDao.getAll();
	}

	@Override
	public District getById(int id) {
		// TODO Auto-generated method stub
		return districtDao.selectByPrimaryKey(id);
	}

	@Override
	public int createDistrict(District district) {
		// TODO Auto-generated method stub
		district.setStatus(0);
		return districtDao.insert(district);
	}

	@Override
	public int deleteByid(int id) {
		// TODO Auto-generated method stub
		return districtDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<DistrictDto> getDistrictTree() {
		// TODO Auto-generated method stub
		List<District> districts = districtDao.getAll();
		List<DistrictDto> dtos = getChildrens(null, districts);
		return dtos;
	}

	private List<DistrictDto> getChildrens(Integer parentid, List<District> districts) {
		List<DistrictDto> childrens = new ArrayList<DistrictDto>();
		for (int i=0;i<districts.size();i++) {
			District district = districts.get(i);
			Integer pInteger = district.getParentid();
			if(parentid==null){
				if (pInteger == parentid) {
					DistrictDto dto = new DistrictDto(district);
					childrens.add(dto);
					dto.setChilds(getChildrens(district.getId(), districts));
				}
			}
			else{
				if (parentid.equals(pInteger)) {
					DistrictDto dto = new DistrictDto(district);
					childrens.add(dto);
					dto.setChilds(getChildrens(district.getId(), districts));
				}
			}			
		}
		return childrens;
	}
}
