package br.com.alura.Artistas.Alura.model;

public enum Tipo {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipoArtista;

    Tipo(String tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public static Tipo fromString(String text){
        for (Tipo tipo : Tipo.values()) {
            if(tipo.tipoArtista.equalsIgnoreCase(text)){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida");
    }
}
