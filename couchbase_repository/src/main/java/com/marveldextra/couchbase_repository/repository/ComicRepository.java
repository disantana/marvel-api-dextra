package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Comic;
import org.springframework.data.couchbase.core.query.N1QLExpression;
import org.springframework.data.couchbase.core.query.N1QLQuery;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ComicRepository extends CrudRepository<Comic,String> {
   
    @Query("#{#n1ql.selectEntity} WHERE #{#n1ql.filter} AND character.name = $1")
    List<Comic> findAllByCharacter(String characterName);
}
