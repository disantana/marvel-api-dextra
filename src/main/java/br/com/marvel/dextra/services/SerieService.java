package br.com.marvel.dextra.services;

import br.com.marvel.dextra.exceptions.CharacterNotFoundException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Serie;
import com.marveldextra.couchbase_repository.repository.CharacterRepository;
import com.marveldextra.couchbase_repository.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

  @Autowired  SerieRepository repository;

  @Autowired  CharacterRepository characterRepository;

  public List<Serie> findAll(String characterId){
    Character character = characterRepository.findById(characterId)
        .orElseThrow(() -> new CharacterNotFoundException("Character not Found"));

    List<Serie> all = repository.findSeriesByCharacterExists(character);

    return all;
  }
}
