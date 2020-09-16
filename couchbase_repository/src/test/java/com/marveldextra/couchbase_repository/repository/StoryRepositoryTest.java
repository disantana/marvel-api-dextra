package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StoryRepositoryTest extends Assertions {

    @Autowired private StoryRepository repository;
    @Autowired private CharacterRepository characterRepository;

    @Test
    public void shouldReturnStorySearchedByCharacter(){
      Character character = new Character();
      character.setName("Story character Teste");
      character.setDescription("Character's description");

     Story story = repository.save(Story.builder()
              .character(character)
              .pageCount(300)
              .title("Story title")
              .description("Story description")
              .build());

        List<Story> stories = repository.findStoriesByCharacterExists(character);
        assertFalse(stories.isEmpty());
        assertTrue(stories.get(0).getDescription().equals("Story description"));
    }


}
