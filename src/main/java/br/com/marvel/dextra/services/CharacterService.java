package br.com.marvel.dextra.services;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.exceptions.CharacterExistsException;
import br.com.marvel.dextra.exceptions.CharacterNotFoundException;
import br.com.marvel.dextra.exceptions.RequestException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.repository.CharacterRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class CharacterService {

  @Autowired  CharacterRepository repository;
  ModelMapper modelMapper ;
  public Character findById(String id) {
    return repository.findById(id).orElseThrow(() -> new CharacterNotFoundException("Character not Found"));
  }

  public Page<Character> findAll(int page, int pageSize){
    return repository.findAll(PageRequest.of(page,pageSize));
  }

  public Character save(CharacterRequestDTO dto) {
      checkDTO(dto);
      modelMapper = new ModelMapper();
      Character character = modelMapper.map(dto, Character.class);
      Optional<Character> characterFound = null;

      characterFound = repository.findCharacterByNameExists(character.getName())
          .stream()
          .findFirst();
      if (characterFound.isPresent()) throw new CharacterExistsException(characterFound.get().getName());

      repository.save(character);
      return character;
  }

  private void checkDTO(CharacterRequestDTO dto) {
    if (StringUtils.isBlank(dto.getName()) ||
        StringUtils.isBlank(dto.getDescription()) ||
        StringUtils.isBlank(dto.getResourceURI())) throw new RequestException("Invalid data as payload.");

  }

}
