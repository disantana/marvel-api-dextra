package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Story;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoryRepository extends CrudRepository<Story, String> {

   List<Story> findStoriesByCharacterExists(Character character);
}
