package batch10.job;

import batch10.support.fixture.UserFixture;
import batch10.support.template.DatabaseTemplate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
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
class ExecutionContextTestJobConfigurationTest {

    @Autowired
    private Job executionContextTestJob;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private DatabaseTemplate databaseTemplate;

    @Autowired
    private JobOperator jobOperator;
    @Autowired
    private JobExplorer jobExplorer;

    @Test
    void run() throws Exception{
        databaseTemplate.truncate();
        jobLauncher.run(executionContextTestJob, new JobParameters());
    }

    @Test
    void restart() throws Exception {
        Long lastFailedExecutionId = jobExplorer
            .findJobInstancesByJobName("EXECUTION_CONTEXT_TEST_JOB", 0, 1).stream()
            .flatMap(jobInstance -> jobExplorer.getJobExecutions(jobInstance).stream())
            .filter(jobExecution -> jobExecution.getStatus() == BatchStatus.FAILED)
            .map(jobExecution -> jobExecution.getId())
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No failed job execution found"));

        jobOperator.restart(lastFailedExecutionId);
    }
}
