package ArnaldoWest;
import java.util.List;
import java.util.Scanner;

public class GestoreTurno {
    private List<Giocatore> giocatori;
    private int indiceGiocatoreCorrente;
    private Scanner scanner;
    private MazzoDiCarte mazzo;
    private ControlloreCarte controlloreCarte;
    private Azione azione;

    public GestoreTurno(List<Giocatore> giocatori, Scanner scanner, MazzoDiCarte mazzo) {
        this.giocatori = giocatori;
        this.indiceGiocatoreCorrente = 0; // Parte il giocatore con indice 0
        this.scanner = scanner;
        this.mazzo = mazzo;
        this.controlloreCarte = new ControlloreCarte(scanner, mazzo);
        this.azione = new Azione(scanner, giocatori);
    }
    public Giocatore getGiocatoreCorrente() {
        return giocatori.get(indiceGiocatoreCorrente);
    }
    public void passaTurno() {
        indiceGiocatoreCorrente = (indiceGiocatoreCorrente + 1) % giocatori.size();
    }
    private static boolean isGameOver = false;

    public static boolean isGameOver() {
        return isGameOver;
    }

    public static void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }
    public void eseguiTurni(List<Giocatore> giocatori) {
        while (!isGameOver() && !giocatori.isEmpty()) {
            for (Giocatore giocatore : giocatori) {
                if (isGameOver()) {
                    break;
                }
                eseguiTurno();
            }
        }
    }

    public void eseguiTurno() {
        Giocatore giocatoreCorrente = getGiocatoreCorrente();
        System.out.println("Nome: " + giocatoreCorrente.getNome() + ", Ruolo: " + giocatoreCorrente.getRuolo());
        // Pesca due carte e aggiungile alla mano del giocatore corrente
        for (int i = 0; i < 2; i++) {
            Carta carta = mazzo.pescaCarta();
            if (carta != null) {
                giocatoreCorrente.getCarteInMano().add(carta);
            }
        }
     // Visualizza le carte in mano del giocatore corrente
        System.out.println("Carte in mano di " + giocatoreCorrente.getNome() + ":");
        for (Carta carta : giocatoreCorrente.getCarteInMano()) {
            System.out.print("\t" + carta.getTipo());
            if (carta instanceof Arma) {
                Arma arma = (Arma) carta;
                System.out.print("\t" + arma.getNome());
                System.out.print(" - Distanza: " + arma.getDistanza());
            }
        }
        System.out.println(" ");
        azione.equipaggiaArma(giocatoreCorrente, giocatoreCorrente.getCarteInMano());
		azione.eseguiAzione(giocatoreCorrente);
        controlloreCarte.gestisciCarteInEccesso(giocatoreCorrente);
        passaTurno();
        
    }
}


