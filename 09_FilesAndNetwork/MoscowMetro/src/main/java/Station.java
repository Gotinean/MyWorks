import java.util.Arrays;

public class Station {
    String line = null;
    String [] stations;
    public Station(String line, String [] stations) {
        this.line = line;
        this.stations = stations;

    }

    @Override
    public String toString() {
        return "Station{" +
                "line='" + line + '\'' +
                ", stations=" + Arrays.toString(stations) +
                '}';
    }
}
