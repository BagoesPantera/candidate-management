package com.tera.candidatemanagement.candidate.repository;

import com.tera.candidatemanagement.candidate.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CandidateRepository extends MongoRepository<Candidate,String> {
    Optional<Candidate> findByEmail(String email);
}
