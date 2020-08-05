package com.study.cheeper.login;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class SimpleAuthService implements UserDetailsService{
	
	@Autowired
	private EntityManager em;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return em.createQuery("select new " 
								+ UserSummary.class.getName() 
								+ "(u.id, u.profileName, u.email, u.password) "
								+ "from User u "
								+ "where u.email = :email", UserSummary.class)
				.setParameter("email", username)
				.getSingleResult();
	}

}
