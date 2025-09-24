package com.example.candidate_vacancy_management_system.constant;

import java.util.stream.Stream;

public enum CriteriaType {
    GENDER("gender"),
    SALARY("salary"),
    AGE("age"),
    EDUCATIONLEVEL("educationLevel");

    private final String type;

    private CriteriaType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static CriteriaType fromString(String type) {
        return Stream.of(CriteriaType.values())
                .filter(criteria -> criteria.type.equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown criteria type, please check again"));
    }
}