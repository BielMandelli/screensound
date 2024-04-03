package br.com.alura.Artistas.Alura.repository;

import br.com.alura.Artistas.Alura.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
