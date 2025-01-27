package batch9.job;

import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import sj.batch.global.entity.user.User;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JdbcCursorReaderExampleConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource; // DB에 연결하기 위한 Datasource 객체

    private static final int CHUNK_SIZE = 2;

    @Bean
    public Job jdbcCursorReaderJob() {
        return new JobBuilder("jdbcCursorReaderJob", jobRepository)
            .start(jdbcCursorReaderStep())
            .build();
    }

    @Bean
    public Step jdbcCursorReaderStep() {
        return new StepBuilder("jdbcCursorReaderStep", jobRepository)
            .<User, User>chunk(CHUNK_SIZE, platformTransactionManager)
            .reader(jdbcCursorReader())
            .writer(writer())
            .build();
    }

    @Bean
    public JdbcCursorItemReader<User> jdbcCursorReader() {
        return new JdbcCursorItemReaderBuilder<User>()
            .name("jdbcCursorItemReader")
            .beanRowMapper(User.class) // 쿼리 결과를 인스턴스와 매핑하기 위한 Mapper 객체, 내부적으로 BeanPropertyRowMapper를 사용해서 rowMapper 필드에 설정
            .dataSource(dataSource)
            .fetchSize(CHUNK_SIZE)
            .sql("select * from users")
            .build();
    }

    @Bean
    public ItemWriter<User> writer() {
        return item -> log.info("User 정보 => {}", item);
    }
}
