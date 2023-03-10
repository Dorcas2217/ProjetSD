public class Ligne {

  private int idLigne;
  private final String numeroLigne;
  private final String premiereStation;
  private final String destination;
  private String type_transport;
  private int temps_attente;

  public Ligne(int idLigne, String numeroLigne, String premiereStation, String destination,
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

  public String getNumeroLigne() {
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
    return "Ligne [" + "id: " + idLigne + ", n° ligne: " + numeroLigne +
        ", départ: " + premiereStation + " -> destination: " + destination +
        ", " + type_transport + ", temps: " + temps_attente + ']';
  }
}
