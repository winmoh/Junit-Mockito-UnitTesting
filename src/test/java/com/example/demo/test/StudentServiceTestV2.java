package com.example.demo.test;


import com.example.demo.student.StudentRepository;
import com.example.demo.student.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class StudentServiceTestV2 {


    @Mock
    private StudentRepository SRepo;
    private StudentService SService;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void before(){
        autoCloseable= MockitoAnnotations.openMocks(this);
        SService=new StudentService(SRepo);

    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }






    @Test
    public void StudentServTest(){


        //when
        SService.getAllStudents();

        //then
        verify(SRepo).findAll();


    }
}
