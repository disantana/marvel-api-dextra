package br.com.marvel.dextra.controllers;

abstract class AbstractMarvelController {
  protected static final int PAGE_SIZE = 30;
  protected static final int FIRST_PAGE = 0;
  protected static final String BASE_PATH = "/v1/public/characters";
  protected static final String LINK_TO_CHARACTER = "characters";
  protected static final String LINK_TO_EVENT = "events";
  protected static final String LINK_TO_STORY = "stories";
  protected static final String LINK_TO_COMIC = "comics";
  protected static final String LINK_TO_SERIE = "series";
}
