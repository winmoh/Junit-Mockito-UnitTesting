package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import com.example.demo.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.List;


@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    private JavaMailSender JMSender;

    @Value("$(spring.mail.username)")
    private String senderMail;

    public void sendEmail(String email)  {
        SimpleMailMessage smm=new SimpleMailMessage();
        smm.setFrom(senderMail);
        smm.setTo(email);
        smm.setSubject("absence reclamation");
        smm.setText("you will go home for a week if you continue absence");
        JMSender.send(smm);

    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Boolean existsEmail = studentRepository
                .selectExistsEmail(student.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + student.getEmail() + " taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if(!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "Student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }
}
