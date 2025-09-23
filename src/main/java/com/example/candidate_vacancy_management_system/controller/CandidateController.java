package com.example.candidate_vacancy_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.candidate_vacancy_management_system.dto.BaseResponse;
import com.example.candidate_vacancy_management_system.dto.CreateCandidateRequest;
import com.example.candidate_vacancy_management_system.model.Candidate;
import com.example.candidate_vacancy_management_system.service.CandidateService;
import com.example.candidate_vacancy_management_system.exception.BadRequestException;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Candidate>> createCandidate(@RequestBody CreateCandidateRequest request) {
        try {
            Candidate newCandidate = candidateService.create(request);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.CREATED.value(), true, newCandidate),
                    HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
