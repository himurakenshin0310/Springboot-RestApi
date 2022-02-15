package com.springboot.securityconfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.entity.User;
import com.springboot.entity.UserRoles;
public class UserCustomDeitail implements UserDetails {
	User user;

	public UserCustomDeitail(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> author = new ArrayList<SimpleGrantedAuthority>();
		for (UserRoles u : user.getUserRole()) {
			author.add(new SimpleGrantedAuthority(u.getRoleId().getRoleName()));
		}
		return author;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
