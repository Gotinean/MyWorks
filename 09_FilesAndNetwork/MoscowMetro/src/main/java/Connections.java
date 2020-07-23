import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Connections {
    private TreeMap lineAndStation = new TreeMap();

    public void addLineAndStation(String lineNumber, String stationName){
        lineAndStation.put(lineNumber,stationName);

    }

    @Override
    public String toString() {
        return "Connections{" +
                "lineAndStation=" + lineAndStation +
                '}';
    }
}
