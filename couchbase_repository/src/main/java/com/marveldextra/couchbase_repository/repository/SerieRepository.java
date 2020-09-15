package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Serie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SerieRepository extends CrudRepository<Serie,String> {

    List<Serie> findSeriesByCharacterExists(Character character);
}
