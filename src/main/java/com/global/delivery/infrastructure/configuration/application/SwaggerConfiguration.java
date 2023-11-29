package com.global.delivery.infrastructure.configuration.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfiguration {

    public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

    @Value("${spring.application.name}")
    private String applicationName;



    @Bean
    public Docket api() {
        final Contact contact = new Contact("IMen Delivery Service", "https://www.imen-delivery.com", "imen@delivery.com");
        final ApiInfo apiInfo = new ApiInfoBuilder()
                .contact(contact)
                .title(applicationName)
                .build();

        final Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .pathMapping("/")
                .apiInfo(ApiInfo.DEFAULT)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Date.class)
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .directModelSubstitute(ZonedDateTime.class, Date.class)
                .securityContexts(List.of(SecurityContext.builder().forPaths(regex(DEFAULT_INCLUDE_PATTERN)).build()))
                .useDefaultResponseMessages(false)
                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .build();

        return docket;
    }

}
