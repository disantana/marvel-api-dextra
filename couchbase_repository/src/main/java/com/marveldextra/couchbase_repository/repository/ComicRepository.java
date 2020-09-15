package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Comic;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ComicRepository extends CrudRepository<Comic,String> {
   
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND character.name = $1")
    List<Comic> findAllByCharacter(String name);

    Page<Comic> findAll(Pageable pageable);
}
