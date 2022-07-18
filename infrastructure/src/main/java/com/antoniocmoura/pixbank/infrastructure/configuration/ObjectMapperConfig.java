package com.antoniocmoura.pixbank.infrastructure.configuration;

import com.antoniocmoura.pixbank.infrastructure.configuration.json.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return Json.mapper();
    }

}
