package com.study.cheeper.validator;

import com.study.cheeper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProfileNameUniqueValidator implements ConstraintValidator<ProfileNameUnique, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String profileName, ConstraintValidatorContext constraintValidatorContext) {
        return !userService.isProfileNameInUse(profileName);
    }
}
