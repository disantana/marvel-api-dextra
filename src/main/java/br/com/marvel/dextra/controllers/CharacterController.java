package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import br.com.marvel.dextra.services.CharacterService;
import com.marveldextra.couchbase_repository.entity.Character;
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
public class CharacterController extends AbstractMarvelController{

  @Autowired CharacterService service;

  @GetMapping("")
  CollectionModel<EntityModel<Character>> all() {
    List<EntityModel<Character>> characters = service.findAll(FIRST_PAGE,PAGE_SIZE)
        .map(character -> EntityModel.of(character,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(character.getId())).withRel(LINK_TO_CHARACTER)))
        .stream().collect(Collectors.toList());

    return CollectionModel.of(characters, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }

  @GetMapping("/{id}")
  EntityModel<Character> getById(@PathVariable String id){
    Character character = service.findById(id);
    return EntityModel.of(character,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(id)).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withRel(LINK_TO_CHARACTER),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).delete(id)).withRel(LINK_TO_CHARACTER),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ComicController.class).all(id)).withRel(LINK_TO_COMIC),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StoryController.class).all(id)).withRel(LINK_TO_STORY),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EventController.class).all(id)).withRel(LINK_TO_EVENT),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SerieController.class).all(id)).withRel(LINK_TO_EVENT)
    );
  }

  @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  EntityModel<ResponseEntity<Character>> save(@RequestBody CharacterRequestDTO dto) {
    Character characterAfterSave = service.save(dto);

    return EntityModel.of(ResponseEntity. status(HttpStatus.CREATED).body(characterAfterSave),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class)
            .getById(characterAfterSave.getId())).withRel(LINK_TO_CHARACTER));
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.OK)
  CollectionModel<Character> delete(@PathVariable String id) {
    Character characterToDelete = service.findById(id);
    return CollectionModel.of(service.findAll(FIRST_PAGE,PAGE_SIZE),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }

}
