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
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Graph {

  private HashMap<Integer, Ligne> ensembleLigne = new HashMap<>();

  public Graph(File lignes, File troncons) throws FileNotFoundException {
     String  currentLine ;
    try {
      BufferedReader bufferedReader = new BufferedReader(new BufferedReader( new FileReader(lignes)));

      while( (currentLine = bufferedReader.readLine()) != null ){

         String[] Alire  = bufferedReader.readLine().split(",");
        Ligne ligneLue = new Ligne(
            Integer.parseInt(Alire[0]),
            Alire[1],
            Alire[2], Alire[3], Alire[4],
            Integer.parseInt(Alire[5]) );
           ensembleLigne.put(ligneLue.getIdLigne(), ligneLue);

      }

    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public void calculerCheminMinimisantNombreTroncons(String boileau, String alma) {
  }

  public void calculerCheminMinimisantTempsTransport(String boileau, String alma) {

  }
}
