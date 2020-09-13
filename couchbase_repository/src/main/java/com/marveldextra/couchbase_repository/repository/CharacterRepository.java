package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

public interface CharacterRepository extends CouchbaseRepository<Character, String> {

}
