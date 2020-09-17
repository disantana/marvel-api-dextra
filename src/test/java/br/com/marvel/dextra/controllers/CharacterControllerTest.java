package br.com.marvel.dextra.controllers;

import br.com.marvel.dextra.dto.CharacterRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  @Autowired
  private ObjectMapper objectMapper;

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

  @Test
  public void shouldReturnCreated() throws Exception{
    mvc.perform(MockMvcRequestBuilders.post(AbstractMarvelController.BASE_PATH)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(new CharacterRequestDTO("Hulk", "description",
            "hulk.com"))))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  public void shouldNotDelete() throws Exception{
    mvc.perform(MockMvcRequestBuilders.delete(AbstractMarvelController.BASE_PATH.concat("/abc"))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().is4xxClientError());
  }

}
