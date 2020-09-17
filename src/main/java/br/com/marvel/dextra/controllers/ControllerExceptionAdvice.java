package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionAdvice {

  @ResponseBody
  @ExceptionHandler(CharacterNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String employeeNotFoundHandler(CharacterNotFoundException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(MarvelGenericException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  void genericError(MarvelGenericException ex) {

  }

  @ResponseBody
  @ExceptionHandler(RequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity.BodyBuilder requestError(RequestException ex) {
    return ResponseEntity.badRequest();
  }

  @ResponseBody
  @ExceptionHandler(CharacterExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<String> requestError(CharacterExistsException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(ComicExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<String> requestError(ComicExistsException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(EventExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<String> requestError(EventExistsException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(SerieExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<String> requestError(SerieExistsException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

}
