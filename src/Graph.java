import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
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
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void calculerCheminMinimisantNombreTroncons(String depart, String arrive) {
    Set<Station> stationsVisites = new HashSet<>();
    Deque<Station> file = new LinkedList<>();
    Map<Station, Troncon> mapStationTroncon = new HashMap<>();
    int dureeTransport = 0, dureeTotale = 0, nbTroncons = 0;
    Ligne lignePrecedente = null;

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
            Troncon tronconCourant = troncon;
            while (tronconCourant != null) {
              dureeTransport += tronconCourant.getDuree();
              nbTroncons++;
              if (lignePrecedente != null && lignePrecedente != tronconCourant.getLigne()) {
                dureeTotale += lignePrecedente.getTemps_attente();
              }
              lignePrecedente = tronconCourant.getLigne();
              tronconCourant = mapStationTroncon.get(tronconCourant.getDepart());
            }
            dureeTotale += lignePrecedente.getTemps_attente();
            afficherTrajet(mapStation.get(arrive), mapStationTroncon, stationDepart,
                dureeTransport, dureeTotale, nbTroncons);
          }
        }
      }
    }
  }

  public void afficherTrajet(Station stationActuelle, Map<Station, Troncon> mapStationTroncon,
      Station depart, int dureeTransport, int dureeTotale, int nbTroncons) {

    List<Troncon> parcours = new ArrayList<>();

    while (!stationActuelle.equals(depart)) {
      Troncon tronconActuel = mapStationTroncon.get(stationActuelle);
      parcours.add(tronconActuel);
      stationActuelle = tronconActuel.getDepart();
    }

    Collections.reverse(parcours);

    for (Troncon troncon : parcours) {
      System.out.println(troncon);
    }

    System.out.println("Durée transport: " + dureeTransport + ", durée totale: " +
        dureeTotale + ", nbTroncons: " + nbTroncons);
  }

  public void calculerCheminMinimisantTempsTransport(String depart, String arrive) {

    Station currentStation = mapStation.get(depart);
    Station arriveeStation = mapStation.get(arrive);

    Map<Station, Troncon> cheminsOptimaux = new HashMap<>();
    Map<Station, Integer> couts = new HashMap<>();
    Map<Station, Integer> coutsTempo = new HashMap<>();
    int dureeTransport = 0, dureeTotale = 0, nbTroncons = 0;

    coutsTempo.put(currentStation, 0);
    PriorityQueue<Station> pq = new PriorityQueue<>(Comparator.comparingInt(coutsTempo::get));
    pq.add(currentStation);

    while (!couts.containsKey(arriveeStation)) {
      currentStation = pq.poll();

      couts.put(currentStation, coutsTempo.get(currentStation));
      Set<Troncon> lesTroncons = ensembleTroncon.get(currentStation);

      for (Troncon troncon : lesTroncons) {
        Station stationVoisine = troncon.getArrive();
        if (stationVoisine != null && (!couts.containsKey(stationVoisine)
            || coutsTempo.get(currentStation) + troncon.getDuree() < coutsTempo.get(
            stationVoisine))) {
          cheminsOptimaux.put(stationVoisine, troncon);
          coutsTempo.put(stationVoisine, coutsTempo.get(currentStation) + troncon.getDuree());
          pq.add(stationVoisine);
          dureeTransport += troncon.getDuree();
          nbTroncons++;
        }
      }
      if (currentStation == arriveeStation) {
        dureeTotale = coutsTempo.get(currentStation);
      }
    }
      afficherTrajet(arriveeStation, cheminsOptimaux, mapStation.get(depart), dureeTransport,
          dureeTotale, nbTroncons);
  }
}