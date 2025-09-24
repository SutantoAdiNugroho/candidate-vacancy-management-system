package com.example.candidate_vacancy_management_system.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.candidate_vacancy_management_system.constant.EducationLevel;
import com.example.candidate_vacancy_management_system.dto.candidate.CreateCandidateRequest;
import com.example.candidate_vacancy_management_system.dto.candidate.SearchRequest;
import com.example.candidate_vacancy_management_system.dto.candidate.UpdateCandidateRequest;
import com.example.candidate_vacancy_management_system.model.Candidate;
import com.example.candidate_vacancy_management_system.repository.CandidateRepository;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate create(CreateCandidateRequest request) {
        this.validateInput(request);

        // check duplicate email
        Optional<Candidate> existingCandidate = candidateRepository.findByEmail(request.getEmail());
        if (existingCandidate.isPresent()) {
            throw new IllegalArgumentException("candidate with this email already exist");
        }

        Candidate candidate = new Candidate();

        if (request.getGender().equalsIgnoreCase("male")
                || request.getGender().equalsIgnoreCase("female")) {
            candidate.setGender(request.getGender().toLowerCase());
        } else {
            throw new IllegalArgumentException("gender must be 'male' or 'female'");
        }

        try {
            candidate.setBirthdate(new SimpleDateFormat("dd-MM-yyyy").parse(request.getBirthdate()));
        } catch (Exception e) {
            throw new IllegalArgumentException("birthdate format must be dd-MM-yyyy");
        }

        candidate.setFirstName(request.getFirstName().trim());
        candidate.setLastName(request.getLastName().trim());
        candidate.setEmail(request.getEmail().trim().toLowerCase());
        candidate.setCurrentSalary(request.getCurrentSalary());
        candidate.setEducationLevel(request.getEducationLevel());
        candidate.setSchoolName(request.getSchoolName().trim());

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
        this.validateInput(reqCandidate);

        return candidateRepository.findById(id).map(candidate -> {
            if (reqCandidate.getBirthdate() != null) {
                try {
                    candidate.setBirthdate(new SimpleDateFormat("dd-MM-yyyy").parse(reqCandidate.getBirthdate()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException("birthdate format must be dd-mm-yyyy");
                }
            }

            if (reqCandidate.getGender().equalsIgnoreCase("male")
                    || reqCandidate.getGender().equalsIgnoreCase("female")) {
                candidate.setGender(reqCandidate.getGender().toLowerCase());
            } else {
                throw new IllegalArgumentException("gender must be 'male' or 'female'");
            }

            candidate.setFirstName(reqCandidate.getFirstName().trim());
            candidate.setLastName(reqCandidate.getLastName().trim());
            candidate.setEmail(reqCandidate.getEmail().trim().toLowerCase());
            candidate.setCurrentSalary(reqCandidate.getCurrentSalary());
            candidate.setEducationLevel(reqCandidate.getEducationLevel());
            candidate.setSchoolName(reqCandidate.getSchoolName().trim());

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

    private void validateInput(CreateCandidateRequest request) {
        if (request.getFirstName() == null || request.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("firstName is required");
        }
        if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("lastName is required");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("email is required");
        }
        if (request.getBirthdate() == null || request.getBirthdate().trim().isEmpty()) {
            throw new IllegalArgumentException("birthdate is required");
        }
        if (request.getEducationLevel() == null || request.getEducationLevel().trim().isEmpty()) {
            throw new IllegalArgumentException("educationLevel is required");
        }
        if (request.getSchoolName() == null || request.getSchoolName().trim().isEmpty()) {
            throw new IllegalArgumentException("schoolName is required");
        }
        if (request.getCurrentSalary() == null) {
            throw new IllegalArgumentException("currentSalary is required");
        }
        if (request.getGender() == null || request.getGender().trim().isEmpty()) {
            throw new IllegalArgumentException("gender is required");
        }

        // education validation
        if (!EducationLevel.isValidEducationLevel(request.getEducationLevel())) {
            throw new IllegalArgumentException("invalid education level input");
        }

    }
}
