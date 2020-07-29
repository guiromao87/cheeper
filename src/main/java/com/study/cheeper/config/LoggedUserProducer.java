package com.study.cheeper.config;

import com.study.cheeper.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class LoggedUserProducer {

    @Bean
    public User getLoggedUser() {
        if(SecurityContextHolder.getContext().getAuthentication() != null
            && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        return new User();
    }
}