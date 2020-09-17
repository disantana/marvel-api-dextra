package br.com.marvel.dextra.service;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.dto.ComicRequestDTO;
import br.com.marvel.dextra.services.CharacterService;
import br.com.marvel.dextra.services.ComicService;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Comic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ComicServiceTest extends Assertions {

  @Autowired  ComicService service;
  @Autowired  CharacterService characterService;

  @Test
  public void shouldSaveWithoutException() {
    Character character = characterService.save(new CharacterRequestDTO("Jaspion", "test", "resource"));
    Comic saved = service.save(character.getId(), new ComicRequestDTO("CJaspyon", 10, "desc"));
    assertNotNull(saved);
  }
}
