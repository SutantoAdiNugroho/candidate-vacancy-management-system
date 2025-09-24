package com.example.candidate_vacancy_management_system.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankCandidateResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer score;
}
