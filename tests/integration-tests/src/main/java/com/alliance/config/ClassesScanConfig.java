package com.alliance.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.alliance")
@EnableJpaRepositories(basePackages= "com.alliance.repository")
@EntityScan(basePackages = "com.alliance.entity")
public class ClassesScanConfig {
}
