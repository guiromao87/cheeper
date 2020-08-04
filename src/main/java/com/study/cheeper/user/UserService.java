package com.study.cheeper.user;

import com.study.cheeper.user.NewUserForm;
import com.study.cheeper.user.User;
import com.study.cheeper.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public void createNewUser(NewUserForm newUserForm) {
        User newUser = newUserForm.toUser(passwordEncoder);
        this.userRepository.save(newUser);
    }

    public boolean isProfileNameInUse(String profileName) {
        return userRepository.existsByProfileName(profileName);
    }
}
