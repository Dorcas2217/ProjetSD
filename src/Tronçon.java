import java.util.Objects;

public class Tronçon {

  private  Ligne ligne;
  private String depart;
  private String arrive;
  private int duree;


  public Tronçon(Ligne ligne , String depart, String arrive, int duree) {
    this.ligne = ligne;
    this.depart = depart;
    this.arrive = arrive;
    this.duree = duree;
  }

  public Ligne getLigne() {
    return ligne;
  }

  public void setLigne(Ligne ligne) {
    this.ligne = ligne;
  }

  public String getDepart() {
    return depart;
  }

  public void setDepart(String depart) {
    this.depart = depart;
  }

  public String getArrive() {
    return arrive;
  }

  public void setArrive(String arrive) {
    this.arrive = arrive;
  }

  public int getDuree() {
    return duree;
  }

  public void setDuree(int duree) {
    this.duree = duree;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Tronçon tronçon)) {
      return false;
    }
    return ligne.equals(tronçon.ligne) && depart.equals(tronçon.depart) && arrive.equals(
        tronçon.arrive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ligne, depart, arrive);
  }
}
