package br.com.marvel.dextra.services;

import br.com.marvel.dextra.exceptions.CharacterNotFoundException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Comic;
import com.marveldextra.couchbase_repository.repository.CharacterRepository;
import com.marveldextra.couchbase_repository.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicService {

  @Autowired  ComicRepository repository;
  @Autowired  CharacterRepository characterRepository;

  public List<Comic> findAll(String characterId){
    Character character = characterRepository.findById(characterId)
        .orElseThrow(() -> new CharacterNotFoundException("Character not Found"));

    List<Comic> all = repository.findComicsByCharacterExists(character);

    return all;
  }
}
