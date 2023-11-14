package com.example.demo.test;


import com.example.demo.student.Gender;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.student.StudentService;
import com.example.demo.student.exception.BadRequestException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class studentServRepoV1 {

    private StudentService SService;

    @Mock
    private StudentRepository SRepo;


    @BeforeEach
    void before(){

        SService=new StudentService(SRepo);
    }



    @Test
    public void findAllTest(){
        //when
        SService.getAllStudents();

        //then
        verify(SRepo).findAll();
    }

    @Test
    public void  saveServiceStudent(){

        //given
        Student std=new Student("ayoubiz","ayoubiz@gmail.com", Gender.MALE);

        //when
        SService.addStudent(std);

        //then
        ArgumentCaptor<Student> argCaptor=ArgumentCaptor.forClass(Student.class);
        verify(SRepo).save(argCaptor.capture());
        Student capStd=argCaptor.getValue();
        assertThat(std).isEqualTo(capStd);
    }

    @Test
    public void EmailExistingexeptionRaisinTest(){
        //given
        Student std=new Student("ayoubiz","ayoubiz@gmail.com", Gender.MALE);

        given(SRepo.selectExistsEmail(std.getEmail()))
                .willReturn(true);
        //when
        //then
        assertThatThrownBy(()->SService.addStudent(std))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + std.getEmail() + " taken");

    }
}
