package com.tera.candidatemanagement.vacancy.validation;

import com.tera.candidatemanagement.vacancy.enums.CriterionType;
import com.tera.candidatemanagement.vacancy.model.Criterion;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriterionValidator implements ConstraintValidator<ValidCriterion, Criterion> {

    private final Map<CriterionType, CriterionValidationStrategy> strategyMap = new HashMap<>();

    public CriterionValidator(List<CriterionValidationStrategy> strategies) {
        for (CriterionValidationStrategy strategy : strategies) {
            strategyMap.put(strategy.getSupportedType(), strategy);
        }
    }

    @Override
    public boolean isValid(Criterion criterion, ConstraintValidatorContext context) {
        if (criterion == null || criterion.getType() == null) return true;

        context.disableDefaultConstraintViolation();

        CriterionValidationStrategy strategy = strategyMap.get(criterion.getType());
        if (strategy != null) {
            return strategy.isValid(criterion, context);
        }

        // fallback: unknown type = valid (or return false if preferred)
        return true;
    }
}