package com.study.cheeper.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProfileNameUniqueValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ProfileNameUnique {

    public String message() default "";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};
}
