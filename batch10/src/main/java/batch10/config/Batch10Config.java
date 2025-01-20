package batch10.config;

import jakarta.annotation.Nonnull;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.repository.ExecutionContextSerializer;
import org.springframework.batch.core.repository.dao.Jackson2ExecutionContextStringSerializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Batch10Config extends DefaultBatchConfiguration {

    @Override
    @Nonnull
    protected ExecutionContextSerializer getExecutionContextSerializer() {
        return new Jackson2ExecutionContextStringSerializer();
    }
}
