package ArnaldoWest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inizializzazione {

    public static List<Giocatore> assegnaRuoli(List<Giocatore> giocatori) {
        int numeroGiocatori = giocatori.size();
        List<String> ruoliDisponibili = new ArrayList<>();

        // Aggiungi i ruoli disponibili in base al numero di giocatori
        if (numeroGiocatori == 4) {
            Collections.addAll(ruoliDisponibili, "Sceriffo", "Fuorilegge", "Fuorilegge", "Rinnegato");
        } else if (numeroGiocatori == 5) {
            Collections.addAll(ruoliDisponibili, "Sceriffo", "Fuorilegge", "Fuorilegge", "Rinnegato", "Vice");
        } else if (numeroGiocatori >= 6) {
            Collections.addAll(ruoliDisponibili, "Sceriffo", "Fuorilegge", "Fuorilegge", "Rinnegato", "Vice", "Fuorilegge");
        } else if (numeroGiocatori >= 7) {
            Collections.addAll(ruoliDisponibili, "Sceriffo", "Fuorilegge", "Fuorilegge", "Rinnegato", "Vice", "Vice", "Fuorilegge");
        }
        

        // Assegna casualmente i ruoli ai giocatori
        Collections.shuffle(ruoliDisponibili);
        for (int i = 0; i < numeroGiocatori; i++) {
            giocatori.get(i).setRuolo(ruoliDisponibili.get(i));
        }

        return giocatori;
    }
}

