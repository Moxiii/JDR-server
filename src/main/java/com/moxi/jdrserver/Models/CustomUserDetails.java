package com.moxi.jdrserver.Models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
	private final String username;
	private final String password;


	private final User user;
public CustomUserDetails(String username, String password, User user) {
	this.username = username;
	this.password = password;
	this.user = user;
}

public User getUser() {
	return user;
}
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return List.of();
}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}


@Override
public boolean isAccountNonExpired() {
	return UserDetails.super.isAccountNonExpired();
}

@Override
public boolean isAccountNonLocked() {
	return UserDetails.super.isAccountNonLocked();
}

@Override
public boolean isCredentialsNonExpired() {
	return UserDetails.super.isCredentialsNonExpired();
}

@Override
public boolean isEnabled() {
	return UserDetails.super.isEnabled();
}
}
