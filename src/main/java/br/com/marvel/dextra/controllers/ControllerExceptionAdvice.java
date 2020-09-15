package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.exceptions.CharacterNotFoundException;
import org.springframework.http.HttpStatus;
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
}
