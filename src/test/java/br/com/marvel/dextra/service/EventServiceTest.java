package br.com.marvel.dextra.service;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.dto.EventRequestDTO;
import br.com.marvel.dextra.services.CharacterService;
import br.com.marvel.dextra.services.EventService;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventServiceTest extends Assertions {

  @Autowired  CharacterService characterService;
  @Autowired  EventService service;

  @Test
  public void shouldSaveWithoutException() {
    Character character = characterService.save(new CharacterRequestDTO("Kummba", "test", "resource"));
    Event saved = service.save(character.getId(), new EventRequestDTO("Party to","event hall",character));
    assertNotNull(saved);
  }
}
