package com.tera.candidatemanagement.vacancy.validation;

import com.tera.candidatemanagement.vacancy.enums.CriterionType;
import com.tera.candidatemanagement.vacancy.model.Criterion;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class GenderCriterionValidator implements CriterionValidationStrategy{

    @Override
    public boolean isValid(Criterion c, ConstraintValidatorContext context) {
        if (c.getGender() == null) {
            context.buildConstraintViolationWithTemplate("gender is required for GENDER")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public CriterionType getSupportedType() {
        return CriterionType.GENDER;
    }
}
