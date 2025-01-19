
package batch9.job;

import batch9.support.fixture.UserFixture;
import batch9.support.template.DatabaseTemplate;
import batch9.support.template.TestTemplate;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import sj.batch.global.entity.user.User;
import sj.batch.global.entity.user.UserRepository;

@Slf4j
@Testcontainers
@SpringBootTest
@SpringBatchTest
class JpaCursorReaderConfigurationTest extends TestTemplate{

    @Autowired
    private Job jpaCursorReaderJob;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DatabaseTemplate databaseTemplate;

    @BeforeEach
    public void setup() {
        databaseTemplate.truncate();
    }

    @Test
    @SneakyThrows
    void run() throws Exception {
        // given
        List<User> users = List.of(
            UserFixture.create("sj"),
            UserFixture.create("sj2"),
            UserFixture.create("sj3"),
            UserFixture.create("sj4"),
            UserFixture.create("sj5"),
            UserFixture.create("sj6")
        );
        userRepository.saveAll(users);

        // when
        jobLauncher.run(jpaCursorReaderJob, new JobParameters());
    }
}
