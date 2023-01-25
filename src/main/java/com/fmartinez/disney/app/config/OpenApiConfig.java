package com.fmartinez.disney.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fmartinez.disney.app.constant.ApplicationConstant.*;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Disney-Microservice")
                .version("1.0.0")
                .description("API Rest demo application for characters, movies & genre managment")
                .license(new License()
                        .name("Licence MIT")
                        .url("https://opensource.org/licenses/MIT"))
                .contact(new Contact()
                        .name("Facundo Martinez")
                        .email("martinez.facundo85@gmail.com")));

    }

    @Bean
    public GroupedOpenApi charactersApi() {
        String[] paths = {CHARACTER_PATH + "/**"};
        return GroupedOpenApi.builder()
                .group("Characters")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi movieSerieApi() {
        String[] paths = {MOVIE_SERIE_PATH + "/**"};
        return GroupedOpenApi.builder()
                .group("Movies-Series")
                .pathsToMatch(paths).consumesToMatch()
                .build();
    }

    @Bean
    public GroupedOpenApi genreApi() {
        String[] paths = {GENRE_PATH + "/**"};
        return GroupedOpenApi.builder()
                .group("Genre")
                .pathsToMatch(paths)
                .build();
    }
}
