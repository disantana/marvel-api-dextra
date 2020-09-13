package com.marveldextra.couchbase_repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

@Document
@Builder
@Value
public class Character {

    @Id @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;
    @Field
    private String name;
    @Field
    private String description;
    @Field
    private String resourceURI;
}