import java.util.List;
import java.util.Random;

public class StatistichePersonaggio {

    public void applicaAbilitaExtra(Giocatore personaggioColpito, Giocatore giocatoreColpito, List<Carta> mazzo) {
        switch (personaggioColpito.getNome()) {
            case "Bart Cassidy":
                if (personaggioColpito.equals(giocatoreColpito)) {
                    // Controlla se il personaggio colpito perde un punto ferita
                    if (personaggioColpito.getPf() < giocatoreColpito.getPf()) {
                        // Pesca una carta dal mazzo
                        Carta cartaPescata = mazzo.remove(new Random().nextInt(mazzo.size()));
                        personaggioColpito.getCarteInMano().add(cartaPescata);
                        System.out.println(personaggioColpito.getNome() + " ha perso un PF e ha pescato una carta.");
                    }
                }
                break;
            case "El Gringo":
                if (personaggioColpito.equals(giocatoreColpito)) {
                    // Controlla se il personaggio colpito perde un punto ferita a causa di una carta giocata da un giocatore
                    if (personaggioColpito.getPf() < giocatoreColpito.getPf()) {
                        // Controlla se il giocatore che lo ha colpito ha carte in mano
                        if (!giocatoreColpito.getCarteInMano().isEmpty()) {
                            // Rimuove una carta a caso dalla mano del giocatore che lo ha colpito
                            Carta cartaRimossa = giocatoreColpito.getCarteInMano().remove(new Random().nextInt(giocatoreColpito.getCarteInMano().size()));
                            System.out.println(personaggioColpito.getNome() + " ha perso un PF e ha pescato una carta da " + giocatoreColpito.getNome() + ": " + cartaRimossa.getTipo());
                        } else {
                            System.out.println("Peccato! " + giocatoreColpito.getNome() + " non ha carte in mano.");
                        }
                    }
                }
                break;
            case "Jourdonnais":
                if (personaggioColpito.equals(giocatoreColpito)) {
                    // Ripete l'abilità di scartare Miss
                    while (personaggioColpito.haMiss()) {
                        personaggioColpito.scartaMiss();
                        System.out.println(personaggioColpito.getNome() + " ha scartato un Miss.");
                    }
                }
                break;
            case "Rose Doolan":
                // Aggiunge +1 alla distanza ogni volta che cambia arma
                personaggioColpito.incrementaDistanza();
                System.out.println(personaggioColpito.getNome() + " ha cambiato arma. La distanza è aumentata di 1.");
                break;
            // Aggiungi altri casi per gli altri personaggi
            default:
                System.out.println("Nessuna abilità extra applicabile per " + personaggioColpito.getNome());
        }
    }
}
