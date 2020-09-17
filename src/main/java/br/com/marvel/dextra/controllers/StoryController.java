package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.dto.StoryRequestDTO;
import br.com.marvel.dextra.services.StoryService;
import com.marveldextra.couchbase_repository.entity.Story;
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
public class StoryController extends AbstractMarvelController {

  @Autowired  StoryService service;

  @GetMapping("/{characterId}/stories")
  CollectionModel<EntityModel<Story>> all(@PathVariable String characterId) {
    List<EntityModel<Story>> stories = service.findAll(characterId).stream()
        .map(story -> EntityModel.of(story,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(story.getId())).withRel(LINK_TO_STORY)))
        .collect(Collectors.toList());

    return CollectionModel.of(stories, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }

  @PostMapping(value = "/{characterId}/stories", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  EntityModel<ResponseEntity<Story>> save(@PathVariable String characterId,
                                          @RequestBody StoryRequestDTO dto) {

    Story storyAfterSave = service.save(characterId,dto);

    return EntityModel.of(ResponseEntity. status(HttpStatus.CREATED).body(storyAfterSave),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class)
            .getById(storyAfterSave.getId())).withRel(LINK_TO_CHARACTER));
  }
}
