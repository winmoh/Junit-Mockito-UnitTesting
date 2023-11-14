package com.example.demo.student;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private final StudentService studentService;


    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestParam String email ) throws MessagingException {
        studentService.sendEmail(email);
        return new ResponseEntity<String>("student notified",null, HttpStatus.OK);


    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(
            @PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}
