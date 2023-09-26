package com.correoargentino.services.postalcode.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.swagger.v3.core.jackson.ModelResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JacksonConfiguration.
 */
@Configuration
public class JacksonConfiguration {
  @Bean
  public ModelResolver modelResolver(ObjectMapper objectMapper) {
    return new ModelResolver(objectMapper
        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE));
  }
}