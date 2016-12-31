package com.grotek.web.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.grotek.pojo.Admin;

public class AdminUserDetails extends Admin implements UserDetails {
	
	private static final long serialVersionUID = -3405289086051043887L;
	private int count;

	public AdminUserDetails(Admin user) {
		setId(user.getId());
		setPassword(user.getPassword());
		setName(user.getName());
		setStatus(user.getStatus());
		setRole(user.getRole());
	}

	public AdminUserDetails(Admin user, int c) {
		setId(user.getId());
		setPassword(user.getPassword());
		setName(user.getName());
		setStatus(user.getStatus());
		setRole(user.getRole());
		count = c;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return AuthorityUtils.createAuthorityList(getRole().getCode());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getName();
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
