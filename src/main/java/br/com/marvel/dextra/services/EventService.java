package br.com.marvel.dextra.services;

import br.com.marvel.dextra.exceptions.CharacterNotFoundException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Event;
import com.marveldextra.couchbase_repository.repository.CharacterRepository;
import com.marveldextra.couchbase_repository.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

  @Autowired  EventRepository repository;

  @Autowired
  CharacterRepository characterRepository;

  public List<Event> findAll(String characterId){
    Character character = characterRepository.findById(characterId)
        .orElseThrow(() -> new CharacterNotFoundException("Character not Found"));

    List<Event> all = repository.findEventsByCharacterExists(character);

    return all;
  }
}
