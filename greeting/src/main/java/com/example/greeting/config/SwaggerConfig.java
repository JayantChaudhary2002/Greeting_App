package com.example.greeting.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Greeting API")
                        .version("1.0")
                        .description("This is a sample API for user authentication and email services.")
                        .contact(new Contact()
                                .name("Jayant Chaudhary")
                                .email("jayantchaudhary2002.com")
                        )
                );
    }
}
