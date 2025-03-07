package br.com.alura.Artistas.Alura.repository;

import br.com.alura.Artistas.Alura.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {


    Optional<Artista> findByNomeContainingIgnoreCase(String nome);
}
