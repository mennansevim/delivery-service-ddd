package com.global.delivery.infrastructure.configuration.couchbase;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "couchbase")
@Getter
@Setter
public class CouchbaseProperties {

    private String connectionString;
    private String username;
    private String password;
    private String bucket;
    private String companyCollection;
}
