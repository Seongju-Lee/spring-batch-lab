package awsspringmessaginglab.sj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSjApplication {

	public static void main(String[] args) {
		SpringApplication.from(SjApplication::main).with(TestSjApplication.class).run(args);
	}

}
