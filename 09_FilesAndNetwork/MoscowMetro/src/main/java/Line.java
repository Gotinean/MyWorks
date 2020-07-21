import java.util.ArrayList;
import java.util.List;

public class Line {

    private String number;
    private List<String> stations = new ArrayList<>();

    public Line(String number) { // при создании объекта мы обычно знаем номер линии
        this.number = number;
    }

    public void addStation(String stationName) { // для удобства сделаем метод который добавляем станции
        stations.add(stationName);
    }

    public String getNumber() {
        return number;
    }

    public List<String> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return "Line{" +
                "number='" + number + '\'' +
                ", stations=" + stations +
                '}';
    }
}