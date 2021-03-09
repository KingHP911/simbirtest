package ru.dvkorol.simbirtest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("ru.dvkorol.simbirtest")
public class SimbirConfig {
}
