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
import com.example.candidate_vacancy_management_system.dto.CreateCandidateRequest;
import com.example.candidate_vacancy_management_system.dto.PaginationInfo;
import com.example.candidate_vacancy_management_system.dto.PaginationResponse;
import com.example.candidate_vacancy_management_system.dto.SearchRequest;
import com.example.candidate_vacancy_management_system.dto.UpdateCandidateRequest;
import com.example.candidate_vacancy_management_system.model.Candidate;
import com.example.candidate_vacancy_management_system.service.CandidateService;
import com.example.candidate_vacancy_management_system.exception.BadRequestException;
import com.example.candidate_vacancy_management_system.exception.NotFoundException;

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

    @GetMapping
    public ResponseEntity<BaseResponse<PaginationResponse<Candidate>>> findCandidates(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        SearchRequest request = new SearchRequest(search, page, size);
        Page<Candidate> candidates = candidateService.getAll(request);

        PaginationResponse<Candidate> response = new PaginationResponse<>(
                candidates.getContent(),
                PaginationInfo.createPagination(candidates));

        return new ResponseEntity<>(
                new BaseResponse<PaginationResponse<Candidate>>(HttpStatus.OK.value(), true, response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Candidate>> getById(@PathVariable String id) {
        Optional<Candidate> candidate = candidateService.getById(id);
        if (candidate.isPresent()) {
            BaseResponse<Candidate> response = new BaseResponse<>(HttpStatus.OK.value(), true, candidate.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new NotFoundException("candidate with that id not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Candidate>> update(@PathVariable String id,
            @RequestBody UpdateCandidateRequest request) {
        try {
            Candidate candidate = candidateService.update(id, request);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), true, candidate), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> delete(@PathVariable String id) {
        try {
            candidateService.delete(id);
            return new ResponseEntity<>(new BaseResponse<>(HttpStatus.OK.value(), true, null), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(e.getMessage());
        }
    }
}
