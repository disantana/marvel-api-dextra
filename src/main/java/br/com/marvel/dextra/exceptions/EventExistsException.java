package br.com.marvel.dextra.exceptions;

public class EventExistsException extends RuntimeException{

  public EventExistsException() {
    super("Not be able to save event with the same title.");
  }

  public EventExistsException(String name) {
    super("Not be able to save event with the same title.".concat(name));
  }
}
