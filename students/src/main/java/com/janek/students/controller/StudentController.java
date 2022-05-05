package com.janek.students.controller;

import com.janek.students.enumerations.Endpoints;
import com.janek.students.model.Student;
import com.janek.students.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = Endpoints.STUDENTS)
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return studentRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping
    public ResponseEntity<Student> putStudent(@RequestBody @Valid Student student) {
        return studentRepository.findById(student.getId()).map(s ->
                ResponseEntity.ok(studentRepository.save(student))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchData(@RequestBody Student student, @PathVariable Long id) {
        return studentRepository.findById(id).map(s -> {
            if(!StringUtils.isEmpty(student.getEmail()))
                s.setEmail(student.getEmail());
            if(!StringUtils.isEmpty(student.getFirstName()))
                s.setFirstName(student.getFirstName());
            if(!StringUtils.isEmpty(student.getLastName()))
                s.setLastName(student.getLastName());
            return ResponseEntity.ok(studentRepository.save(s));
        }).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> removeStudents(@PathVariable Long id) {
        return studentRepository.findById(id).map(studnet -> {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
