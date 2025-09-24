package com.example.candidate_vacancy_management_system.dto.vacancy;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AgeCriteriaRequest.class, name = "age"),
        @JsonSubTypes.Type(value = GenderCriteriaRequest.class, name = "gender"),
        @JsonSubTypes.Type(value = EducationLevelRequest.class, name = "educationLevel"),
        @JsonSubTypes.Type(value = SalaryCriteriaRequest.class, name = "salary")
})
public abstract class CreateCriteriaRequest {
    private Integer weight = 1;
}
