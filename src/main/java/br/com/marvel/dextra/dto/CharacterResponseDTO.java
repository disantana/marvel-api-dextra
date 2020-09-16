package br.com.marvel.dextra.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
@Builder
@Value
@Data
public class CharacterResponseDTO {

   private String id;

   private String name;

   private String description;

   private String resourceURI;
}
