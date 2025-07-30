package com.tera.candidatemanagement.vacancy.evaluator;

import com.tera.candidatemanagement.candidate.model.Candidate;
import com.tera.candidatemanagement.vacancy.model.Criterion;

import java.time.LocalDate;
import java.time.Period;

public class AgeCriterionEvaluator implements CriterionEvaluator {

    @Override
    public int evaluate(Criterion criterion, Candidate candidate) {
        if (candidate.getBirthDate() == null) return 0;

        int age = calculateAge(candidate.getBirthDate());
        if ((criterion.getMinAge() == null || age >= criterion.getMinAge()) &&
                (criterion.getMaxAge() == null || age <= criterion.getMaxAge())) {
            return criterion.getWeight() != null ? criterion.getWeight() : 1;
        }
        return 0;
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}