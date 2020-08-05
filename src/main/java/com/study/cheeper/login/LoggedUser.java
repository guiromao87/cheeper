package com.study.cheeper.login;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.study.cheeper.user.User;
import com.study.cheeper.user.UserRepository;

@Component
public class LoggedUser {

	private static final Supplier<? extends UserNotLoggedException> NOT_LOGGED = () -> new UserNotLoggedException();

	@Autowired
	private UserRepository users;

	private Optional<UserSummary> getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null && auth.getPrincipal() instanceof UserDetails) {
			return Optional.of((UserSummary)auth.getPrincipal());
		}else {
			return Optional.empty();
		}

	}

	public boolean isPresent() {
		return getCurrentUser().isPresent();
	}

	public String getProfileName() {
		return getCurrentUser().map(u -> u.getProfileName()).orElseThrow(NOT_LOGGED);
	}

	public User asUser() {
		return getCurrentUser().flatMap(u -> users.findById(u.getId())).orElseThrow(NOT_LOGGED);
	}

}
