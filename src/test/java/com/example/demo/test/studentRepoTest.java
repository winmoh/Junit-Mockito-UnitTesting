package com.example.demo.test;

import com.example.demo.student.Gender;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class studentRepoTest {

    @Autowired
    private StudentRepository SRepo;

    @AfterEach
    void tearDown(){
        SRepo.deleteAll();

    }


    @Test
    public void repoTestEmailShoulNotExist(){

        //given
       String email="mohamed@gmail.com";

        //when
        Boolean expected=SRepo.selectExistsEmail(email);

        //then
        assertThat(expected).isFalse();


    }
    @Test
    public void repoTestEmailShouldExist(){

        //given
        Student std =new Student("mohamed","mohamed@gmail.com", Gender.MALE);
        SRepo.save(std);

        //when
        Boolean expected=SRepo.selectExistsEmail("mohamed@gmail.com");

        //then
        assertThat(expected).isTrue();


    }
}
