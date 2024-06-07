package ArnaldoWest;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        XMLReader xmlReader = new XMLReader();
        Scanner scanner = new Scanner(System.in);
        int numeroGiocatori;
        do {
            System.out.print("Inserisci il numero di giocatori (da 4 a 7): ");
            numeroGiocatori = scanner.nextInt();
            scanner.nextLine(); // Consuma il resto della riga
        } while (numeroGiocatori < 4 || numeroGiocatori > 7);
        
        // Ottieni la lista dei giocatori
        List<Giocatore> giocatori = xmlReader.assegnaPersonaggi(numeroGiocatori);
        giocatori = Inizializzazione.assegnaRuoli(giocatori);

        // Stampa i giocatori con i ruoli assegnati
        for (Giocatore giocatore : giocatori) {
        	if (giocatore.getRuolo()=="Sceriffo") {
        		System.out.println("Nome: " + giocatore.getNome() + ", Ruolo: " + giocatore.getRuolo() + "\n");
        	}
        }
        // Crea un gestore del turno
        MazzoDiCarte mazzoDiCarte = new MazzoDiCarte();
        GestoreTurno gestoreTurno = new GestoreTurno(giocatori, scanner, mazzoDiCarte);

        // Loop del giro
        boolean giocoFinito = false;
        while (!giocoFinito) {
        
            // Esegui il turno del giocatore corrente
            gestoreTurno.eseguiTurno();
            giocoFinito = GestoreTurno.isGameOver();
            
        }
        
        // Chiudi lo scanner
        scanner.close();
    }
}
