package com.example.candidate_vacancy_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.candidate_vacancy_management_system.dto.BaseResponse;
import com.example.candidate_vacancy_management_system.dto.vacancy.CreateVacancyRequest;
import com.example.candidate_vacancy_management_system.exception.BadRequestException;
import com.example.candidate_vacancy_management_system.model.Vacancy;
import com.example.candidate_vacancy_management_system.service.VacancyService;

@RestController
@RequestMapping("/api/vacancies")
public class VacancyController {

    private final VacancyService vacancyService;

    @Autowired
    public VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Vacancy>> create(@RequestBody CreateVacancyRequest request) {
        try {
            Vacancy vacancy = vacancyService.create(request);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.CREATED.value(), true, vacancy),
                    HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
