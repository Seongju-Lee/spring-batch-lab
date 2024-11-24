package sj.batch.stepAndJob.support.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"sj.springBatch.global", "sj.springBatch.batch1_stepAndJob"})
public class GlobalConfig {
}
