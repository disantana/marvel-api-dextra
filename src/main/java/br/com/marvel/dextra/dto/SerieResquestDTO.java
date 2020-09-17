package br.com.marvel.dextra.dto;

import com.marveldextra.couchbase_repository.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SerieResquestDTO {


  private String title;

  private int startYear;

  private int endYear;

  private String description;

  private Character character;
}
