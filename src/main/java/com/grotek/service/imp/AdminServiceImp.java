package com.grotek.service.imp;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grotek.dao.AdminMapper;
import com.grotek.dao.RoleMapper;
import com.grotek.pojo.Admin;
import com.grotek.pojo.Role;
import com.grotek.service.AdminService;
@Service("adminService")
@org.springframework.transaction.annotation.Transactional
public class AdminServiceImp implements AdminService {
	
	@Autowired
	private AdminMapper adminDao;
	
	@Autowired
	private RoleMapper roleDao;

	@Override
	public Admin getById(int id) {
		// TODO Auto-generated method stub
		return adminDao.selectByPrimaryKey(id);
	}

	@Override
	public Admin getByName(String name) {
		// TODO Auto-generated method stub
		return adminDao.getByName(name);
	}

	@Override
	public List<Admin> getAll() {
		// TODO Auto-generated method stub
		return adminDao.getAll();
	}

	@Override
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return roleDao.getAll();
	}

	@Override
	public int createAdmin(Admin admin) {
		// TODO Auto-generated method stub
		admin.setStatus(0);
		return adminDao.insert(admin);
	}

	@Override
	public int editAdmin(Admin admin) {
		// TODO Auto-generated method stub
		admin.setStatus(0);
		return adminDao.updateByPrimaryKey(admin);
	}

	@Override
	public int deleteAdmin(int id) {
		// TODO Auto-generated method stub
		return adminDao.deleteByPrimaryKey(id);
	}

	
}
