package com.example.candidate_vacancy_management_system.dto.vacancy;

import java.util.List;

import lombok.Data;

@Data
public class CreateVacancyRequest {
    private String name;
    private String description;
    private List<CreateCriteriaRequest> criteria;
}
