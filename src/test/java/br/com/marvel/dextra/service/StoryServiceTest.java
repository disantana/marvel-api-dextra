package br.com.marvel.dextra.service;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.dto.StoryRequestDTO;
import br.com.marvel.dextra.services.CharacterService;
import br.com.marvel.dextra.services.StoryService;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoryServiceTest extends Assertions {

  @Autowired CharacterService characterService;
  @Autowired StoryService service;

  @Test
  public void shouldSaveWithoutException() {
    Character character = characterService
        .save(new CharacterRequestDTO("Kuvmba Story", "testing ...", "resource"));

    Story saved = service.save(character.getId(),
        new StoryRequestDTO("Locw Story",300,"crazy story"));
    assertNotNull(saved);
  }
}
