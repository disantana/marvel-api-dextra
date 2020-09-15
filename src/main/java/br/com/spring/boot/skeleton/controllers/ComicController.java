package br.com.spring.boot.skeleton.controllers;

import com.marveldextra.couchbase_repository.entity.Comic;
import com.marveldextra.couchbase_repository.repository.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

  @Autowired ComicRepository repository;

  @GetMapping("/{characterId}/comics")
  CollectionModel<EntityModel<Comic>> all(@PathVariable String characterId) {
    List<EntityModel<Comic>> comics = repository.findAll(PageRequest.of(FIRST_PAGE,PAGE_SIZE))
        .map(comic -> EntityModel.of(comic,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(comic.getId())).withRel("comics")))
        .stream().collect(Collectors.toList());

    return CollectionModel.of(comics, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }
}
