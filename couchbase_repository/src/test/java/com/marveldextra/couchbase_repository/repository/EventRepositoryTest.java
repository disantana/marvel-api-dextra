package com.marveldextra.couchbase_repository.repository;

import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EventRepositoryTest extends Assertions {

    @Autowired private EventRepository repository;
    @Autowired private CharacterRepository characterRepository;

    @Test
    public void shouldReturnEventSearchedByCharacter(){
        Character character = characterRepository.save(Character.builder()
                .name("Comic's character Teste")
                .description("Character's description")
                .build());

        Event event = repository.save(Event.builder()
                .character(character)
                .description("Event description")
                .title("Event title")
                .build());
        List<Event> events= repository.findEventsByCharacterExists(character);
        assertFalse(events.isEmpty());
        assertTrue(events.get(0).getDescription().equals("Event description"));
    }
}
