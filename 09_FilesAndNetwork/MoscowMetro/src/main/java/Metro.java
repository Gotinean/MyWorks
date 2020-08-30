import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metro {
    private List<Line> lines = new ArrayList<>();
    private Map<String, List<String>> stations = new HashMap<>();
    private List<List<Station>> connections = new ArrayList<List<Station>>();

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Map<String, List<String>> getStations() {
        return stations;
    }

    public void setStations(Map<String, List<String>> stations) {
        this.stations = stations;
    }

    public List<List<Station>> getConnections() {
        return connections;
    }

    public void setConnections(List<List<Station>> connections) {
        this.connections = connections;
    }
}
