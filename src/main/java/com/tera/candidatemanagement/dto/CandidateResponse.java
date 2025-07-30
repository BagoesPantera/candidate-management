package com.tera.candidatemanagement.dto;

import com.tera.candidatemanagement.candidate.Candidate;
import com.tera.candidatemanagement.model.Gender;
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