package com.grotek.service;

import com.grotek.pojo.Employee;

public interface UserContext {

	public Employee getCurrentUser();

	public void setCurrentUser(Employee user);
	
}
