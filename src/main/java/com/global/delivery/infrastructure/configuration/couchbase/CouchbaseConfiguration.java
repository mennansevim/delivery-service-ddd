package com.global.delivery.infrastructure.configuration.couchbase;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.ReactiveCluster;
import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.couchbase.client.java.json.JsonValueModule;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class CouchbaseConfiguration extends AbstractCouchbaseConfiguration {

   private final CouchbaseProperties couchbaseProperties;
    public CouchbaseConfiguration(CouchbaseProperties couchbaseProperties) {
        this.couchbaseProperties = couchbaseProperties;
    }

   @Override
   public String getConnectionString() {
       return couchbaseProperties.getConnectionString();
   }

    @Override
    public String getUserName() {
        return couchbaseProperties.getUsername();
    }

    @Override
    public String getPassword() {
        return couchbaseProperties.getPassword();
    }

    @Override
   public String getBucketName() {
       return couchbaseProperties.getBucket();
   }

   @Bean
   public ReactiveCluster reactiveCouchbaseCluster() {
       final ObjectMapper mapper = new Jackson2ObjectMapperBuilder()
               .modules(new JsonValueModule())
               .serializationInclusion(JsonInclude.Include.NON_NULL)
               .build();
       mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

       ClusterEnvironment clusterEnvironment =
               ClusterEnvironment.builder()
                       .jsonSerializer(JacksonJsonSerializer.create(mapper))
                       .build();

       return Cluster
               .connect(
                       couchbaseProperties.getConnectionString(),
                       ClusterOptions
                               .clusterOptions(couchbaseProperties.getUsername(), couchbaseProperties.getPassword())
                               .environment(clusterEnvironment)
               )
               .reactive();
   }
}
