package com.global.delivery.infrastructure.configuration.couchbase;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CouchbaseIndex {

    private String bucket;
    private String name;
    private List<String> fields;
}
