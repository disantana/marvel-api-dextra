package br.com.spring.boot.skeleton.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

  @RequestMapping("/")
  public String index() {

    return "Hello Marvel Consumers";
  }
}
