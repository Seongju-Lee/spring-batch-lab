package batch9.support.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"sj.batch.global", "batch9"})
public class GlobalConfig {
}
