package br.com.marvel.dextra.exceptions;

public class CharacterExistsException extends RuntimeException{

  public CharacterExistsException(String name){
    super("Not be able to save character with the same name: ".concat(name));
  }
}
