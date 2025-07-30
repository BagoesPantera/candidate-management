package com.tera.candidatemanagement.candidate.service;

import com.tera.candidatemanagement.candidate.repository.CandidateRepository;
import com.tera.candidatemanagement.candidate.dto.CandidateRequest;
import com.tera.candidatemanagement.candidate.exception.EmailAlreadyExistsException;
import com.tera.candidatemanagement.candidate.exception.CandidateNotFoundException;
import com.tera.candidatemanagement.candidate.model.Candidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getById(String id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found"));
    }

    @Override
    public Candidate create(CandidateRequest request) {
        if (candidateRepository.findByEmail(request.getEmail()).isPresent()) {
            log.error("Email {} already in use", request.getEmail());
            throw new EmailAlreadyExistsException("Email already in use");
        }

        Candidate candidate = new Candidate();
        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());
        candidate.setBirthDate(request.getBirthDate());
        candidate.setGender(request.getGender());
        candidate.setCurrentSalary(request.getCurrentSalary());

        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate update(String id, CandidateRequest request) {
        Candidate candidate = getById(id);

        Optional<Candidate> existing = candidateRepository.findByEmail(request.getEmail());
        if (existing.isPresent() && !existing.get().getId().equals(id)) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());
        candidate.setBirthDate(request.getBirthDate());
        candidate.setGender(request.getGender());
        candidate.setCurrentSalary(request.getCurrentSalary());

        return candidateRepository.save(candidate);
    }

    @Override
    public void delete(String id) {
        Candidate candidate = getById(id);
        candidateRepository.delete(candidate);
    }
}
