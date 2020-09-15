package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.services.ComicService;
import com.marveldextra.couchbase_repository.entity.Comic;
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
public class ComicController extends AbstractMarvelController{

  @Autowired  ComicService service;

  @GetMapping("/{characterId}/comics")
  CollectionModel<EntityModel<Comic>> all(@PathVariable String characterId) {
    List<EntityModel<Comic>> comics = service.findAll(characterId).stream()
        .map(comic -> EntityModel.of(comic,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(comic.getId())).withRel(LINK_TO_COMIC)))
        .collect(Collectors.toList());

    return CollectionModel.of(comics, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }
}
