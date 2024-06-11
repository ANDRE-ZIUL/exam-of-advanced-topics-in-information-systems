package br.edu.univas.topics.andre_dias_integration_test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class AndreDiasIntegrationTestApplicationTests {

	@Test
	void contextLoads() {
	}

}
