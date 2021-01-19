package com.example.amigoscode.service;

import com.example.amigoscode.dto.StudentDto;

import java.util.List;

public interface StudentService {

    public StudentDto saveStudent(StudentDto student);
    public void deleteStudent(Long id);
    public void updateStudent(Long id, String name, String email);
    public List<StudentDto> listStudentDto();

}
