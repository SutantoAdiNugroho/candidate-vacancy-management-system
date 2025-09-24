package com.example.candidate_vacancy_management_system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.candidate_vacancy_management_system.dto.BaseResponse;
import com.example.candidate_vacancy_management_system.dto.PaginationInfo;
import com.example.candidate_vacancy_management_system.dto.PaginationResponse;
import com.example.candidate_vacancy_management_system.dto.candidate.SearchRequest;
import com.example.candidate_vacancy_management_system.dto.candidate.UpdateCandidateRequest;
import com.example.candidate_vacancy_management_system.dto.vacancy.CreateVacancyRequest;
import com.example.candidate_vacancy_management_system.dto.vacancy.UpdateVacancyRequest;
import com.example.candidate_vacancy_management_system.exception.BadRequestException;
import com.example.candidate_vacancy_management_system.exception.NotFoundException;
import com.example.candidate_vacancy_management_system.model.Candidate;
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

    @GetMapping
    public ResponseEntity<BaseResponse<PaginationResponse<Vacancy>>> findVacancies(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        SearchRequest request = new SearchRequest(search, page, size);
        Page<Vacancy> vacancies = vacancyService.getAll(request);

        PaginationResponse<Vacancy> response = new PaginationResponse<>(
                vacancies.getContent(),
                PaginationInfo.createPagination(vacancies));

        return new ResponseEntity<>(
                new BaseResponse<PaginationResponse<Vacancy>>(HttpStatus.OK.value(), true, response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Vacancy>> getById(@PathVariable String id) {
        Optional<Vacancy> vacancy = vacancyService.getById(id);
        if (vacancy.isPresent()) {
            BaseResponse<Vacancy> response = new BaseResponse<>(HttpStatus.OK.value(), true, vacancy.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new NotFoundException("vacancy with that id not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable String id) {
        try {
            vacancyService.delete(id);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), true, null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Vacancy>> update(@PathVariable String id,
            @RequestBody UpdateVacancyRequest request) {
        try {
            Vacancy vacancy = vacancyService.update(id, request);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), true, vacancy), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
