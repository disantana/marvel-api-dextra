package com.marveldextra.couchbase_repository.repository;


import com.marveldextra.couchbase_repository.entity.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CharacterRepositoryTest {

    @Autowired private CharacterRepository repository;

    @Test
    public void shouldSaveANewCharacter(){
        Character c = repository.save(Character.builder().description("description").name("name").build());
        Assertions.assertEquals("description", c.getDescription());
    }
}
