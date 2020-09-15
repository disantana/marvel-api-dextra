package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.services.StoryService;
import com.marveldextra.couchbase_repository.entity.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(AbstractMarvelController.BASE_PATH)
public class StoryController extends AbstractMarvelController {

  @Autowired  StoryService service;

  @GetMapping("/{characterId}/stories")
  CollectionModel<EntityModel<Story>> all(@PathVariable String characterId) {
    List<EntityModel<Story>> stories = service.findAll(characterId).stream()
        .map(story -> EntityModel.of(story,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(story.getId())).withRel(LINK_TO_COMIC)))
        .collect(Collectors.toList());

    return CollectionModel.of(stories, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }
}
