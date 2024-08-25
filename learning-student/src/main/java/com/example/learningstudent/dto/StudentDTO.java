package com.example.learningstudent.dto;

import com.example.learningstudent.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private long id;

    private String name;

    private String email;

    private Gender gender;

    private int minAge;

    private int maxAge;


}