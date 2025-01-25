package batch10.job;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ExecutionContextTestJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job executionContextTestJob() {
        return new JobBuilder("EXECUTION_CONTEXT_TEST_JOB", jobRepository)
            .start(step())
            .build();
    }

    @Bean
    public Step step() {
        return new StepBuilder("STEP", jobRepository)
            .<String, String>chunk(2, platformTransactionManager)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .build();
    }

    @Bean
    public CustomReader reader() {
        return new CustomReader();
    }

    @Bean
    public CustomProcessor processor() {
        return new CustomProcessor();
    }

    @Bean
    public ItemWriter<String> writer() {
        return items -> System.out.println("Writing items: " + items);
    }
}
