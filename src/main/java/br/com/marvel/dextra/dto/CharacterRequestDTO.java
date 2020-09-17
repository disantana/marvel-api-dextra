package br.com.marvel.dextra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class CharacterRequestDTO {

  private String name;

  private String description;

  private String resourceURI;
}
