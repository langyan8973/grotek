package com.grotek.web.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.grotek.pojo.Admin;
import com.grotek.service.AdminService;

@Component
public class AdminDetailsService implements UserDetailsService {

	@Autowired
	private AdminService adminService;
	
	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Admin user = adminService.getByName(arg0);
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		AdminUserDetails adminUserDetails = new AdminUserDetails(user);
		return adminUserDetails;
	}

}
