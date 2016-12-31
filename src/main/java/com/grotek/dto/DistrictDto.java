package com.grotek.dto;

import java.io.Serializable;
import java.util.List;

import com.grotek.pojo.District;

public class DistrictDto implements Serializable {

	private Integer id;
    private String name;
    private Integer parentid;
    private Integer status;
    private List<DistrictDto> childs;
    
    public DistrictDto(District dis){
    	this.id = dis.getId();
    	this.name = dis.getName();
    	this.parentid = dis.getParentid();
    	this.status = dis.getStatus();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
	public List<DistrictDto> getChilds() {
		return childs;
	}
	public void setChilds(List<DistrictDto> childs) {
		this.childs = childs;
	}
    
}
