package com.example.learningstudent.dao;


import com.example.learningstudent.converter.GenderConverter;
import com.example.learningstudent.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }


}
