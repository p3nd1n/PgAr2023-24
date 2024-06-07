package ArnaldoWest;

public class Finale {
    public static void gestisciFinale(String ruoloVincitore) {
    	
        GestoreTurno.setGameOver(true);
        if (ruoloVincitore.equals("Rinnegato")) {
            System.out.println("Il rinnegato ha vinto!");
        } else if (ruoloVincitore.equals("Fuorilegge")) {
            System.out.println("I fuorilegge hanno vinto!");
        } else {
            System.out.println("Vittoria non determinata.");
        }
    }
}
