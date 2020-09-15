package br.com.marvel.dextra.exceptions;

public class CharacterNotFoundException extends RuntimeException {

  public CharacterNotFoundException(String message) {
    super(message);
  }
}
