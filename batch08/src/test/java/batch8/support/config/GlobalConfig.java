package batch8.support.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"sj.batch.global", "batch8"})
public class GlobalConfig {
}
