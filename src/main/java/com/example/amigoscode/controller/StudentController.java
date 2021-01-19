package com.example.amigoscode.controller;

import com.example.amigoscode.dto.StudentDto;
import com.example.amigoscode.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST Estudantes")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping(value = "/students")
    @ApiOperation(value = "list all students")
    public List<StudentDto> getStudent() {
        return service.listStudentDto();
    }

    @PostMapping(value = "/students")
    @ApiOperation(value = "insert student")
    public ResponseEntity<StudentDto> save(@Valid @RequestBody StudentDto student) {
        return ResponseEntity.ok().body(service.saveStudent(student));
    }

    @DeleteMapping(value = "/students/{studentId}")
    @ApiOperation(value = "delete student")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        service.deleteStudent(id);
    }

    @PutMapping(value = "{studentId}")
    @ApiOperation(value = "update student")
        public void updateStudent(@PathVariable("studentId") Long id,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email) {
        service.updateStudent(id, name, email);
    }
}
