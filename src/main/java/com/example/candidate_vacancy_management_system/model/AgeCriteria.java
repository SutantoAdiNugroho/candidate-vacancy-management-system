package com.example.candidate_vacancy_management_system.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AgeCriteria extends Criteria {
    private int minAge;
    private int maxAge;
}
