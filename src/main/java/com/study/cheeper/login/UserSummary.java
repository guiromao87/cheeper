package com.study.cheeper.login;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSummary implements UserDetails {
	
	private Long id;
	private String profileName;
	private String username;
	private String password;
	
	
	public UserSummary(Long id, String profileName, String username, String password) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getProfileName() {
		return profileName;
	}
	
	public String getEmail() {
		return username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
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
