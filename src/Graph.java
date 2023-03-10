import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Graph {

    public Graph(File lignes, File troncons) {
        final Supplier<Stream<String>> supplier = () -> {
            try {
                return Files.lines(Paths.get(lignes.toURI()), Charset.defaultCharset());
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }

    public void calculerCheminMinimisantNombreTroncons(String boileau, String alma) {
    }

    public void calculerCheminMinimisantTempsTransport(String boileau, String alma) {

    }
}
