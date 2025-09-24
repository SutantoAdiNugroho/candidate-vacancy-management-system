package com.example.candidate_vacancy_management_system.dto.vacancy;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SalaryCriteriaRequest extends CreateCriteriaRequest {
    private Integer minSalary;
    private Integer maxSalary;
}
