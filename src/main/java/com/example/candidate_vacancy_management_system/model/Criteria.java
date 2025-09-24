package com.example.candidate_vacancy_management_system.model;

import lombok.Data;

@Data
public abstract class Criteria {
    private int weight;
    private String type;
}
