package br.com.marvel.dextra.services;

import br.com.marvel.dextra.dto.StoryRequestDTO;
import br.com.marvel.dextra.exceptions.RequestException;
import br.com.marvel.dextra.exceptions.SerieExistsException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Story;
import com.marveldextra.couchbase_repository.repository.StoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoryService {

  @Autowired  StoryRepository repository;
  @Autowired  CharacterService characterService;

  private ModelMapper modelMapper ;

  public List<Story> findAll(String characterId){
    Character character = characterService.findById(characterId);

    List<Story> all = repository.findStoriesByCharacterExists(character);

    return all;
  }

  public Story save(String characterId, StoryRequestDTO dto) {
    checkDTO(dto);
    modelMapper = new ModelMapper();
    Story story = modelMapper.map(dto, Story.class);
    List<Story> storyFound = null;

    Character characterFound = characterService.findById(characterId);

    storyFound = repository.findStoriesByCharacterExists(characterFound)
        .stream()
        .filter(filter -> filter.getTitle().equals(story.getTitle()))
        .collect(Collectors.toList());

    if (!storyFound.isEmpty()) throw new SerieExistsException(storyFound.get(0).getTitle());

    story.setCharacter(characterFound);
    repository.save(story);
    return story;

  }

  private void checkDTO(StoryRequestDTO dto) {

    if (StringUtils.isBlank(dto.getTitle())) throw new RequestException("Invalid data as payload.");
    if (StringUtils.contains(dto.getTitle(), "/") || StringUtils.isNumeric(dto.getTitle()))
      throw new RequestException("Invalid data as payload.");
  }

}
