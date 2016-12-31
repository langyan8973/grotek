package com.grotek.service;

import java.util.List;

import com.grotek.pojo.Admin;
import com.grotek.pojo.Role;

public interface AdminService {

	public Admin getById(int id);
	
	public Admin getByName(String name);
	
	public List<Admin> getAll();
	
	public List<Role> getRoles();
	
	public int createAdmin(Admin admin);
	
	public int editAdmin(Admin admin);
	
	public int deleteAdmin(int id);
	
}
