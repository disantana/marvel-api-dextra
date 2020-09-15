package br.com.spring.boot.skeleton.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CharacterControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void shouldReturnOKToBasePath() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get(AbstractMarvelController.BASE_PATH.concat("/")).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldReturnNotFound() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get(AbstractMarvelController.BASE_PATH.concat("/abc")).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Character not Found"));
  }
}
