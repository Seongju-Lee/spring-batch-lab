package sj.batch.global.config.BatchConfig;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
@RequiredArgsConstructor
public class BatchConfig extends DefaultBatchConfiguration {
    private final DataSource dataSource;

    @Bean
    public ApplicationRunner applicationRunner(final DataSource dataSource) {
        return args -> {
            if (!existsBatchMetaTable(dataSource)) {
                Resource resource = new ClassPathResource("org/springframework/batch/core/schema-mysql.sql");
                ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(resource);
                resourceDatabasePopulator.execute(dataSource);
            }
        };
    }

    private boolean existsBatchMetaTable(final DataSource dataSource) {
        final String[] tableNames = {
            "BATCH_JOB_EXECUTION",
            "BATCH_JOB_EXECUTION_CONTEXT",
            "BATCH_JOB_EXECUTION_PARAMS",
            "BATCH_JOB_EXECUTION_SEQ",
            "BATCH_JOB_INSTANCE",
            "BATCH_JOB_SEQ",
            "BATCH_STEP_EXECUTION",
            "BATCH_STEP_EXECUTION_CONTEXT",
            "BATCH_STEP_EXECUTION_SEQ"
        };

        try (Connection connection = dataSource.getConnection()) {
            final DatabaseMetaData metaData = connection.getMetaData();
            for (String tableName : tableNames) {
                try (ResultSet resultSet = metaData.getTables(null, null, tableName, new String[]{"TABLE"})) {
                    if (!resultSet.next()) {
                        return false;
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check if tables exist", e);
        }
    }
}
