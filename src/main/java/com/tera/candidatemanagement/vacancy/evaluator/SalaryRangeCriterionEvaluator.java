package com.tera.candidatemanagement.vacancy.evaluator;

import com.tera.candidatemanagement.candidate.model.Candidate;
import com.tera.candidatemanagement.vacancy.model.Criterion;

public class SalaryRangeCriterionEvaluator implements  CriterionEvaluator {

    @Override
    public int evaluate(Criterion criterion, Candidate candidate) {
        int salary = candidate.getCurrentSalary();
        if ((criterion.getMinSalary() == null || salary >= criterion.getMinSalary()) && (criterion.getMaxSalary() == null || salary <= criterion.getMaxSalary())) {
            return criterion.getWeight() != null ? criterion.getWeight() : 1;
        }
        return 0;
    }
}
