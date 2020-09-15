package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Serie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SerieRepositoryTest extends Assertions {

    @Autowired private SerieRepository repository;
    @Autowired private CharacterRepository characterRepository;

    @Test
    public void shouldReturnSerieSearchedByCharacter(){
        Character character = characterRepository.save(Character.builder()
                .name("Comic's character Teste")
                .description("Character's description")
                .build());

        Serie story = repository.save(Serie.builder()
                .character(character)
                .title("Serie title")
                .description("Serie description")
                .startYear(2010)
                .endYear(2020)
                .build());

        List<Serie> series = repository.findAllByCharacter(character.getName());
        assertFalse(series.isEmpty());
        assertTrue(series.get(0).getDescription().equals("Serie description"));
        assertTrue(series.get(0).getEndYear() == 2020);
    }

}
