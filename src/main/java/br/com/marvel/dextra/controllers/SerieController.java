package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.services.SerieService;
import com.marveldextra.couchbase_repository.entity.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(AbstractMarvelController.BASE_PATH)
public class SerieController extends AbstractMarvelController {

  @Autowired
  SerieService service;

  CollectionModel<EntityModel<Serie>> all(@PathVariable String characterId) {
    List<EntityModel<Serie>> series = service.findAll(characterId).stream()
        .map(serie -> EntityModel.of(serie,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(serie.getId())).withRel(LINK_TO_SERIE)))
        .collect(Collectors.toList());

    return CollectionModel.of(series, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }
}
