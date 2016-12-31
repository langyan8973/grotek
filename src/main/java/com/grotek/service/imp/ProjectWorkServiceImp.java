package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.grotek.dao.ProjectWorkMapper;
import com.grotek.pojo.ProjectWork;
import com.grotek.service.ProjectWorkService;
@Service("projectworkService")
@org.springframework.transaction.annotation.Transactional
public class ProjectWorkServiceImp implements ProjectWorkService {
	
	@Autowired
	private ProjectWorkMapper workDao;

	@Override
	public List<ProjectWork> getByEid(String eid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return workDao.getWorksByEid(eid, pageRequest);
	}

	@Override
	public int allCountByEid(String eid) {
		// TODO Auto-generated method stub
		return workDao.allCountByEid(eid);
	}

	@Override
	public List<ProjectWork> findWorks(String eid, String key, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return workDao.findWorks(eid, key, pageRequest);
	}

	@Override
	public int searchCountEid(String eid, String key) {
		// TODO Auto-generated method stub
		return workDao.searchCountByEid(eid, key);
	}

	@Override
	public List<ProjectWork> getBySid(String sid, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return workDao.getWorksByLeader(sid, pageRequest);
	}

	@Override
	public int allCountBySid(String sid) {
		// TODO Auto-generated method stub
		return workDao.allCountBySid(sid);
	}

	@Override
	public ProjectWork getByid(int id) {
		// TODO Auto-generated method stub
		return workDao.selectByPrimaryKey(id);
	}

	@Override
	public int createProjectWork(ProjectWork projectWork) {
		// TODO Auto-generated method stub
		projectWork.setStatus(0);
		return workDao.insert(projectWork);
	}

	@Override
	public int editProjectWork(ProjectWork projectWork) {
		// TODO Auto-generated method stub
		return workDao.updateByPrimaryKey(projectWork);
	}

	@Override
	public int deleteProjectWork(int id) {
		// TODO Auto-generated method stub
		return workDao.deleteByPrimaryKey(id);
	}

}
