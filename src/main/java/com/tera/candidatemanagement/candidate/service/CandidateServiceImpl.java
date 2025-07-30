package com.tera.candidatemanagement.candidate.service;

import com.tera.candidatemanagement.candidate.dto.CandidateResponse;
import com.tera.candidatemanagement.candidate.repository.CandidateRepository;
import com.tera.candidatemanagement.candidate.dto.CandidateRequest;
import com.tera.candidatemanagement.candidate.exception.EmailAlreadyExistsException;
import com.tera.candidatemanagement.candidate.exception.CandidateNotFoundException;
import com.tera.candidatemanagement.candidate.model.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public List<CandidateResponse> getAll() {
        return candidateRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Candidate getById(String id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found"));
    }

    @Override
    public CandidateResponse create(CandidateRequest request) {
        if (candidateRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use");
        }

        Candidate candidate = new Candidate();
        candidate.setName(request.getName());
        candidate.setEmail(request.getEmail());
        candidate.setBirthDate(request.getBirthDate());
        candidate.setGender(request.getGender());
        candidate.setCurrentSalary(request.getCurrentSalary());
        Candidate saved = candidateRepository.save(candidate);

        return mapToResponse(saved);
    }

    @Override
    public CandidateResponse update(String id, CandidateRequest request) {
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
        Candidate updated = candidateRepository.save(candidate);

        return mapToResponse(updated);
    }

    @Override
    public void delete(String id) {
        if (!candidateRepository.existsById(id)) {
            throw new CandidateNotFoundException("Candidate not found");
        }
        candidateRepository.deleteById(id);
    }

    private CandidateResponse mapToResponse(Candidate candidate) {
        return new CandidateResponse(
                candidate.getId(),
                candidate.getName(),
                candidate.getEmail(),
                candidate.getBirthDate(),
                candidate.getGender(),
                candidate.getCurrentSalary()
        );
    }
}
