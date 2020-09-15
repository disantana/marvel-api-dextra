package br.com.spring.boot.skeleton.controllers;

import br.com.spring.boot.skeleton.services.CharacterService;
import com.marveldextra.couchbase_repository.entity.Character;
import com.marveldextra.couchbase_repository.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AbstractMarvelController.BASE_PATH)
public class CharacterController extends AbstractMarvelController{

  @Autowired
  CharacterRepository repository;

  @Autowired CharacterService service;

  @GetMapping("")
  CollectionModel<EntityModel<Character>> all() {
    List<EntityModel<Character>> characters = service.findAll(FIRST_PAGE,PAGE_SIZE)
        .map(character -> EntityModel.of(character,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(character.getId())).withRel("characters")))
        .stream().collect(Collectors.toList());

    return CollectionModel.of(characters, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }

  @GetMapping("/{id}")
  EntityModel<Character> getById(@PathVariable String id){
    Character character = service.findById(id);
    return EntityModel.of(character,
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(id)).withSelfRel(),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withRel("employees"));
  }

}
