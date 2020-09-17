package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.dto.ComicRequestDTO;
import br.com.marvel.dextra.services.ComicService;
import com.marveldextra.couchbase_repository.entity.Comic;
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

  @PostMapping(value = "/{characterId}/comics", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  EntityModel<ResponseEntity<Comic>> save(@PathVariable String characterId,
                                          @RequestBody ComicRequestDTO dto) {

    Comic comicAfterSave = service.save(characterId,dto);

    return EntityModel.of(ResponseEntity. status(HttpStatus.CREATED).body(comicAfterSave),
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CharacterController.class)
            .getById(comicAfterSave.getId())).withRel(LINK_TO_CHARACTER));
  }
}
