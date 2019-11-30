package com.war.orke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.war.orke"})
@EntityScan(basePackages = {"com.war.orke.entity"})
@EnableJpaRepositories(basePackages = {"com.war.orke.repository"})
@EnableDiscoveryClient
public class OrkeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrkeApplication.class, args);
    }

}
