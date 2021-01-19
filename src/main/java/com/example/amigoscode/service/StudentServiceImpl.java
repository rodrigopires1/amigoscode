package com.example.amigoscode.service;

import com.example.amigoscode.domain.Student;
import com.example.amigoscode.dto.StudentDto;
import com.example.amigoscode.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<StudentDto> listStudentDto() {
        List<Student> student = studentRepository.findAll();
        return modelMapper.map(student, new TypeToken<List<StudentDto>>() {
        }.getType());
    }

    public StudentDto saveStudent(StudentDto student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        Student student1 = dtoToEntity(student);
        Student student2 = studentRepository.save(student1);
        return entityToDto(student2);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("student with id " + id + " does not exists");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "student with id " + id + " does not exists"));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

    public StudentDto entityToDto (Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    public Student dtoToEntity (StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }

}
