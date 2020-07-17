package com.study.cheeper.validator;

import com.study.cheeper.model.form.NewUserForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ConfirmPasswordValidator implements Validator {

    @Override
    public boolean supports(Class aClass) {
        return NewUserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewUserForm newUserForm = (NewUserForm) o;

        if(!newUserForm.getPassword().equals(newUserForm.getConfirmPassword()))
            errors.rejectValue("confirmPassword", "field.confirmPassword.notEqualPassword", "Senha incorreta");
    }
}
