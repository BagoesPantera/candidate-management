package com.tera.candidatemanagement.vacancy.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tera.candidatemanagement.vacancy.enums.CriterionType;
import com.tera.candidatemanagement.vacancy.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criterion {

    @NotNull
    private CriterionType type;

    private Integer minAge;
    private Integer maxAge;

    private Gender gender;

    private Long minSalary;
    private Long maxSalary;

    private Integer weight = 1;
}