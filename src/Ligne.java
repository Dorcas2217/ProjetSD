public class Ligne {
    private int idLigne;
    private final int numeroLigne;
    private final String premiereStation;
    private final String destination;
    private String type_transport;
    private int temps_attente;

    public Ligne(int idLigne, int numeroLigne, String premiereStation, String destination,
                 String type_transport, int temps_attente) {
        this.idLigne = idLigne;
        this.numeroLigne = numeroLigne;
        this.premiereStation = premiereStation;
        this.destination = destination;
        this.type_transport = type_transport;
        this.temps_attente = temps_attente;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public int getNumeroLigne() {
        return numeroLigne;
    }

    public String getPremiereStation() {
        return premiereStation;
    }

    public String getDestination() {
        return destination;
    }

    public String getType_transport() {
        return type_transport;
    }

    public void setType_transport(String type_transport) {
        this.type_transport = type_transport;
    }

    public int getTemps_attente() {
        return temps_attente;
    }

    public void setTemps_attente(int temps_attente) {
        this.temps_attente = temps_attente;
    }

    @Override
    public String toString() {
        return "Ligne{" +
            "idLigne=" + idLigne +
            ", numeroLigne=" + numeroLigne +
            ", premiereStation='" + premiereStation + '\'' +
            ", destination='" + destination + '\'' +
            ", type_transport='" + type_transport + '\'' +
            ", temps_attente=" + temps_attente +
            '}';
    }
}
