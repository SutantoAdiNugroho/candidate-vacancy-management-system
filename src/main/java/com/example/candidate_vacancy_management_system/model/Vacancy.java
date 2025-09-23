package com.example.candidate_vacancy_management_system.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vacancies")
public class Vacancy {
    private String id;
    private String name;
    private String description;
    private List<Criteria> criteria;
}
