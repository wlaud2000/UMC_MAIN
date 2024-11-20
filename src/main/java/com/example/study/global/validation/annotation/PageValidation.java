package com.example.study.global.validation.annotation;

import com.example.study.global.validation.validator.PageValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PageValidation {
    String message() default "Page must be greater than or equal to 1.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
