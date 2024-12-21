package batch2.support.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"sj.batch.global", "batch3"})
public class GlobalConfig {
}
