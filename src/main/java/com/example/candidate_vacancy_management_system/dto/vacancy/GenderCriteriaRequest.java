package com.example.candidate_vacancy_management_system.dto.vacancy;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GenderCriteriaRequest extends CreateCriteriaRequest {
    private String gender;
}
