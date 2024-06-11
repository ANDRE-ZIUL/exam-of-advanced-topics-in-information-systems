package br.edu.univas.topics.andre_dias_integration_test;

import org.springframework.boot.SpringApplication;

public class TestAndreDiasIntegrationTestApplication {

	public static void main(String[] args) {
		SpringApplication.from(AndreDiasIntegrationTestApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
