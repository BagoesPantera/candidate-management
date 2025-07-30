package com.tera.candidatemanagement.vacancy.validation;

import com.tera.candidatemanagement.vacancy.enums.CriterionType;
import com.tera.candidatemanagement.vacancy.model.Criterion;
import jakarta.validation.ConstraintValidatorContext;

public interface CriterionValidationStrategy {
    boolean isValid(Criterion criterion, ConstraintValidatorContext context);
    CriterionType getSupportedType();
}
