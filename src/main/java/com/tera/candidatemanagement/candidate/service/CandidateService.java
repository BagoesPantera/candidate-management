package com.tera.candidatemanagement.candidate.service;

import com.tera.candidatemanagement.candidate.dto.CandidateRequest;
import com.tera.candidatemanagement.candidate.dto.CandidateResponse;
import com.tera.candidatemanagement.candidate.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {
    List<CandidateResponse> getAll();
    Candidate getById(String id);
    CandidateResponse create(CandidateRequest request);
    CandidateResponse update(String id, CandidateRequest request);
    void delete(String id);
}
