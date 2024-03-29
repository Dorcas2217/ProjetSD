import java.util.Objects;

public class Troncon {

    private Ligne ligne;
    private Station depart;
    private Station arrive;
    private int duree;


    public Troncon(Ligne ligne , Station depart, Station arrive, int duree) {
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
        public Station getDepart() {
            return depart;
        }
        public void setDepart(Station depart) {
            this.depart = depart;
        }
        public Station getArrive() {
            return arrive;
        }
        public void setDuree(int duree) {
            this.duree = duree;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Troncon tronçon)) {
                return false;
            }
            return ligne.equals(tronçon.ligne) && depart.equals(tronçon.depart) && arrive.equals(
                    tronçon.arrive);
        }
        @Override
        public int hashCode() {
            return Objects.hash(ligne, depart, arrive);
        }

        @Override
        public String toString() {
            return "Tronçon [ départ=" + depart +
                    ", arrivée=" + arrive + ", durée=" + duree + ", " + ligne ;
        }

    public int getDuree() {
        return duree;
    }
}