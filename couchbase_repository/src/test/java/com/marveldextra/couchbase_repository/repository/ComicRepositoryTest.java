package com.marveldextra.couchbase_repository.repository;


import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Comic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class ComicRepositoryTest {

    @Autowired private ComicRepository repository;
    @Autowired private CharacterRepository characterRepository;

    @Test
    public void shouldReturnComicWithSpecifiedCharacter(){

        Character character = characterRepository.save(Character.builder()
                .name("Comic's character Teste")
                .description("Character's description")
                .build());
        
        Comic comic = repository.save(Comic.builder()
                .description("Comic description")
                .pageCount(10)
                .character(character).build());
        
        List<Comic> comics = repository.findAllByCharacter(character.getName());
        comics.size();
        Assertions.assertTrue(!comics.isEmpty());
        Assertions.assertEquals("Comic description",comics.get(0).getDescription());
        Assertions.assertTrue(comics.get(0).getCharacter().equals(character));
    }
}
