package batch2.support.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"sj.springBatch.global", "sj.springBatch.batch2"})
public class GlobalConfig {
}
