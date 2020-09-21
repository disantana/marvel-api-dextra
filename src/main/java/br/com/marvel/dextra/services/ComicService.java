package br.com.marvel.dextra.services;

import br.com.marvel.dextra.dto.ComicRequestDTO;
import br.com.marvel.dextra.exceptions.ComicExistsException;
import br.com.marvel.dextra.exceptions.RequestException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Comic;
import com.marveldextra.couchbase_repository.repository.ComicRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComicService {

  @Autowired  ComicRepository repository;
  //@Autowired  CharacterRepository characterRepository;
  @Autowired  CharacterService characterService;
  private ModelMapper modelMapper ;

  public List<Comic> findAll(String characterId){
    Character character = characterService.findById(characterId);
    List<Comic> all = repository.findComicsByCharacterExists(character);
    return all;
  }

  public Comic save(String characterId, ComicRequestDTO dto) {
    checkDTO(dto);
    modelMapper = new ModelMapper();
    Comic comic = modelMapper.map(dto, Comic.class);
    List<Comic> comicFound = null;

    Character characterFound = characterService.findById(characterId);

    comicFound = repository.findComicsByCharacterExists(characterFound)
        .stream()
        .filter(filter -> filter.getTitle().equals(comic.getTitle()))
        .collect(Collectors.toList());

    if (!comicFound.isEmpty()) throw new ComicExistsException(comicFound.get(0).getTitle());

    comic.setCharacter(characterFound);
    repository.save(comic);
    return comic;
  }

  private void checkDTO(ComicRequestDTO dto) {

    if (StringUtils.isBlank(dto.getTitle())) throw new RequestException("Invalid data as payload.");
    if (StringUtils.contains(dto.getTitle(), "/") || StringUtils.isNumeric(dto.getTitle()))
      throw new RequestException("Invalid data as payload.");
  }
}
