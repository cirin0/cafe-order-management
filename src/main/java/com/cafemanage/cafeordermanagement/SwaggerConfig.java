package com.cafemanage.cafeordermanagement;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI customOpenAPI() {
    Info info = new Info()
        .title("Cafe Order Management API")
        .version("1.0")
        .description("API for managing orders in a cafe");

    return new OpenAPI().info(info);
  }
}
