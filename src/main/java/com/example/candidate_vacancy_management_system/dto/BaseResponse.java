package com.example.candidate_vacancy_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private Integer statusCode;
    private boolean success;
    private T data;
}
