package br.com.marvel.dextra.exceptions;

public class SerieExistsException extends RuntimeException{

  public SerieExistsException() {
    super("Not be able to save serie with the same title.");
  }

  public SerieExistsException(String name) {
    super("Not be able to save serie with the same title.".concat(name));
  }
}
