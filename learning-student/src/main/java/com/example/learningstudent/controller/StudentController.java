package com.example.learningstudent.controller;

import com.example.learningstudent.Response;
import com.example.learningstudent.dto.StudentDTO;
import com.example.learningstudent.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{id}")
    public Response<StudentDTO> getStudentById(@PathVariable long id) {
        return Response.success(studentService.getStudentById(id));
    }

    @GetMapping("/student")
    public Response<List<StudentDTO>> getStudentByAge(@RequestParam int maxAge, @RequestParam int minAge) {
        return Response.success(studentService.getStudentsByAges(maxAge, minAge));
    }


    @PostMapping("/student")
    public Response<Long> addNewStudent(@RequestBody StudentDTO studentDTO) {
        return Response.success(studentService.addNewStudent(studentDTO));
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("/student/{id}")
    public Response<StudentDTO> updateStudentById(@PathVariable long id, @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String email) {

        return Response.success(studentService.updateStudentById(id, name, email));
    }
}