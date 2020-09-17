package br.com.marvel.dextra.dto;

import com.marveldextra.couchbase_repository.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EventRequestDTO {

  private String title;

  private String description;

  private Character character;

}
