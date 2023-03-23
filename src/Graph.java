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
        Troncon ligneTroncon = new Troncon(ensembleLigne.get(Integer.parseInt(tabLigne[0])),
            new Station(tabLigne[1]), new Station(tabLigne[2]), Integer.parseInt(tabLigne[3]));

        Station station = mapStation.get(tabLigne[1]);
        if (station == null) {
          mapStation.put(tabLigne[1], new Station(tabLigne[1]));
          mapStation.put(tabLigne[2], new Station(tabLigne[2]));
        }

        if (ensembleTroncon.get(station) == null) {
          ensembleTroncon.put(station, new HashSet<>());
        }
        ensembleTroncon.get(station).add(ligneTroncon);
        System.out.println(ligneTroncon);
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
      Set<Troncon> ensembleTronconStation = ensembleTroncon.get(file.removeFirst());

      for (Troncon troncon : ensembleTronconStation) {
        if (!stationsVisites.contains(troncon.getArrive())) {
          mapStationTroncon.put(mapStation.get(troncon.getArrive()), troncon);
          stationsVisites.add(troncon.getArrive());
          file.add(mapStation.get(troncon.getArrive()));

          if (troncon.getArrive().station.equals(arrive)) {
                List<Troncon> maListe = new ArrayList<>();
//                while(){
//
//                }
          }
        }


      }


    }


  }

 /*public void affichage(String arrive, String depart, HashMap<String, Troncon> chemins) {
    if (!chemins.containsKey(arrive)) {
      throw new RuntimeException();
    }
    String current = arrive;
    ArrayDeque<Troncon> arrayDeque1 = new ArrayDeque<>();
    while (!current.equals(depart)) {
      Troncon troncon = chemins.get(current);
      arrayDeque1.addFirst(troncon);
      current = chemins.get(current).getDepart();
    }
    for (Troncon troncon : arrayDeque1) {
      System.out.println(troncon);
    }
  } */

 /* public void calculerCheminMinimisantTempsTransport(String depart, String arrive) {

    HashMap<String, Integer> etiquettesProvisoires = new HashMap<>();
    HashMap<String, Integer> etiquettesDefinitives = new HashMap<>();
    HashMap<String, Troncon> chemins = new HashMap<>();


    etiquettesProvisoires.put(depart, 0);


    String current = depart;
    while (!etiquettesDefinitives.containsKey(arrive)) {
      int min = Integer.MAX_VALUE;
      for (String s : etiquettesProvisoires.keySet()) {
        if (etiquettesProvisoires.get(s) < min) {
          min = etiquettesProvisoires.get(s);
          current = s;
        }
      }
      etiquettesProvisoires.remove(current);
      etiquettesDefinitives.put(current, min);
      HashSet<Troncon> tronconHashSet = listeAdjacence.get(current);
      for (Troncon troncon : tronconHashSet) {
        if ((etiquettesProvisoires.get(troncon.getArrive()) == null || min + troncon.getDurée() < etiquettesProvisoires.get(troncon.getArrive()))
                && !etiquettesDefinitives.containsKey(troncon.getArrive())) {
          chemins.put(troncon.getArrive(), troncon);
          etiquettesProvisoires.put(troncon.getArrive(), min + troncon.getDurée());
        }
      }
    }
    affichage(arrive, depart, chemins);
  } */

  public void calculerCheminMinimisantTempsTransport(String depart, String arrive) {

  }
}
