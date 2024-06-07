package ArnaldoWest;

import java.util.ArrayList;
import java.util.List;

public class Giocatore {
    private int pf;
    private String nome;
    private String ruolo;
    private String personaggio;
    private String descrizionePersonaggio; // Aggiunta la descrizione del personaggio
    private List<Carta> carteInMano;
    private Arma cartaEquipaggiata;
    
    public Giocatore(int pf, String nome, String ruolo, String personaggio, String descrizionePersonaggio, List<Carta> carteInMano, Arma cartaEquipaggiata) {
        this.pf = pf;
        this.nome = nome;
        this.ruolo = ruolo;
        this.personaggio = personaggio;
        this.descrizionePersonaggio = descrizionePersonaggio;
        this.carteInMano = carteInMano != null ? carteInMano : new ArrayList<>(); // Inizializzazione della lista
        this.cartaEquipaggiata = cartaEquipaggiata;
    }

    public int getPf() {
        return pf;
    }

    public void setPf(int pf) {
        this.pf = pf;
    }
    public boolean togliePf(int puntiFerita, List<Giocatore> giocatori) {
        if (puntiFerita >= 0) {
            pf -= puntiFerita;
            if (pf <= 0) {
                pf = 0; // Assicura che i punti ferita non diventino negativi o nulli
                System.out.println("Il giocatore " + nome + " è stato eliminato! Ruolo: " + ruolo);
                giocatori.remove(this); // Rimuovi il giocatore dalla lista dei giocatori
                return true; // Il giocatore è morto
            }
        }
        return false; // Il giocatore è ancora vivo
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getPersonaggio() {
        return personaggio;
    }

    public void setPersonaggio(String personaggio) {
        this.personaggio = personaggio;
    }

    public String getDescrizionePersonaggio() {
        return descrizionePersonaggio;
    }

    public void setDescrizionePersonaggio(String descrizionePersonaggio) {
        this.descrizionePersonaggio = descrizionePersonaggio;
    }

    public List<Carta> getCarteInMano() {
        return carteInMano;
    }

    public void setCarteInMano(List<Carta> carteInMano) {
        this.carteInMano = carteInMano;
    }

    public Arma getCartaEquipaggiata() {
        return cartaEquipaggiata;
    }

    public void setCartaEquipaggiata(Arma cartaEquipaggiata) {
        this.cartaEquipaggiata = cartaEquipaggiata;
    }
    public boolean haArma() {
        for (Carta carta : carteInMano) {
            if (carta instanceof Arma) {
                return true;
            }
        }
        return false;
    }
    public boolean haBang() {
        for (Carta carta : carteInMano) {
            if (carta.getTipo().equals("Bang")) {
                return true;
            }
        }
        return false;
    }
    public boolean haMiss() {
        for (Carta carta : carteInMano) {
            if (carta.getTipo().equals("Miss")) {
                return true;
            }
        }
        return false;
    }
    public boolean scartaMiss() {
        // Cerca la prima occorrenza di una carta "Miss" nella mano del giocatore
        for (int i = 0; i < carteInMano.size(); i++) {
            Carta carta = carteInMano.get(i);
            if (carta.getTipo().equals("Miss")) {
                // Rimuovi la carta "Miss" dalla mano del giocatore e restituisci true
                carteInMano.remove(i);
                return true;
            }
        }
        // Se non viene trovata nessuna carta "Miss", restituisci false
        return false;
    }

}
