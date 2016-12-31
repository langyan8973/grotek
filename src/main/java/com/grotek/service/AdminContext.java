package com.grotek.service;

import com.grotek.pojo.Admin;
import com.grotek.pojo.Employee;

public interface AdminContext {

	public Admin getCurrentUser();

	public void setCurrentUser(Admin user);
	
}
