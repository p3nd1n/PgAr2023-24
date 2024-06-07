package ArnaldoWest;

import java.util.List;
import java.util.Scanner;

public class ControlloreCarte {

    private Scanner scanner;
    private MazzoDiCarte mazzo;

    public ControlloreCarte(Scanner scanner, MazzoDiCarte mazzo) {
        this.scanner = scanner;
        this.mazzo = mazzo;
    }

    public void gestisciCarteInEccesso(Giocatore giocatoreCorrente) {
        int numeroCarteMassimo = giocatoreCorrente.getPf();
        int numeroCarteInMano = giocatoreCorrente.getCarteInMano().size();
        

        if (numeroCarteInMano > numeroCarteMassimo) {
        	System.out.println(numeroCarteMassimo + " " + numeroCarteInMano);
            System.out.println("Hai troppe carte in mano. Devi scartarne alcune.");
            int carteDaScartare = numeroCarteInMano - numeroCarteMassimo;

            // Mostra le carte attualmente in mano al giocatore
            List<Carta> carteInMano = giocatoreCorrente.getCarteInMano();
            for (int i = 0; i < carteInMano.size(); i++) {
                System.out.println((i + 1) + ". " + carteInMano.get(i).getTipo());
            }
            // Chiedi al giocatore di scegliere i numeri delle carte da scartare
            System.out.println("Inserisci i numeri delle carte da scartare separati da spazio:");
            String[] numeriDaScartareString = scanner.nextLine().split(" ");

            // Converti i numeri da stringhe a interi e rimuovi le carte corrispondenti dalla mano del giocatore
            for (String numeroString : numeriDaScartareString) {
                int numero = Integer.parseInt(numeroString);
                // Verifica che il numero sia valido
                if (numero >= 1 && numero <= carteInMano.size()) {
                    // Rimuovi la carta dalla mano del giocatore
                    Carta cartaScartata = carteInMano.remove(numero - 1);
                    // Aggiungi la carta allo scarto
                    mazzo.scartaCarta(cartaScartata);
                } else {
                    System.out.println("Numero non valido. Riprova.");
                }
            }
        }
    }
}
