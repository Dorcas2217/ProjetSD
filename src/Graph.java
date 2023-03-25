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
            mapStation.get(stationDepart), mapStation.get(stationArrivee),
            Integer.parseInt(tabLigne[3]));

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
    Station stationDepart = mapStation.get(depart);
    file.add(stationDepart);
    stationsVisites.add(stationDepart);
    while (!file.isEmpty()) {
      Station s = file.removeFirst();
      Set<Troncon> ensembleTronconStation = ensembleTroncon.get(s);
      for (Troncon troncon : ensembleTronconStation) {
        if (!stationsVisites.contains(troncon.getArrive())) {
          mapStationTroncon.put(troncon.getArrive(), troncon);
          stationsVisites.add(troncon.getArrive());
          file.add(troncon.getArrive());

          if (troncon.getArrive().station.equals(arrive)) {
            affichageTroncons(mapStation.get(arrive), mapStationTroncon, stationDepart);
          }
        }
      }
    }
  }

  public void affichageTroncons(Station stationCurrent, Map<Station, Troncon> mapStationTroncon,
      Station depart) {
    List<Troncon> parcours = new ArrayList<>();
    int dureeTransport = 0, dureeTotale = 0;
    // addition duree et duree totale
    while (!stationCurrent.equals(depart)) {
      Troncon troncon1 = mapStationTroncon.get(stationCurrent);
      parcours.add(troncon1);
      stationCurrent = troncon1.getDepart();
    }
    for (int i = parcours.size() - 1; i >= 0; i--) {
      System.out.println(parcours.get(i));
    }
    System.out.println("Durée transport: " + dureeTransport + ", durée totale: " + dureeTotale);

  }

  public void calculerCheminMinimisantTempsTransport(String depart, String arrive) {
    Station currentStation = mapStation.get(depart);
    Station arriveeStation = mapStation.get(arrive);
    HashMap<Station, Troncon> cheminsOptimaux = new HashMap<>();
    HashMap<Station, Integer> couts = new HashMap<>();
    HashMap<Station, Integer> coutsTempo = new HashMap<>();

    coutsTempo.put(currentStation, 0);
    while (!couts.containsKey(arriveeStation)) {
      int coutMinimum = Integer.MAX_VALUE;
      for (Station station : coutsTempo.keySet()) {
        int cout = coutsTempo.get(station);
        if (cout < coutMinimum) {
          currentStation = station;
          coutMinimum = cout;
        }
      }
      couts.put(currentStation, coutMinimum);
      coutsTempo.remove(currentStation);
      Set<Troncon> lesTroncons = ensembleTroncon.get(currentStation);
      for (Troncon troncon : lesTroncons) {
        if ((coutsTempo.get(troncon.getArrive()) == null
            || coutMinimum + troncon.getDuree() < coutsTempo.get(troncon.getArrive()))
            && !couts.containsKey(troncon.getArrive())) {
          cheminsOptimaux.put(troncon.getArrive(), troncon);
          coutsTempo.put(troncon.getArrive(), coutMinimum + troncon.getDuree());
        }
      }
    }
    affichageTroncons(arriveeStation, cheminsOptimaux, mapStation.get(depart));
  }
}
/*
public void calculerCheminMinimisantTempsTransport(String depart, String arrive) {
    Station currentStation = mapStation.get(depart);
    Station arriveeStation = mapStation.get(arrive);
    HashMap<Station, Troncon> cheminsOptimaux = new HashMap<>();
    HashMap<Station, Integer> couts = new HashMap<>();
    Queue<Station> coutsTempo = new PriorityQueue<>(
        Comparator.comparingInt(s -> couts.getOrDefault(s, Integer.MAX_VALUE)));

    couts.put(currentStation, 0);
    coutsTempo.add(currentStation);
    while (!coutsTempo.isEmpty() && !couts.containsKey(arriveeStation)) {
      currentStation = coutsTempo.poll();
      Set<Troncon> lesTroncons = ensembleTroncon.get(currentStation);
      for (Troncon troncon : lesTroncons) {
        int coutVoisin = couts.get(currentStation) + troncon.getDuree();
        Station stationVoisine = troncon.getArrive();
        if (couts.containsKey(stationVoisine) && coutVoisin < couts.get(stationVoisine)){
          System.out.println("tst");
          couts.put(stationVoisine, coutVoisin);
          cheminsOptimaux.put(stationVoisine, troncon);
          coutsTempo.add(stationVoisine);
        }
      }
    }
    affichageTroncons(arriveeStation, cheminsOptimaux, mapStation.get(depart));
  }
 */
