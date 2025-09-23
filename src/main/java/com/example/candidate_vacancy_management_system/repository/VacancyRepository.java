package com.example.candidate_vacancy_management_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.candidate_vacancy_management_system.model.Vacancy;

@Repository
public interface VacancyRepository extends MongoRepository<Vacancy, String> {
}
