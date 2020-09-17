package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Comic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ComicRepository extends CrudRepository<Comic,String> {

  List<Comic> findComicsByCharacterExists(Character character);

  Page<Comic> findAll(Pageable pageable);
}
