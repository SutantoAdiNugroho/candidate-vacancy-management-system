package com.example.candidate_vacancy_management_system.dto;

import lombok.Data;

@Data
public class BaseErrorResponse {
    private Integer statusCode;
    private boolean success;
    private String message;
}