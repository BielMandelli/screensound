package br.com.alura.Artistas.Alura.principal;

import br.com.alura.Artistas.Alura.model.Artista;
import br.com.alura.Artistas.Alura.model.Musica;
import br.com.alura.Artistas.Alura.repository.ArtistaRepository;
import br.com.alura.Artistas.Alura.repository.MusicaRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Artista artista = new Artista();
    private Scanner leitor = new Scanner(System.in);
    private ArtistaRepository repositorioArtista;
    private MusicaRepository repositorioMusica;

    public Principal(ArtistaRepository repositorioArtista, MusicaRepository repositorioMusica) {
        this.repositorioArtista = repositorioArtista;
        this.repositorioMusica = repositorioMusica;
    }

    public void exibeMenu(){
        var escolha = -1;
        while (escolha != 0){
        System.out.println("---ESCOLHA UMA DAS OPÇÕES ABAIXO---\n" +
                "1 - Cadastrar Artista \n" +
                "2 - Cadastrar Música \n" +
                "3 - Listar Artistas \n" +
                "4 - Buscar Músicas por Artista \n" +
                "\n" +
                "0 - Sair");

        escolha = leitor.nextInt();
        leitor.nextLine();


        switch (escolha){
            case 1:
                cadastrarArtista();
                break;
            case 2:
                cadastraMusica();
                break;
            case 3:
                listarArtistas();
                break;
            case 4:
                listarMusicaPorArtista();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção Inválida");
                break;
            }
        }
    }

    private void cadastrarArtista() {
        System.out.println("Qual o nome do artista?");
        var nome = leitor.nextLine();
        System.out.println("Escolha o tipo do artista (SOLO, DUPLA, BANDA)");
        var tipo = leitor.nextLine();
        Artista artista = new Artista(nome, tipo);
        repositorioArtista.save(artista);
        System.out.println("Cadatrar outro artista? (S/N)");
        var continuar = leitor.nextLine();

        switch (continuar.toUpperCase()) {
            case "S":
                cadastrarArtista();
                break;
            case "N":
                exibeMenu();
            default:
                System.out.println("Inválido");
                break;
        }
    }

    private void cadastraMusica() {
        System.out.println("Quem é o artista da música?");
        var artistaNome = leitor.nextLine();
        Optional<Artista> nomeArtista = repositorioArtista.findByNomeContainingIgnoreCase(artistaNome);
        if(nomeArtista.isPresent()){

            System.out.println("Qual o nome da música?");
            var nomeMusica = leitor.nextLine();

            System.out.println("A Qual albúm ela pertence? (Deixe vazio se não souber)");
            var nomeAlbum = leitor.nextLine();

            if (nomeAlbum.isEmpty()){
                nomeAlbum = "Desconhecido";
            }

            Musica musica = new Musica(nomeMusica, nomeAlbum, nomeArtista.get());
            repositorioMusica.save(musica);

            System.out.println("Cadatrar outro artista? (S/N)");
            var continuar = leitor.nextLine();

            switch (continuar.toUpperCase()) {
                case "S":
                    cadastraMusica();
                    break;
                case "N":
                    exibeMenu();
                default:
                    System.out.println("Inválido");
                    break;
            }
        }
        else {
            System.out.println("Artista inválido!\n");
        }

    }

    private void listarArtistas() {
        List<Artista> artistas = repositorioArtista.findAll();
        artistas.stream()
                .sorted(Comparator.comparing(Artista::getNome))
                .forEach(System.out::println);
    }

    private void listarMusicaPorArtista() {
        System.out.println("Quem é o artista da música?");
        var artistaNome = leitor.nextLine();
        Optional<Artista> artista = repositorioArtista.findByNomeContainingIgnoreCase(artistaNome);
        if(artista.isPresent()){
            System.out.println("Artista: " + artista.get().getNome());
            System.out.println("Tipo: " + artista.get().getTipo());
            System.out.println("---MÚSICAS---");
            artista.get().getMusicas().stream()
                            .forEach(System.out::println);
        }

    }
}
