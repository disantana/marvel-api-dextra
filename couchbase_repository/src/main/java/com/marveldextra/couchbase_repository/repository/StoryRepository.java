package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Event;
import com.marveldextra.couchbase_repository.entity.Story;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoryRepository extends CrudRepository<Story, String> {

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND character.name = $1")
    List<Story> findAllByCharacter(String name);
}
