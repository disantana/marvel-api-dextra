package br.com.marvel.dextra.service;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.dto.SerieResquestDTO;
import br.com.marvel.dextra.services.CharacterService;
import br.com.marvel.dextra.services.SerieService;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SerieServiceTest extends Assertions {

  @Autowired CharacterService characterService;
  @Autowired SerieService service;

  @Test
  public void shouldSaveWithoutException() {
    Character character = characterService
        .save(new CharacterRequestDTO("Kummba Fix", "testing ...", "resource"));

    Serie saved = service.save(character.getId(),
        new SerieResquestDTO("Series Fix", 2010,2020,"descfix",character));
    assertNotNull(saved);
  }
}
