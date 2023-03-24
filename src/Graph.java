import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {

    private HashMap<Integer, Ligne> ensembleLigne = new HashMap<Integer, Ligne>();
    private HashMap<String, Station> mapStation = new HashMap<String, Station>();
    private Map<Station, Set<Troncon>> ensembleTroncon = new HashMap<Station, Set<Troncon>>();

    public Graph(File lignes, File troncons) {
        String currentLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(lignes));

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] tabLigne = currentLine.split(",");
                Ligne ligne = new Ligne(Integer.parseInt(tabLigne[0]), tabLigne[1], tabLigne[2],
                        tabLigne[3], tabLigne[4], Integer.parseInt(tabLigne[5]));
                ensembleLigne.put(ligne.getIdLigne(), ligne);
            }

            bufferedReader = new BufferedReader(new FileReader(troncons));

            while ((currentLine = bufferedReader.readLine()) != null) {

                String[] tabLigne = currentLine.split(",");
                String stationDepart = tabLigne[1];
                Station station = mapStation.get(stationDepart);
                if (station == null) {
                    Station s = new Station(stationDepart);
                    mapStation.put(stationDepart, s);
                    ensembleTroncon.put(s, new HashSet<>());
                }
                String stationArrivee = tabLigne[2];
                Station station2 = mapStation.get(stationArrivee);
                if (station2 == null) {
                    Station s = new Station(stationArrivee);
                    mapStation.put(stationArrivee, s);
                    ensembleTroncon.put(s, new HashSet<>());
                }
                Troncon ligneTroncon = new Troncon(ensembleLigne.get(Integer.parseInt(tabLigne[0])),
                        mapStation.get(stationDepart), mapStation.get(stationArrivee), Integer.parseInt(tabLigne[3]));


                station = ligneTroncon.getDepart();

                ensembleTroncon.get(station).add(ligneTroncon);
                //System.out.println(ligneTroncon);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void calculerCheminMinimisantNombreTroncons(String depart, String arrive) {
        Set<Station> stationsVisites = new HashSet<>();
        Deque<Station> file = new LinkedList<>();
        Map<Station, Troncon> mapStationTroncon = new HashMap<>();
        file.add(mapStation.get(depart));
        stationsVisites.add(mapStation.get(depart));
        while (!file.isEmpty()) {
            Station s = file.removeFirst();
            Set<Troncon> ensembleTronconStation = ensembleTroncon.get(s);
            for (Troncon troncon : ensembleTronconStation) {
                if (!stationsVisites.contains(troncon.getArrive())) {
                    mapStationTroncon.put(troncon.getArrive(), troncon);
                    stationsVisites.add(troncon.getArrive());
                    file.add(troncon.getArrive());

                    if (troncon.getArrive().station.equals(arrive)) {
                        affichageTroncons(mapStation.get(arrive), mapStationTroncon, depart);
                    }
                }
            }
        }
    }

    public void affichageTroncons(Station stationCurrent, Map<Station, Troncon> mapStationTroncon, String depart) {
        List<Troncon> parcours = new ArrayList<>();
        int dureeTransport = 0, dureeTotale = 0;
        // addition duree et duree totale
        while (!stationCurrent.equals(mapStation.get(depart))) {
            Troncon troncon1 = mapStationTroncon.get(stationCurrent);
            parcours.add(troncon1);
            stationCurrent = troncon1.getDepart();
        }
        for (int i = parcours.size() - 1; i > 0; i--) {
            System.out.println(parcours.get(i));
        }
        System.out.println("Durée transport: "+dureeTransport+", durée totale: "+dureeTotale);

    }

    public void calculerCheminMinimisantTempsTransport(String depart, String arrive) {
        HashMap<String, Integer> mapProvisoires = new HashMap<>();
        HashMap<String, Integer> mapDefinitives = new HashMap<>();
        HashMap<String, Troncon> mapChemins = new HashMap<>();
        mapProvisoires.put(depart, 0);
        String current = depart;
//        while(){
//
//        }

    }
}
