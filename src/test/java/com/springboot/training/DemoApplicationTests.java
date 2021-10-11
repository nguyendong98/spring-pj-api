package com.springboot.training;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DemoApplicationTests {

//	Calculator underTest = new Calculator();
//
//	@Test
//	void contextLoads() {
//	}

//	@Test
//	void itShouldAddNumber() {
//		int numberOne = 10;
//		int numberTwo = 20;
//		int result = underTest.add(numberOne, numberTwo);
//		assertThat(result).isEqualTo(30);
//	}

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}

}
