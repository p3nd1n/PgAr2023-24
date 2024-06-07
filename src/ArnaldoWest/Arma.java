package ArnaldoWest;

public class Arma extends Carta {
    private String nome;
    private int distanza;

    public Arma(String nome, int distanza) {
        super("arma");
        this.nome = nome;
        this.distanza = distanza;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }
}
