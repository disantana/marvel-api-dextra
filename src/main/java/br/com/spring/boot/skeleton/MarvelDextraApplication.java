package br.com.spring.boot.skeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.marveldextra.couchbase_repository",
        "br.com.spring.boot.skeleton"})
public class MarvelDextraApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarvelDextraApplication.class, args);
  }

}
