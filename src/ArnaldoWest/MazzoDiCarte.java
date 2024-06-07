package ArnaldoWest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazzoDiCarte {
    private List<Carta> carte;
    private List<Carta> scarti;

    public MazzoDiCarte() {
        carte = new ArrayList<>();
        scarti = new ArrayList<>();

        // Aggiungere 6 armi
        carte.add(new Arma("SHOFIELD", 2));
        carte.add(new Arma("Winchester", 5));
        carte.add(new Arma("SHOFIELD", 2));
        carte.add(new Arma("ReMMIT0N", 3));
        carte.add(new Arma("SHOFIELD", 2));
        carte.add(new Arma("REVERENDA CARABINE", 4));

        // Aggiungere 50 carte Bang
        for (int i = 0; i < 50; i++) {
            carte.add(new Carta("Bang"));
        }

        // Aggiungere 24 carte Miss
        for (int i = 0; i < 24; i++) {
            carte.add(new Carta("Miss"));
        }

        // Mescolare il mazzo
        Collections.shuffle(carte);
    }

    public Carta pescaCarta() {
        if (carte.isEmpty()) {
            rimescolaScarti();
            if (carte.isEmpty()) {
                return null; // Se anche il mazzo di scarti è vuoto, non ci sono carte da pescare
            }
        }
        return carte.remove(0);
    }

    public void scartaCarta(Carta carta) {
        scarti.add(carta);
    }

    private void rimescolaScarti() {
        carte.addAll(scarti);
        scarti.clear();
        Collections.shuffle(carte);
    }
    public List<Carta> getScarti() {
        return scarti;
    }

    public void setScarti(List<Carta> scarti) {
        this.scarti = scarti;
    }

    public int getNumeroCarte() {
        return carte.size();
    }

    public int getNumeroScarti() {
        return scarti.size();
    }
}
