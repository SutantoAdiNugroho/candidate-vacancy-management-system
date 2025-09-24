package com.example.candidate_vacancy_management_system.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.candidate_vacancy_management_system.dto.CreateCandidateRequest;
import com.example.candidate_vacancy_management_system.dto.SearchRequest;
import com.example.candidate_vacancy_management_system.dto.UpdateCandidateRequest;
import com.example.candidate_vacancy_management_system.model.Candidate;
import com.example.candidate_vacancy_management_system.model.EducationLevel;
import com.example.candidate_vacancy_management_system.repository.CandidateRepository;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate create(CreateCandidateRequest request) {
        Optional<Candidate> existingCandidate = candidateRepository.findByEmail(request.getEmail());
        if (existingCandidate.isPresent()) {
            throw new IllegalArgumentException("candidate with this email already exist");
        }

        if (!EducationLevel.isValidEducationLevel(request.getEducationLevel())) {
            throw new IllegalArgumentException("invalid education level input");
        }

        // birthdate validation
        Date birthdate;
        try {
            birthdate = new SimpleDateFormat("dd-MM-yyyy").parse(request.getBirthdate());
        } catch (Exception e) {
            throw new IllegalArgumentException("birthdate format must be dd-mm-yyyy");
        }

        Candidate candidate = new Candidate();
        candidate.setFirstName(request.getFirstName());
        candidate.setLastName(request.getLastName());
        candidate.setEmail(request.getEmail());
        candidate.setBirthdate(birthdate);
        candidate.setGender(request.getGender());
        candidate.setCurrentSalary(request.getCurrentSalary());
        candidate.setEducationLevel(request.getEducationLevel());
        candidate.setSchoolName(request.getSchoolName());

        return candidateRepository.save(candidate);
    }

    public Page<Candidate> getAll(SearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        String query = request.getSearch();

        if (query == null || query.trim().isEmpty()) {
            return candidateRepository.findAll(pageable);
        } else {
            return candidateRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query,
                    pageable);
        }
    }

    public Optional<Candidate> getById(String id) {
        return candidateRepository.findById(id);
    }

    public Candidate update(String id, UpdateCandidateRequest reqCandidate) {
        return candidateRepository.findById(id).map(candidate -> {
            if (!EducationLevel.isValidEducationLevel(reqCandidate.getEducationLevel())) {
                throw new IllegalArgumentException("invalid education level input");
            }

            if (reqCandidate.getBirthdate() != null) {
                try {
                    candidate.setBirthdate(new SimpleDateFormat("dd-MM-yyyy").parse(reqCandidate.getBirthdate()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException("birthdate format must be dd-mm-yyyy");
                }
            }

            if (reqCandidate.getGender() != null) {
                if (reqCandidate.getGender().equalsIgnoreCase("male")
                        && reqCandidate.getGender().equalsIgnoreCase("female"))
                    candidate.setGender(reqCandidate.getGender());

                throw new IllegalArgumentException("gender must be 'male' or 'female'");
            }

            candidate.setFirstName(reqCandidate.getFirstName());
            candidate.setLastName(reqCandidate.getLastName());
            candidate.setCurrentSalary(reqCandidate.getCurrentSalary());
            candidate.setEducationLevel(reqCandidate.getEducationLevel());
            candidate.setSchoolName(reqCandidate.getSchoolName());

            return candidateRepository.save(candidate);
        }).orElseThrow(() -> new IllegalArgumentException("candidate with that id not found"));
    }

    public void delete(String id) {
        Optional<Candidate> candidate = this.getById(id);
        if (!candidate.isPresent()) {
            throw new IllegalArgumentException("candidate with this id not exist");
        }

        candidateRepository.deleteById(id);
    }
}
