package com.example.study.global.validation.validator;

import com.example.study.global.validation.annotation.PageValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageValidator implements ConstraintValidator<PageValidation, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Page must be greater than or equal to 1.")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
