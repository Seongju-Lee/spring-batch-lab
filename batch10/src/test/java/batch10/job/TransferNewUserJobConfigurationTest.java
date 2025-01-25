package batch10.job;

import batch10.support.fixture.UserFixture;
import batch10.support.template.DatabaseTemplate;
import batch10.support.template.TestTemplate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
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
@ActiveProfiles("test")
class TransferNewUserJobConfigurationTest {

    @Autowired
    private Job transferNewUserJob;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private DatabaseTemplate databaseTemplate;

    @BeforeEach
    void setup() {
//        databaseTemplate.truncate();
        jobLauncherTestUtils.setJob(transferNewUserJob);
    }

    @Test
    void run() throws Exception {
        // given
        final List<User> users = List.of(
            UserFixture.create("sj", LocalDateTime.now().minusDays(1)),
            UserFixture.create("sj2", LocalDateTime.now().minusDays(1)),
            UserFixture.create("sj3", LocalDateTime.now().minusDays(1)),
            UserFixture.create("sj4", LocalDateTime.now().minusDays(1)),
            UserFixture.create("sj5", LocalDateTime.now().minusDays(1)),
            UserFixture.create("sj6", LocalDateTime.now().minusDays(1))
        );
        userRepository.saveAll(users);

        // when
        final JobParameters jobParameters = new JobParametersBuilder()
            .addLocalDate("targetDate", LocalDateTime.now().minusDays(1).toLocalDate())
            .toJobParameters();
        jobLauncherTestUtils.launchJob(jobParameters);
    }
}
