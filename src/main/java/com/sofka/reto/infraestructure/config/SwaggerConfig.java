package com.sofka.reto.infraestructure.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi tourGroupApi() {
        return GroupedOpenApi.builder()
                .group("tour")
                .pathsToMatch("/**")
                .build();
    }
}