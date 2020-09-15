package com.marveldextra.couchbase_repository.repository;


import com.marveldextra.couchbase_repository.entity.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class CharacterRepositoryTest extends Assertions{

    @Autowired private CharacterRepository repository;
    private Character character;

    @Test
    public void shouldSaveANewCharacterWithID(){
        character = repository.save(Character.builder()
                .description("description")
                .name("name")
                .resourceURI("http://www.marvel.com").build());

        Iterable<Character> all = repository.findAll();
        assertEquals("description", character.getDescription());
    }

    @Test
    public void shouldReturnCharacterWithNameSearched(){
        List<Character> c = repository.findCharacterByNameExists("name");
        assertFalse(c.isEmpty());
    }

    @Test
    public void shouldReturnCharacterWithIdSearched(){
       character = repository.findCharacterByNameExists("name").get(0);
       Optional<Character> searched = repository.findById(character.getId());
       assertTrue(searched.get().equals(character));
    }

    @Test
    public void shouldReturnPageableCharacter(){
      Page<Character> paginatedCharacter = repository.findAll(PageRequest.of(0, 20));
      paginatedCharacter.getContent();
      assertEquals(20, paginatedCharacter.getSize());
    }
}
