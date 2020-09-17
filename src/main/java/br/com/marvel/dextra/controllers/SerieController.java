package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.dto.SerieResquestDTO;
import br.com.marvel.dextra.services.SerieService;
import com.marveldextra.couchbase_repository.entity.Serie;
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
public class SerieController extends AbstractMarvelController {

  @Autowired
  SerieService service;

  @GetMapping(value = "/{characterId}/series", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.OK)
  CollectionModel<EntityModel<Serie>> all(@PathVariable String characterId) {
    List<EntityModel<Serie>> series = service.findAll(characterId).stream()
        .map(serie -> EntityModel.of(serie,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).getById(serie.getId())).withRel(LINK_TO_SERIE)))
        .collect(Collectors.toList());

    return CollectionModel.of(series, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class).all()).withSelfRel());

  }

  @PostMapping(value = "/{characterId}/series", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  EntityModel<ResponseEntity<Serie>> save(@PathVariable String characterId,
                                          @RequestBody SerieResquestDTO dto) {

    Serie serieAfterSave = service.save(characterId,dto);

    return EntityModel.of(ResponseEntity. status(HttpStatus.CREATED).body(serieAfterSave),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class)
            .getById(serieAfterSave.getId())).withRel(LINK_TO_CHARACTER));
  }
}
