package com.example.amigoscode.service;

import com.example.amigoscode.domain.Student;

import java.util.List;

public interface StudentService {

    public Student saveStudent(Student student);
    public void deleteStudent(Long id);
    public void updateStudent(Long id, String name, String email);
    public List<Student> getStudentList();

}
