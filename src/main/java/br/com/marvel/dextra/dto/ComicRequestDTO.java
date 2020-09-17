package br.com.marvel.dextra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ComicRequestDTO {

  private String title;

  private int pageCount;

  private String description;
}
