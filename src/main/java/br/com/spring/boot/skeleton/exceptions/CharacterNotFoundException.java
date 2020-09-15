package br.com.spring.boot.skeleton.exceptions;

public class CharacterNotFoundException extends RuntimeException {

  public CharacterNotFoundException(String message) {
    super(message);
  }
}
