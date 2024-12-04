package batch2.job;

import static org.assertj.core.api.Assertions.assertThat;

import batch2.support.fixture.UserFixture;
import batch2.support.template.TestTemplate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import sj.batch.global.entity.user.User;
import sj.batch.global.entity.user.UserRepository;
import batch2.support.template.DatabaseTemplate;

@Slf4j
@Testcontainers
@SpringBootTest
@SpringBatchTest
class TransferNewUserJobConfigurationTest extends TestTemplate {

    @Autowired
    private Job transferNewUserJob;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DatabaseTemplate databaseTemplate;

    @BeforeEach
    void setup() {
        jobLauncherTestUtils.setJob(transferNewUserJob);
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
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters1);
    }

    @Test
    @SneakyThrows
    void test_identifying() throws Exception {
        // given
        final User user = UserFixture.create(LocalDateTime.now().minusDays(1));
        userRepository.save(user);

        // when
        final JobParameters jobParameters1 = new JobParametersBuilder()
            .addLocalDate("targetDate", LocalDateTime.now().minusDays(1).toLocalDate())
            .addString("randomUuid", UUID.randomUUID().toString(), false)
            .toJobParameters();
        JobExecution jobExecution1 = jobLauncherTestUtils.launchJob(jobParameters1);
//        log.info("jobExecution1 종료");

        final JobParameters jobParameters2 = new JobParametersBuilder()
            .addLocalDate("targetDate", LocalDateTime.now().minusDays(1).toLocalDate())
            .addString("randomUuid", UUID.randomUUID().toString(), false)
            .toJobParameters();
        JobExecution jobExecution2 = jobLauncherTestUtils.launchJob(jobParameters2);
//        log.info("jobExecution2 종료");
    }

    @Test
    @SneakyThrows
    void test_retry() throws Exception {
        // given
        final User user = UserFixture.create(LocalDateTime.now().minusDays(1));
        userRepository.save(user);

        // when
        final JobParameters jobParameters1 = new JobParametersBuilder()
            .addLocalDate("targetDate", LocalDateTime.now().minusDays(1).toLocalDate())
            .addString("randomUuid", UUID.randomUUID().toString(), false)
            .toJobParameters();
        JobExecution jobExecution1 = jobLauncherTestUtils.launchJob(jobParameters1);
    }
}
