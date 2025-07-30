package com.tera.candidatemanagement.candidate.dto;

import com.tera.candidatemanagement.candidate.model.Candidate;
import com.tera.candidatemanagement.candidate.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateResponse {
    private String id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Gender gender;
    private Integer currentSalary;

    public static CandidateResponse fromEntity(Candidate candidate) {
        return CandidateResponse.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .birthDate(candidate.getBirthDate())
                .gender(candidate.getGender())
                .currentSalary(candidate.getCurrentSalary())
                .build();
    }
}