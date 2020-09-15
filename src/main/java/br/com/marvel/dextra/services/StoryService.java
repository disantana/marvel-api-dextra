package br.com.marvel.dextra.services;

import br.com.marvel.dextra.exceptions.CharacterNotFoundException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Story;
import com.marveldextra.couchbase_repository.repository.CharacterRepository;
import com.marveldextra.couchbase_repository.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {
  @Autowired
  StoryRepository repository;
  @Autowired
  CharacterRepository characterRepository;

  public List<Story> findAll(String characterId){
    Character character = characterRepository.findById(characterId)
        .orElseThrow(() -> new CharacterNotFoundException("Character not Found"));

    List<Story> all = repository.findStoriesByCharacterExists(character);

    return all;
  }
}
