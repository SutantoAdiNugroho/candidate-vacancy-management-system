package com.example.candidate_vacancy_management_system.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.candidate_vacancy_management_system.model.Candidate;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

    Optional<Candidate> findByEmail(String email);

}
