package com.example.candidate_vacancy_management_system.dto.candidate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCandidateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String birthdate;
    private String gender;
    private Integer currentSalary;
    private String educationLevel;
    private String schoolName;
}
