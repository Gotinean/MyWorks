import java.util.ArrayList;
import java.util.List;

public class Line {

    private String name;
    private String number;
    // private List<String> stations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

   /* public void setStations(List<String> stations) {
        this.stations = stations;
    }*/

    public Line(String number, String name) { // при создании объекта мы обычно знаем номер линии
        this.number = number;
        this.name = name;
    }

    /*  public void addStation(String stationName) { // для удобства сделаем метод который добавляем станции
          stations.add(stationName);
      }
  */
    public String getNumber() {
        return number;
    }

   /* public List<String> getStations() {
        return stations;
    }*/

    @Override
    public String toString() {
        return "Line{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'';
    }
}