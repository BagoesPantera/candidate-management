package com.tera.candidatemanagement.vacancy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VacancyNotFoundException extends RuntimeException {
    public VacancyNotFoundException(String id) {
        super("Vacancy with ID " + id + " not found.");
    }
}
