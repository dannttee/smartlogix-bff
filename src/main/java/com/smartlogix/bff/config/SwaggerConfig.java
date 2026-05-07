package com.smartlogix.bff.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("SmartLogix BFF API")
                .version("1.0.0")
                .description("Backend For Frontend - Capa de integración entre el frontend y los microservicios de SmartLogix")
                .contact(new Contact()
                    .name("Dante Muñoz")
                    .email("dant.munozf@duocuc.cl")));
    }
}