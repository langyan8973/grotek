package com.grotek.web.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.grotek.pojo.Employee;
import com.grotek.service.EmployeeService;
import com.grotek.service.WeekliesService;

@Component
public class WorkDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeService emService;
	@Autowired
	private WeekliesService weekService;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Employee user = emService.getByMobile(arg0);
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		WorkUserDetails workUserDetails = new WorkUserDetails(user);
		return workUserDetails;
	}

}
