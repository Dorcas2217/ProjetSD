public class Station {

  String station ;

  public Station(String station) {
    this.station = station;
  }

  public String getStation() {
    return station;
  }

  public void setStation(String station) {
    this.station = station;
  }

  @Override
  public String toString() {
    return station ;
  }
}
