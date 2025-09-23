package com.example.candidate_vacancy_management_system.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "criteria")
public class Criteria {
    private String id;
    private String type;
    private Integer weight;
    private Object data;
}
