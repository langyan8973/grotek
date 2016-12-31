package com.grotek.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.grotek.pojo.ProjectWork;

public interface ProjectWorkService {
	
	public List<ProjectWork> getByEid(String eid,PageRequest pageRequest);
	
	public int allCountByEid(String eid);
	
	public List<ProjectWork> findWorks(String eid,String key,PageRequest pageRequest);
	
	public int searchCountEid(String eid,String key);
	
	public List<ProjectWork> getBySid(String sid,PageRequest pageRequest);
	
	public int allCountBySid(String sid);
	
	public ProjectWork getByid(int id);
	
	public int createProjectWork(ProjectWork projectWork);
	
	public int editProjectWork(ProjectWork projectWork);
	
	public int deleteProjectWork(int id);
}
