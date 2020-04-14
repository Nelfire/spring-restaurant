package dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author 20-100
 *
 */
@Configuration
@Import({ JpaConfig.class, DataSourceH2TestConfig.class })
public class JpaTestConfig {

}