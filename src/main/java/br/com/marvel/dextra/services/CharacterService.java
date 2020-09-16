package br.com.marvel.dextra.services;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.dto.CharacterResponseDTO;
import br.com.marvel.dextra.exceptions.CharacterNotFoundException;
import br.com.marvel.dextra.exceptions.MarvelGenericException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.repository.CharacterRepository;
import lombok.extern.log4j.Log4j2;
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

  public CharacterResponseDTO save(CharacterRequestDTO dto) {
    try {
      modelMapper = new ModelMapper();
      //TODO VALIDAR INPUT
      Character character = modelMapper.map(dto, Character.CharacterBuilder.class).build();
      repository.save(character);
      Optional<Character> characterSaved = repository.findCharacterByNameExists(character.getName()).stream().findFirst();
      return modelMapper.map(characterSaved, CharacterResponseDTO.CharacterResponseDTOBuilder.class).build();

    } catch (Exception e) {
      log.atFatal().log("Can not save entity", e);
      throw new MarvelGenericException("Can not save entity.");
    }
  }

}
