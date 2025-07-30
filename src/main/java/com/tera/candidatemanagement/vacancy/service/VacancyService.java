package com.tera.candidatemanagement.vacancy.service;

import com.tera.candidatemanagement.vacancy.dto.CandidateRankingResponse;
import com.tera.candidatemanagement.vacancy.dto.VacancyRequest;
import com.tera.candidatemanagement.vacancy.dto.VacancyResponse;
import com.tera.candidatemanagement.vacancy.model.Vacancy;

import java.util.List;

public interface VacancyService {
    VacancyResponse create(VacancyRequest request);
    List<VacancyResponse> getAll();

    Vacancy getById(String id);

    VacancyResponse update(String id, VacancyRequest request);
    void delete(String id);

    List<CandidateRankingResponse> rankCandidates(String vacancyId);
}