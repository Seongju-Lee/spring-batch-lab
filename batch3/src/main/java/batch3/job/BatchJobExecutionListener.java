package batch3.job;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchJobExecutionListener implements JobExecutionListener {

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("[Job 실행]  JobName: " + jobExecution.getJobInstance().getJobName());
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("[Job 종료] JobName: " + jobExecution.getJobInstance().getJobName() +
            ", 상태: " + jobExecution.getStatus());
    }
}
