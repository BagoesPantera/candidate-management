package com.tera.candidatemanagement.vacancy.validation;

import com.tera.candidatemanagement.vacancy.enums.CriterionType;
import com.tera.candidatemanagement.vacancy.model.Criterion;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class AgeCriterionValidator implements CriterionValidationStrategy {

    @Override
    public boolean isValid(Criterion c, ConstraintValidatorContext context) {
        if (c.getMinAge() == null || c.getMaxAge() == null) {
            context.buildConstraintViolationWithTemplate("minAge and maxAge required for AGE")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public CriterionType getSupportedType() {
        return CriterionType.AGE;
    }
}

