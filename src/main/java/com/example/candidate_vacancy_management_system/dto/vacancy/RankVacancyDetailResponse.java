package com.example.candidate_vacancy_management_system.dto.vacancy;

import java.util.List;

import com.example.candidate_vacancy_management_system.dto.PaginationInfo;
import com.example.candidate_vacancy_management_system.dto.candidate.RankCandidateResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankVacancyDetailResponse {
    private String vacancyName;
    private String vacancyDescription;
    private List<RankCandidateResponse> candidates;
    private PaginationInfo pagination;
}
