package com.example.learningstudent.service;

import com.example.learningstudent.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO getStudentById(long id);

    Long addNewStudent(StudentDTO studentDTO);

    void deleteStudentById(long id);

    StudentDTO updateStudentById(long id, String name, String email);

    List<StudentDTO> getStudentsByAges(int maxAge, int minAge);
}
