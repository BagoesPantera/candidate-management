package com.tera.candidatemanagement.vacancy.evaluator;

import com.tera.candidatemanagement.candidate.model.Candidate;
import com.tera.candidatemanagement.vacancy.model.Criterion;

public interface CriterionEvaluator {
    int evaluate(Criterion criterion, Candidate candidate);
}
