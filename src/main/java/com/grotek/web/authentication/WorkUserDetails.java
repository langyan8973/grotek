package com.grotek.web.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import com.grotek.pojo.Employee;

public final class WorkUserDetails extends Employee implements UserDetails {
	
	private static final long serialVersionUID = -3405289086051043887L;
	private int count;

	public WorkUserDetails(Employee user) {
		setId(user.getId());
		setMobile(user.getMobile());
		setPassword(user.getPassword());
		setFullname(user.getFullname());
		setStatus(user.getStatus());
	}

	public WorkUserDetails(Employee user, int c) {
		setId(user.getId());
		setMobile(user.getMobile());
		setPassword(user.getPassword());
		setFullname(user.getFullname());
		setStatus(user.getStatus());
		count = c;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return AuthorityUtils.createAuthorityList("WORK");
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getFullname();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
