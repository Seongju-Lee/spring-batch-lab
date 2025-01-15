package batch9.job;

import batch8.service.UserService;
import jakarta.persistence.EntityManagerFactory;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import sj.batch.global.entity.user.User;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TransferNewUserJobConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final EntityManagerFactory entityManagerFactory;

    private static final int CHUNK_SIZE = 50;

    @Bean
    public Job transferNewUserJob() {
        return new JobBuilder("TRANSFER_NEW_USER_JOB", jobRepository)
            .start(transferNewUserStep(null))
            .build();
    }

    @Bean
    @JobScope
    public Step transferNewUserStep(
        @Value("#{jobParameters['targetDate']}") LocalDate targetDate
    ) {
        return new StepBuilder("TRANSFER_NEW_USER_STEP", jobRepository)
            .<User, User>chunk(CHUNK_SIZE, platformTransactionManager)
            .reader(reader())
            .processor(processor(null))
            .writer(writer())
            .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<User> reader() {
        return new JpaPagingItemReaderBuilder<User>()
            .name("TRANSFER_NEW_USER_STEP_READER")
            .entityManagerFactory(entityManagerFactory)
            .queryString("""
                SELECT u
                FROM User u
                """)
            .pageSize(CHUNK_SIZE)
            .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<User, User> processor(
        @Value("#{jobParameters['targetDate']}") final LocalDate targetDate
    ) {
        return new FunctionItemProcessor<>(user -> {
            if (user.getRegisteredAt().toLocalDate().isEqual(targetDate)) {
                return user;
            }
            return null;
        });
    }

    @Bean
    @StepScope
    public ItemWriter<User> writer() {
        return chunk -> chunk.getItems().forEach(user ->
            log.info("DB에 유저 정보 저장 =>  id: {}, name: {}", user.getId(), user.getName())
        );
    }
}
