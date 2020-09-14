package com.marveldextra.couchbase_repository.repository;


import com.marveldextra.couchbase_repository.entity.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class CharacterRepositoryTest {

    @Autowired private CharacterRepository repository;
    private Character character;

    @Test
    public void shouldSaveANewCharacterWithID(){
        character = repository.save(Character.builder()
                .description("description")
                .name("name")
                .resourceURI("http://www.marvel.com").build());

        Iterable<Character> all = repository.findAll();
        Assertions.assertEquals("description", character.getDescription());
    }

    @Test
    public void shouldReturnCharacterWithNameSearched(){
        List<Character> c = repository.findCharacterByNameExists("name");
        Assertions.assertFalse(c.isEmpty());
    }

    @Test
    public void shouldReturnCharacterWithIdSearched(){
       character = repository.findCharacterByNameExists("name").get(0);
       Optional<Character> searched = repository.findById(character.getId());
       Assertions.assertTrue(searched.get().equals(character));
    }


}
