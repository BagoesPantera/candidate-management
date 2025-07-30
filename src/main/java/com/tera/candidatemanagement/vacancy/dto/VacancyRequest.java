package com.tera.candidatemanagement.vacancy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tera.candidatemanagement.vacancy.model.Criterion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class VacancyRequest {

    @NotBlank
    private String name;

    @NotEmpty
    @Valid
    @JsonProperty("criteria")
    private List<@Valid Criterion> criteriaList;
}