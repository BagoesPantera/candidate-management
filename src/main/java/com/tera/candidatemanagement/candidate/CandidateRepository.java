package com.tera.candidatemanagement.candidate;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CandidateRepository extends MongoRepository<Candidate,String> {
    Optional<Candidate> findByEmail(String email);
    boolean existsBy(String email);
}
