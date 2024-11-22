package org.javaacademy.insurance.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(InsuranceProperty.class)
public class InsuranceConfig {
}
