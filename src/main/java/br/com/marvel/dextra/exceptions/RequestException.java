package br.com.marvel.dextra.exceptions;

public class RequestException extends RuntimeException {

  public RequestException(String message) {
    super("Error from request. ".concat(message));
  }

}
