package ArnaldoWest;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XMLReader {
    private static final String FILE_PATH = "assets/listaCarte.xml";

    public List<Giocatore> assegnaPersonaggi(int numeroGiocatori) {
        List<Giocatore> giocatori = new ArrayList<>();
        List<Giocatore> personaggi = leggiPersonaggiDaXML();

        Random rand = new Random();

        for (int i = 0; i < numeroGiocatori; i++) {
            Giocatore personaggio = personaggi.get(rand.nextInt(personaggi.size()));
            Giocatore giocatore = new Giocatore(personaggio.getPf(), "Giocatore " + (i + 1), "", personaggio.getPersonaggio(), personaggio.getDescrizionePersonaggio(), new ArrayList<>(), null);
            giocatori.add(giocatore);
        }

        return giocatori;
    }

    private List<Giocatore> leggiPersonaggiDaXML() {
        List<Giocatore> personaggi = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList personaggiNodeList = doc.getElementsByTagName("personaggio");

            for (int i = 0; i < personaggiNodeList.getLength(); i++) {
                Element personaggioElement = (Element) personaggiNodeList.item(i);
                String nome = personaggioElement.getElementsByTagName("nome").item(0).getTextContent();
                String descrizione = personaggioElement.getElementsByTagName("descrizione").item(0).getTextContent();
                int puntiVita = Integer.parseInt(personaggioElement.getAttribute("pf"));
                Giocatore personaggio = new Giocatore(puntiVita, nome, "", nome, descrizione, new ArrayList<>(), null);
                personaggi.add(personaggio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return personaggi;
    }
}
