package com.study.cheeper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.study.cheeper.login.LoggedUser;
import com.study.cheeper.user.User;

@Component
public class LoggedUserProducer {
    
	@Autowired
	private LoggedUser user;

    @Bean
    @Scope("prototype")
    public User getLoggedUser() {
    	return user.asUser();
    }
    
}