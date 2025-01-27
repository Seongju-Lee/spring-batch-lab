package batch3.job;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

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
