package sj.springBatch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;
import sj.springBatch.support.TestTemplate;

@Testcontainers
@SpringBootTest
class SpringBatchApplicationTests extends TestTemplate {

	@Test
	void contextLoads() {
	}

}
