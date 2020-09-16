package com.marveldextra.couchbase_repository.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration{

  @Value("${couchbase_host}")
  private String hostname;

  @Value("${couchbase_bucket}")
  private String bucket;

  @Value("${couchbase_bucket_password}")
  private String password;

  @Override
  public String getConnectionString() {
      return "couchbase://".concat(hostname);
  }

  @Override
  public String getUserName() {
      return bucket;
  }

  @Override
  public String getPassword() {
      return password;
  }

  @Override
  public String getBucketName() {
      return bucket;
  }



    
}
