package com.grotek.service.imp;

import javax.transaction.Transactional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.grotek.pojo.Admin;
import com.grotek.pojo.Employee;
import com.grotek.service.AdminContext;
import com.grotek.web.authentication.AdminUserDetails;
import com.grotek.web.authentication.WorkUserDetails;
@Service("adminauthenticationService")
@org.springframework.transaction.annotation.Transactional
public class AdminSecurityUserContext implements AdminContext {

	@Override
	public Admin getCurrentUser() {
		// TODO Auto-generated method stub
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}

		return (Admin) authentication.getPrincipal();
	}

	@Override
	public void setCurrentUser(Admin user) {
		// TODO Auto-generated method stub
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}
		AdminUserDetails userDetails = new AdminUserDetails(user,0);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
