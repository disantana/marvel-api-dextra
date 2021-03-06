package com.marveldextra.couchbase_repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Document
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    
    @Field    
    private String title;
    
    @Field
    private int pageCount;
    
    @Field
    private String description;
    @Field
    private Character character;
}
