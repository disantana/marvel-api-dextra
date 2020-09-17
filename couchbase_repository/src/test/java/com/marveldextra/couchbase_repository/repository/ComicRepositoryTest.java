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
    public void shouldReturnComicByCharacter(){
      Character character = new Character();
      character.setDescription("Character's description");
      character.setName("ID");

      Character saved = characterRepository.save(character);
      Comic comic = new Comic();

      comic.setCharacter(character);
      comic.setTitle("Title");
      comic.setPageCount(10);
      comic.setDescription("Description");
      Character searchedCharacter = characterRepository.findCharacterByNameExists("ID").get(0);

      List<Comic> result = repository.findComicsByCharacterExists(searchedCharacter);
      Assertions.assertFalse(result.isEmpty());
    }
}
