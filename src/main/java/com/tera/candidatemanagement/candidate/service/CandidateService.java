package com.tera.candidatemanagement.candidate.service;

import com.tera.candidatemanagement.candidate.dto.CandidateRequest;
import com.tera.candidatemanagement.candidate.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {
    List<Candidate> getAll();
    Candidate getById(String id);
    Candidate create(CandidateRequest request);
    Candidate update(String id, CandidateRequest request);
    void delete(String id);
}
