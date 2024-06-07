package ArnaldoWest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Azione {
    private Scanner scanner;
    private List<Giocatore> giocatori;
    private List<Carta> carteInMano;

    public Azione(Scanner scanner, List<Giocatore> giocatori) {
        this.scanner = scanner;
        this.giocatori = giocatori;
       
    }

    public void eseguiAzione(Giocatore giocatoreCorrente) {
        if (giocatoreCorrente.haArma()) {
            equipaggiaArma(giocatoreCorrente, carteInMano);
        }

        if (giocatoreCorrente.haBang()) {
            usaBang(giocatoreCorrente, giocatori);
        }
    }

    public void equipaggiaArma(Giocatore giocatore, List<Carta> carteInMano) {

        // Mostra le armi disponibili
        List<Arma> armiDisponibili = new ArrayList<>();
        for (Carta carta : carteInMano) {
            if (carta instanceof Arma) {
                armiDisponibili.add((Arma) carta);
            }
        }

        // Se non ci sono armi disponibili, esci dal metodo
        if (armiDisponibili.isEmpty()) {
            System.out.println("Non ci sono armi disponibili da equipaggiare.");
            return;
        }

        // Mostra le armi disponibili
        System.out.println("Armi disponibili:");
        for (int i = 0; i < armiDisponibili.size(); i++) {
            System.out.println((i + 1) + ". " + armiDisponibili.get(i).getNome());
        }

        // Chiedi al giocatore di scegliere un'arma da equipaggiare
        System.out.println("Scegli l'arma da equipaggiare (inserisci il numero corrispondente):");
        int sceltaArma = scanner.nextInt();
        scanner.nextLine(); // Consuma il resto della riga

        // Indice dell'arma scelta nella lista delle armi disponibili
        int indiceArmaScelta = sceltaArma - 1;

        // Controlla se l'indice è valido
        if (indiceArmaScelta >= 0 && indiceArmaScelta < armiDisponibili.size()) {
            Arma armaScelta = armiDisponibili.get(indiceArmaScelta);
            giocatore.setCartaEquipaggiata(armaScelta);
            System.out.println(giocatore.getNome() + " ha equipaggiato " + armaScelta.getNome() + ".");
        } else {
            System.out.println("Scelta non valida. Riprova.");
        }
    }
   

    private void usaBang(Giocatore giocatore, List<Giocatore> giocatori) {
        System.out.println(giocatore.getNome() + " ha un Bang! Usa una pallottola su un altro giocatore.");

        // Indice del giocatore corrente nella lista dei giocatori
        int indiceGiocatore = giocatori.indexOf(giocatore);

        // Numero totale di giocatori
        int numeroGiocatori = giocatori.size();

        // Mostra l'elenco dei giocatori che possono essere colpiti
        System.out.println("Elenco dei giocatori che possono essere colpiti:");

        // Se il giocatore è il primo, può colpire l'ultimo giocatore
        if (indiceGiocatore == 0) {
            Giocatore ultimoGiocatore = giocatori.get(numeroGiocatori - 1);
            System.out.println(numeroGiocatori + ". " + ultimoGiocatore.getNome());
        }

        // Se il giocatore è l'ultimo, può colpire il primo giocatore
        if (indiceGiocatore == numeroGiocatori - 1) {
            Giocatore primoGiocatore = giocatori.get(0);
            System.out.println("1. " + primoGiocatore.getNome());
        }

        // Colpisce il giocatore successivo in senso orario
        int indiceGiocatoreSuccessivo = (indiceGiocatore + 1) % numeroGiocatori;
        Giocatore giocatoreSuccessivo = giocatori.get(indiceGiocatoreSuccessivo);
        System.out.println((indiceGiocatoreSuccessivo + 1) + ". " + giocatoreSuccessivo.getNome());

        // Se non è il primo giocatore, colpisce il giocatore precedente in senso orario
        if (indiceGiocatore != 0) {
            int indiceGiocatorePrecedente = (indiceGiocatore - 1 + numeroGiocatori) % numeroGiocatori;
            Giocatore giocatorePrecedente = giocatori.get(indiceGiocatorePrecedente);
            System.out.println((indiceGiocatorePrecedente + 1) + ". " + giocatorePrecedente.getNome());
        }

        // Chiedi al giocatore di scegliere un giocatore da colpire
        System.out.println("Scegli un giocatore da colpire (inserisci il numero corrispondente):");
        int sceltaGiocatore = scanner.nextInt();
        scanner.nextLine(); // Consuma il resto della riga

        // Indice del giocatore scelto nella lista dei giocatori
        int indiceGiocatoreScelto = (sceltaGiocatore - 1 + numeroGiocatori) % numeroGiocatori;
        Giocatore giocatoreColpito = giocatori.get(indiceGiocatoreScelto);


        // Controlla se il giocatore colpito ha un "Miss" nelle carte in mano
        if (giocatoreColpito.haMiss()) {
            // Chiedi al giocatore colpito se vuole scartare un "Miss" per evitare di perdere punti vita
            System.out.println(giocatoreColpito.getNome() + " ha un Miss. Vuoi scartarlo per evitare di perdere punti vita? (s/n)");
            String risposta = scanner.nextLine().toLowerCase();
            if (risposta.equals("s")) {
                // Il giocatore sceglie di scartare un "Miss", quindi non perde punti vita
  
                giocatoreColpito.scartaMiss(); // Rimuovi il "Miss" dalle carte in mano del giocatore colpito
                System.out.println("Hai scartato un Miss. Non perdi punti vita.");
                return; // Esci dal metodo
            } else {
            	giocatoreColpito.togliePf(1, giocatori);
                System.out.println(giocatore.getNome() + " ha colpito " + giocatoreColpito.getNome() + "!");
            }
        }

        // Riduci i punti ferita del giocatore colpito
        giocatoreColpito.togliePf(1, giocatori);
        System.out.println(giocatore.getNome() + " ha colpito " + giocatoreColpito.getNome() + "!");

        // Se i punti ferita del giocatore colpito sono <= 0, rimuovilo dalla lista dei giocatori e visualizza il suo ruolo
        if (giocatoreColpito.getPf() <= 0) {
            System.out.println(giocatoreColpito.getNome() + " è morto! Il suo ruolo era: " + giocatoreColpito.getRuolo());
            
         // Verifica se il giocatore deceduto è lo sceriffo
            if (giocatoreColpito.getRuolo().equals("Sceriffo")) {
                // Verifica se il giocatore che ha sparato è il rinnegato
                if (giocatore.getRuolo().equals("Rinnegato")) {
                    Finale.gestisciFinale("Rinnegato");
                } else {
                    Finale.gestisciFinale("Fuorilegge");
                }
            }
            
            giocatori.remove(giocatoreColpito);
        }
        
    }




}
