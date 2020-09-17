package br.com.marvel.dextra.exceptions;

public class ComicExistsException extends RuntimeException{

  public ComicExistsException() {
    super("Not be able to save comic with the same title.");
  }

  public ComicExistsException(String name) {
    super("Not be able to save comic with the same title.".concat(name));
  }
}
