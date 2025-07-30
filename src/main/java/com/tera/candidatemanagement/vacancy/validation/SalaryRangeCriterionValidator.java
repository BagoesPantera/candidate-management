package com.tera.candidatemanagement.vacancy.validation;

import com.tera.candidatemanagement.vacancy.enums.CriterionType;
import com.tera.candidatemanagement.vacancy.model.Criterion;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class SalaryRangeCriterionValidator implements CriterionValidationStrategy{

    @Override
    public boolean isValid(Criterion c, ConstraintValidatorContext context) {
        if (c.getMinSalary() == null || c.getMaxSalary() == null) {
            context.buildConstraintViolationWithTemplate("minSalary and maxSalary are required for SALARY_RANGE")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    @Override
    public CriterionType getSupportedType() {
        return CriterionType.SALARY_RANGE;
    }
}
