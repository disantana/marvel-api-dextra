package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Serie;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SerieRepository extends CrudRepository<Serie,String> {

    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND character.name = $1")
    List<Serie> findAllByCharacter(String name);
}
