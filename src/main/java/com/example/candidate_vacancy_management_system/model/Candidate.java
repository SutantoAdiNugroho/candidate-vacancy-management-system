package com.example.candidate_vacancy_management_system.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "candidates")
public class Candidate {
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    private Date birthdate;
    private String gender;
    private Integer currentSalary;
    private String educationLevel;
    private String schoolName;
}
