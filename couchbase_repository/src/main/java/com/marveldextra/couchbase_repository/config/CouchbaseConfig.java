package com.marveldextra.couchbase_repository.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration{

    @Override
    public String getConnectionString() {
        return "couchbase://172.17.0.2";
    }

    @Override
    public String getUserName() {
        return "marvelapp";
    }

    @Override
    public String getPassword() {
        return "marvel";
    }

    @Override
    public String getBucketName() {
        return "marvel";
    } 
    
    
}
