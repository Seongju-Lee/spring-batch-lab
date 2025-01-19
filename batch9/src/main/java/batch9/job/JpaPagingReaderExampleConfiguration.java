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
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import sj.batch.global.entity.user.User;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JpaPagingReaderExampleConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final EntityManagerFactory entityManagerFactory;

    private static final int CHUNK_SIZE = 2;

    @Bean
    public Job jpaPagingReaderJob() {
        return new JobBuilder("jpaPagingReaderJob", jobRepository)
            .start(jpaPagingReaderStep())
            .build();
    }

    @Bean
    public Step jpaPagingReaderStep() {
        return new StepBuilder("jpaPagingReaderStep", jobRepository)
            .<User, User>chunk(CHUNK_SIZE, platformTransactionManager)
            .reader(jpaPagingReader())
            .writer(writer())
            .build();
    }

    @Bean
    public JpaPagingItemReader<User> jpaPagingReader() {
        return new JpaPagingItemReaderBuilder<User>()
            .name("jpaPagingItemReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("select u from User u order by u.id")
            .pageSize(10)
            .build();
    }

    @Bean
    public ItemWriter<User> writer() {
        return item -> log.info("User 정보 => {}", item);
    }
}
