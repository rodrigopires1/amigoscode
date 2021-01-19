package com.example.amigoscode.controller;

import com.example.amigoscode.domain.Student;
import com.example.amigoscode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping(value = "/students")
    public List<Student> getStudent() {
        return service.getStudentList();
    }

    @PostMapping(value = "/students")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return ResponseEntity.ok().body(service.saveStudent(student));
    }

    @DeleteMapping(value = "/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        service.deleteStudent(id);
    }

    @PutMapping(value = "{studentId}")
        public void updateStudent(@PathVariable("studentId") Long id,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email) {
        service.updateStudent(id, name, email);
    }
}
