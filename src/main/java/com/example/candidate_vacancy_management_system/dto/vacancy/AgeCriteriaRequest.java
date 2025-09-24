package com.example.candidate_vacancy_management_system.dto.vacancy;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AgeCriteriaRequest extends CreateCriteriaRequest {
    private Integer minAge;
    private Integer maxAge;
}
