package batch3.job;

import static org.assertj.core.api.Assertions.assertThat;

import batch3.support.fixture.UserFixture;
import batch3.support.template.TestTemplate;
import java.time.LocalDateTime;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import sj.batch.global.entity.user.User;
import sj.batch.global.entity.user.UserRepository;
import batch3.support.template.DatabaseTemplate;

@Slf4j
@Testcontainers
@SpringBootTest
@SpringBatchTest
class TransferNewUserJobConfigurationTest extends TestTemplate {

    @Autowired
    private Job transferNewUserJob;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DatabaseTemplate databaseTemplate;

    @BeforeEach
    void setup() {
        databaseTemplate.truncate();
    }

    @Test
    @SneakyThrows
    void run1() throws Exception {
        // given
        final User user = UserFixture.create(LocalDateTime.now().minusDays(1));
        userRepository.save(user);

        // when
        final JobParameters jobParameters1 = new JobParametersBuilder()
            .addLocalDate("targetDate", LocalDateTime.now().minusDays(1).toLocalDate())
            .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(transferNewUserJob, jobParameters1);
    }
}
