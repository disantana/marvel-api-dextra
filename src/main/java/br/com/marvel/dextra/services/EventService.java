package br.com.marvel.dextra.services;

import br.com.marvel.dextra.dto.EventRequestDTO;
import br.com.marvel.dextra.exceptions.EventExistsException;
import br.com.marvel.dextra.exceptions.RequestException;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.entity.Event;
import com.marveldextra.couchbase_repository.repository.EventRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

  @Autowired  EventRepository repository;

  @Autowired
  CharacterService characterService;

  private ModelMapper modelMapper ;

  public List<Event> findAll(String characterId){
    Character character = characterService.findById(characterId);

    List<Event> all = repository.findEventsByCharacterExists(character);

    return all;
  }

  public Event save(String characterId, EventRequestDTO dto) {
    checkDTO(dto);
    modelMapper = new ModelMapper();
    Event event = modelMapper.map(dto, Event.class);
    List<Event> eventFound = null;

    Character characterFound = characterService.findById(characterId);

    eventFound = repository.findEventsByCharacterExists(characterFound)
        .stream()
        .filter(filter -> filter.getTitle().equals(event.getTitle()))
        .collect(Collectors.toList());

    if (!eventFound.isEmpty()) throw new EventExistsException(eventFound.get(0).getTitle());

    event.setCharacter(characterFound);
    repository.save(event);
    return event;
  }

  private void checkDTO(EventRequestDTO dto) {
    if (StringUtils.isBlank(dto.getTitle())) throw new RequestException("Invalid data as payload.");
    if (StringUtils.contains(dto.getTitle(), "/") || StringUtils.isNumeric(dto.getTitle()))
      throw new RequestException("Invalid data as payload.");
  }
}
