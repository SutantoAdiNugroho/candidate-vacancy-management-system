package com.example.candidate_vacancy_management_system.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "vacancies")
public class Vacancy {
    private String id;
    private String name;
    private String description;
    private List<Criteria> criteria;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }
}
