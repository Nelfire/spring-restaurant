package dev.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 20-100
 *
 */
@Configuration
@EnableTransactionManagement
@Import({ JpaConfig.class, DataSourceH2TestConfig.class })
public class JpaTestConfig {

}