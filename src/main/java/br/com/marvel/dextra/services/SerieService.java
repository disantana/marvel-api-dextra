package br.com.marvel.dextra.services;

import br.com.marvel.dextra.dto.SerieResquestDTO;
import br.com.marvel.dextra.exceptions.RequestException;
import br.com.marvel.dextra.exceptions.SerieExistsException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Serie;
import com.marveldextra.couchbase_repository.repository.SerieRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

  @Autowired  SerieRepository repository;

  @Autowired  CharacterService characterService;

  private ModelMapper modelMapper ;

  public List<Serie> findAll(String characterId){
    Character character = characterService.findById(characterId);

    List<Serie> all = repository.findSeriesByCharacterExists(character);

    return all;
  }

  public Serie save(String characterId, SerieResquestDTO dto) {
    checkDTO(dto);
    modelMapper = new ModelMapper();
    Serie serie = modelMapper.map(dto, Serie.class);
    List<Serie> eventFound = null;

    Character characterFound = characterService.findById(characterId);

    eventFound = repository.findSeriesByCharacterExists(characterFound)
        .stream()
        .filter(filter -> filter.getTitle().equals(serie.getTitle()))
        .collect(Collectors.toList());

    if (!eventFound.isEmpty()) throw new SerieExistsException(eventFound.get(0).getTitle());

    serie.setCharacter(characterFound);
    repository.save(serie);
    return serie;

  }

  private void checkDTO(SerieResquestDTO dto) {

    if (StringUtils.isBlank(dto.getTitle())) throw new RequestException("Invalid data as payload.");
    if (StringUtils.contains(dto.getTitle(), "/") || StringUtils.isNumeric(dto.getTitle()))
      throw new RequestException("Invalid data as payload.");
  }
}
