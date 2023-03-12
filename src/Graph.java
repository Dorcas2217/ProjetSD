import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Graph {

  private HashMap<Integer, Ligne> ensembleLigne = new HashMap<Integer, Ligne>();
  private Map<Tronçon, Set<Ligne>> ensembleTroncon = new HashMap<Tronçon, Set<Ligne>>();

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
        Tronçon ligneTroncon = new Tronçon(ensembleLigne.get(Integer.parseInt(tabLigne[0])),
            tabLigne[1], tabLigne[2], Integer.parseInt(tabLigne[3]));
        ajouterSommet(ligneTroncon);
        ajouterArc(ligneTroncon);
        System.out.println(ligneTroncon);
      }
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void ajouterSommet(Tronçon ligneTroncon) {
    ensembleTroncon.put(ligneTroncon, new HashSet<Ligne>());
  }

  public void ajouterArc(Tronçon tronçon) {
    ensembleTroncon.get(tronçon).add(tronçon.getLigne());
  }


  public void calculerCheminMinimisantNombreTroncons(String boileau, String alma) {
  }

  public void calculerCheminMinimisantTempsTransport(String boileau, String alma) {

  }
}
