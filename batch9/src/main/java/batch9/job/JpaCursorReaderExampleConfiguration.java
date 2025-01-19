package batch9.job;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import sj.batch.global.entity.user.User;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JpaCursorReaderExampleConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final EntityManagerFactory entityManagerFactory;

    private static final int CHUNK_SIZE = 2;

    @Bean
    public Job jpaCursorReaderJob() {
        return new JobBuilder("jpaCursorReaderJob", jobRepository)
            .start(jpaCursorReaderStep())
            .build();
    }

    @Bean
    public Step jpaCursorReaderStep() {
        return new StepBuilder("jpaCursorReaderStep", jobRepository)
            .<User, User>chunk(CHUNK_SIZE, platformTransactionManager)
            .reader(jpaCursorReader())
            .writer(writer())
            .build();
    }

    @Bean
    public JpaCursorItemReader<User> jpaCursorReader() {
        return new JpaCursorItemReaderBuilder<User>()
            .name("jpaCursorItemReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("select u from User u")
            .build();
    }

    @Bean
    public ItemWriter<User> writer() {
        return item -> log.info("User 정보 => {}", item);
    }
}
