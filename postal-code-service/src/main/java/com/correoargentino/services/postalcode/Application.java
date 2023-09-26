package com.correoargentino.services.postalcode;

import com.correoargentino.services.postalcode.entity.PostalCode;
import com.correoargentino.services.postalcode.respository.PostalCodeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Application.
 */
@Slf4j
@SpringBootApplication
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * loadData.
   */
  @Bean
  public CommandLineRunner loadData(PostalCodeRepository repository, ObjectMapper mapper) {
    return args -> {
      var typeReference = new TypeReference<List<PostalCode>>() {
      };
      var inputStream = TypeReference.class
          .getResourceAsStream("/data/postal_codes.json");
      var postalCodes = mapper.readValue(inputStream, typeReference);
      repository.saveAll(postalCodes);
    };
  }
}
