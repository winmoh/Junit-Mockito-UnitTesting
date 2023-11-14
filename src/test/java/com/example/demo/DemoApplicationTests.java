package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DemoApplicationTests {



	@Test
	void isAdditionOfNumbersCorrect() {
		//given
		int numberOne=20;
		int numberTwo=90;

		//when
		Calculator calc=new Calculator();
		int result=calc.addTwoNumbers(numberTwo,numberOne);

		//then
		int expec=110;
		assertThat(result).isEqualTo(expec);

	}

	class Calculator{
		int addTwoNumbers(int a,int b){
			return a+b;
		}
	}

}
