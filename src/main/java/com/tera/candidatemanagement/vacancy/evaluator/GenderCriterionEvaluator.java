package com.tera.candidatemanagement.vacancy.evaluator;

import com.tera.candidatemanagement.candidate.model.Candidate;
import com.tera.candidatemanagement.vacancy.model.Criterion;

public class GenderCriterionEvaluator implements CriterionEvaluator {

    @Override
    public int evaluate(Criterion criterion, Candidate candidate) {
        if (genderMatches(criterion.getGender(), candidate.getGender())) {
            return criterion.getWeight() != null ? criterion.getWeight() : 1;
        }
        return 0;
    }

    private boolean genderMatches(com.tera.candidatemanagement.vacancy.enums.Gender required,
                                  com.tera.candidatemanagement.candidate.enums.Gender actual) {
        return required == null || required == com.tera.candidatemanagement.vacancy.enums.Gender.ANY
                || required.name().equals(actual.name());
    }
}
