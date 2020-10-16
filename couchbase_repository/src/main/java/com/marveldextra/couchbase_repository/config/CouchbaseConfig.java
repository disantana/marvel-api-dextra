package com.marveldextra.couchbase_repository.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration{

    @Value("${ip}")
    private String ip;

    @Override
    public String getConnectionString() {
        return "couchbase://".concat(ip);
    }

    @Override
    public String getUserName() {
        return "Administrator";
    }

    @Override
    public String getPassword() {
        return "123456";
    }

    @Override
    public String getBucketName() {
        return "marvel";
    } 
    
    
}
