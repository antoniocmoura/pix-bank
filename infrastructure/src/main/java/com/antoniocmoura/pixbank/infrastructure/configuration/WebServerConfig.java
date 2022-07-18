package com.antoniocmoura.pixbank.infrastructure.configuration;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.stream.Collectors;

@Configuration
@ComponentScan("com.antoniocmoura.pixbank")
public class WebServerConfig {

    @Bean
    public OpenApiCustomiser sortTagsAlphabetically() {
        return openApi -> openApi.setTags(openApi.getTags()
                .stream()
                .sorted(Comparator.comparing(tag -> StringUtils.stripAccents(tag.getName())))
                .collect(Collectors.toList()));
    }

}
