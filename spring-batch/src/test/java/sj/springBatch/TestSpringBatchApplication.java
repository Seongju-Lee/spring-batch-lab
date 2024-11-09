package sj.springBatch;

import org.springframework.boot.SpringApplication;

public class TestSpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringBatchApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
