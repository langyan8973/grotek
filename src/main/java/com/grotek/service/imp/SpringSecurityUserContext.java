package com.grotek.service.imp;

import javax.transaction.Transactional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.grotek.pojo.Employee;
import com.grotek.service.UserContext;
import com.grotek.web.authentication.WorkUserDetails;
@Service("workauthenticationService")
@org.springframework.transaction.annotation.Transactional
public class SpringSecurityUserContext implements UserContext {

	@Override
	public Employee getCurrentUser() {
		// TODO Auto-generated method stub
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}

		return (Employee) authentication.getPrincipal();
	}

	@Override
	public void setCurrentUser(Employee user) {
		// TODO Auto-generated method stub
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}
		WorkUserDetails userDetails = new WorkUserDetails(user,0);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
