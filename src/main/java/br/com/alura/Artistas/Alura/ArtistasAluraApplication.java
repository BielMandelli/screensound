package br.com.alura.Artistas.Alura;

import br.com.alura.Artistas.Alura.principal.Principal;
import br.com.alura.Artistas.Alura.repository.ArtistaRepository;
import br.com.alura.Artistas.Alura.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtistasAluraApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorioArtista;
	@Autowired
	private MusicaRepository repositorioMusica;
	public static void main(String[] args) {
		SpringApplication.run(ArtistasAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioArtista, repositorioMusica);
		principal.exibeMenu();
	}
}
