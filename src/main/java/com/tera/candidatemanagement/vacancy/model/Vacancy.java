package com.tera.candidatemanagement.vacancy.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "vacancies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotEmpty
    private List<@Valid Criterion> criteriaList;
}