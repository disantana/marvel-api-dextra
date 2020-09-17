package br.com.marvel.dextra.service;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.services.CharacterService;
import com.marveldextra.couchbase_repository.entity.Character;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CharacterServiceTest extends Assertions {

  @Autowired  CharacterService service;

  @Test
  public void shouldSaveWithoutException() {
    CharacterRequestDTO dto = new CharacterRequestDTO("Character Service Test",
        "description", "resource");
    Character response = service.save(dto);
    assertNotNull(response);
    assertEquals(dto.getName(), response.getName());
  }

}
