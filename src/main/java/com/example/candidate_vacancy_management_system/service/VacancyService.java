package com.example.candidate_vacancy_management_system.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.candidate_vacancy_management_system.constant.CriteriaType;
import com.example.candidate_vacancy_management_system.dto.vacancy.AgeCriteriaRequest;
import com.example.candidate_vacancy_management_system.dto.vacancy.CreateCriteriaRequest;
import com.example.candidate_vacancy_management_system.dto.vacancy.CreateVacancyRequest;
import com.example.candidate_vacancy_management_system.dto.vacancy.EducationLevelRequest;
import com.example.candidate_vacancy_management_system.dto.vacancy.GenderCriteriaRequest;
import com.example.candidate_vacancy_management_system.dto.vacancy.SalaryCriteriaRequest;
import com.example.candidate_vacancy_management_system.model.AgeCriteria;
import com.example.candidate_vacancy_management_system.model.Criteria;
import com.example.candidate_vacancy_management_system.model.EducationLevelCriteria;
import com.example.candidate_vacancy_management_system.model.GenderCriteria;
import com.example.candidate_vacancy_management_system.model.SalaryCriteria;
import com.example.candidate_vacancy_management_system.model.Vacancy;
import com.example.candidate_vacancy_management_system.repository.CandidateRepository;
import com.example.candidate_vacancy_management_system.repository.VacancyRepository;

@Service
public class VacancyService {

    private final VacancyRepository vacancyRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public VacancyService(VacancyRepository vacancyRepository, CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
        this.vacancyRepository = vacancyRepository;
    }

    public Vacancy create(CreateVacancyRequest request) {
        validateCriteria(request.getCriteria());

        Vacancy vacancy = new Vacancy();
        vacancy.setName(request.getName());
        vacancy.setDescription(request.getDescription());
        vacancy.setCriteria(buildCriteria(request.getCriteria()));

        return vacancyRepository.save(vacancy);
    }

    private void validateCriteria(List<CreateCriteriaRequest> criteriaRequest) {
        if (criteriaRequest == null || criteriaRequest.isEmpty()) {
            throw new IllegalArgumentException("vacancy must have at least one criteria");
        }
    }

    private List<Criteria> buildCriteria(List<CreateCriteriaRequest> cRequests) {
        return cRequests.stream().map(request -> {
            if (request instanceof AgeCriteriaRequest) {
                AgeCriteriaRequest aRequest = (AgeCriteriaRequest) request;
                this.validateAgeCriteriaRequest(aRequest);

                AgeCriteria ageCriteria = new AgeCriteria();

                ageCriteria.setType(CriteriaType.AGE.getType());
                ageCriteria.setMinAge(aRequest.getMinAge());
                ageCriteria.setMaxAge(aRequest.getMaxAge());
                ageCriteria.setWeight(aRequest.getWeight());

                return ageCriteria;
            } else if (request instanceof GenderCriteriaRequest) {
                GenderCriteriaRequest genderRequest = (GenderCriteriaRequest) request;
                this.validateGenderCriteriaRequest(genderRequest);

                GenderCriteria gCriteria = new GenderCriteria();

                String newGender = genderRequest.getGender().toLowerCase();
                if (newGender.equals("male")
                        || newGender.equals("female")) {
                    gCriteria.setGender(newGender);
                } else {
                    throw new IllegalArgumentException("gender must be 'male' or 'female'");
                }

                gCriteria.setType(CriteriaType.GENDER.getType());
                gCriteria.setWeight(genderRequest.getWeight());
                return gCriteria;
            } else if (request instanceof SalaryCriteriaRequest) {
                SalaryCriteriaRequest salaryRequest = (SalaryCriteriaRequest) request;
                this.validateSalaryCriteriaRequest(salaryRequest);

                SalaryCriteria sCriteria = new SalaryCriteria();

                sCriteria.setType(CriteriaType.SALARY.getType());
                sCriteria.setMinSalary(salaryRequest.getMinSalary());
                sCriteria.setMaxSalary(salaryRequest.getMaxSalary());
                sCriteria.setWeight(salaryRequest.getWeight());

                return sCriteria;
            } else if (request instanceof EducationLevelRequest) {
                EducationLevelRequest educationLevelRequest = (EducationLevelRequest) request;
                this.validateEducationLevelRequest(educationLevelRequest);

                EducationLevelCriteria eCriteria = new EducationLevelCriteria();

                eCriteria.setType(CriteriaType.EDUCATIONLEVEL.getType());
                eCriteria.setMinEducationLevel(educationLevelRequest.getMinEducationLevel());
                eCriteria.setWeight(educationLevelRequest.getWeight());

                return eCriteria;
            }

            throw new IllegalArgumentException("unknown criteria type");
        }).collect(Collectors.toList());
    }

    private void validateAgeCriteriaRequest(AgeCriteriaRequest request) {
        if (request.getMinAge() == null)
            throw new IllegalArgumentException("minAge on criteria age is required");
        if (request.getMaxAge() == null)
            throw new IllegalArgumentException("maxAge on criteria age is required");
        if (request.getWeight() == null)
            throw new IllegalArgumentException("weight on criteria age is required");
    }

    private void validateGenderCriteriaRequest(GenderCriteriaRequest request) {
        if (request.getGender() == null || request.getGender().trim().isEmpty())
            throw new IllegalArgumentException("gender on criteria gender is required");

        String gender = request.getGender().toLowerCase();
        if (!gender.equals("male") && !gender.equals("female"))
            throw new IllegalArgumentException("gender must be 'male' or 'female'");

        if (request.getWeight() == null)
            throw new IllegalArgumentException("weight on criteria gender is required");
    }

    private void validateSalaryCriteriaRequest(SalaryCriteriaRequest request) {
        if (request.getMinSalary() == null) {
            throw new IllegalArgumentException("minSalary on criteria salary is required");
        }
        if (request.getMaxSalary() == null) {
            throw new IllegalArgumentException("maxSalary on criteria salary is required");
        }
        if (request.getWeight() == null) {
            throw new IllegalArgumentException("weight on criteria salary is required");
        }
    }

    private void validateEducationLevelRequest(EducationLevelRequest request) {
        if (request.getMinEducationLevel() == null || request.getMinEducationLevel().trim().isEmpty()) {
            throw new IllegalArgumentException("minEducationLevel on criteria educationalLevel is required");
        }
        if (request.getWeight() == null) {
            throw new IllegalArgumentException("weight on criteria educationalLevel is required");
        }
    }
}
