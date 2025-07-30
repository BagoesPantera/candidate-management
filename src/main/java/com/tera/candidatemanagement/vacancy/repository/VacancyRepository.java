package com.tera.candidatemanagement.vacancy.repository;

import com.tera.candidatemanagement.vacancy.model.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VacancyRepository extends MongoRepository<Vacancy, String> {
}