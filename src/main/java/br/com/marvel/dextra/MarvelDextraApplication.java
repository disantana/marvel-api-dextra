package br.com.marvel.dextra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.marveldextra.couchbase_repository",
        "br.com.marvel.dextra"})
public class MarvelDextraApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarvelDextraApplication.class, args);
  }

}
