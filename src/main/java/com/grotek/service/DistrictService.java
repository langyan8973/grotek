package com.grotek.service;

import java.util.List;

import com.grotek.dto.DistrictDto;
import com.grotek.pojo.District;

public interface DistrictService {

	public List<District> getDistricts();
	
	public District getById(int id);
	
	public int createDistrict(District district);
	
	public int deleteByid(int id);
	
	public List<DistrictDto> getDistrictTree();
	
}
