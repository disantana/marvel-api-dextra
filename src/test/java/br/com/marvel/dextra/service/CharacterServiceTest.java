package br.com.marvel.dextra.service;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.dto.CharacterResponseDTO;
import br.com.marvel.dextra.dto.DtoToBeDeleted;
import br.com.marvel.dextra.services.CharacterService;
import com.marveldextra.couchbase_repository.entity.TesteToBeDeleted;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CharacterServiceTest extends Assertions {

  @Autowired  CharacterService service;

  @Test
  public void shouldSaveWithoutException(){

    CharacterRequestDTO dto = new CharacterRequestDTO("Character Service Test",
        "description", "resource");
    CharacterResponseDTO response = service.save(dto);
    assertNotNull(response);
    assertEquals(dto.getName(), response.getName());
  }
  @Test
  public void teste(){
    DtoToBeDeleted dto = new DtoToBeDeleted("Name teste","desc","resou");
    TesteToBeDeleted result = service.teste(dto);
    assertNotNull(result);
  }
}
