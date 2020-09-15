package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event,String> {

    List<Event> findEventsByCharacterExists(Character character);
}
