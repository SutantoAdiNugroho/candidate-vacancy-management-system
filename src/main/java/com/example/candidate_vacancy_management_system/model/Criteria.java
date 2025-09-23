package com.example.candidate_vacancy_management_system.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "criteria")
public class Criteria {
    private String id;
    private String type;
    private Integer weight;
    private Object data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
