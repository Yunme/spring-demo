package com.example.learningstudent.converter;

import com.example.learningstudent.dao.Student;
import com.example.learningstudent.dto.StudentDTO;

public class StudentConverter {

    public static StudentDTO convertStudent(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setName(student.getName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setGender(student.getGender());
        return studentDTO;
    }

    public static Student convertStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setGender(studentDTO.getGender());
        return student;
    }
}