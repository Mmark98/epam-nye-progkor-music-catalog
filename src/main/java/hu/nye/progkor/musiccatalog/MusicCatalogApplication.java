package hu.nye.progkor.musiccatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class to provide an entry point for the music catalog webservice.
 */
@SpringBootApplication
public class MusicCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicCatalogApplication.class, args);
	}
}
