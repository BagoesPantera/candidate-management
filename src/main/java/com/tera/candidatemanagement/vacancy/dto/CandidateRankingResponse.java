package com.tera.candidatemanagement.vacancy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CandidateRankingResponse {
    private String id;
    private String name;
    private String email;
    private int score;
}
