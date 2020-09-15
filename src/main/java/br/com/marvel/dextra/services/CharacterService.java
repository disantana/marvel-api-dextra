package br.com.marvel.dextra.services;

import br.com.marvel.dextra.exceptions.CharacterNotFoundException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

  @Autowired  CharacterRepository repository;

  public Character findById(String id) {
    return repository.findById(id).orElseThrow(() -> new CharacterNotFoundException("Character not Found"));
  }

  public Page<Character> findAll(int page, int pageSize){
    return repository.findAll(PageRequest.of(page,pageSize));
  }

}
