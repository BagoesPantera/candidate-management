package com.tera.candidatemanagement.vacancy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tera.candidatemanagement.vacancy.model.Criterion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacancyResponse {

    private String id;
    private String name;
    private List<Criterion> criteriaList;
}
