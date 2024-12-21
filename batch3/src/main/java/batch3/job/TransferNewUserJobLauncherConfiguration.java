package batch3.job;

import batch3.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.PlatformTransactionManager;
import sj.batch.global.entity.user.User;

@Configuration
@RequiredArgsConstructor
public class TransferNewUserJobLauncherConfiguration extends QuartzJobBean {

    private final JobLauncher jobLauncher;
    private final Job transferNewUserJob;

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) {
        final JobParameters jobParameters = new JobParametersBuilder()
            .addLocalDate("tartgetDate", LocalDateTime.now().minusDays(1).toLocalDate())
            .toJobParameters();
        jobLauncher.run(transferNewUserJob, jobParameters);
    }
}
