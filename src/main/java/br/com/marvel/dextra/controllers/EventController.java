package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.dto.EventRequestDTO;
import br.com.marvel.dextra.services.EventService;
import com.marveldextra.couchbase_repository.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AbstractMarvelController.BASE_PATH)
public class EventController extends AbstractMarvelController {

  @Autowired  EventService service;

  @GetMapping("/{characterId}/events")
  CollectionModel<EntityModel<Event>> all(@PathVariable String characterId) {
    List<EntityModel<Event>> events = service.findAll(characterId).stream()
        .map(event -> EntityModel.of(event,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(event.getId())).withRel(LINK_TO_EVENT)))
        .collect(Collectors.toList());

    return CollectionModel.of(events, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }

  @PostMapping(value = "/{characterId}/events", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  EntityModel<ResponseEntity<Event>> save(@PathVariable String characterId,
                                          @RequestBody EventRequestDTO dto) {

    Event eventAfterSave = service.save(characterId,dto);

    return EntityModel.of(ResponseEntity. status(HttpStatus.CREATED).body(eventAfterSave),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class)
            .getById(eventAfterSave.getId())).withRel(LINK_TO_CHARACTER));
  }
}
