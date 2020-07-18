import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoscowMetro {
    private static final String url = "https://www.moscowmap.ru/metro.html#lines";
    private static List<String> connections = new ArrayList<>();
    private static List<Station> stations = new ArrayList<>();
    private static List<String> station = new ArrayList<>();
    private static List<String> stationList = new ArrayList<>();
    private static List<String> lines = new ArrayList<>();
    public static String linesAndStations = null;


    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Elements element = document.getElementsByAttribute("id");
        Elements elements = document.select("div[id = metrodata]");
//        setConnections(connections, elements);
        for (Element element1 : element.select("span")) {
            if (!element1.text().matches("\\d+.") && (!element1.text().matches("")))
                station.add(String.valueOf(element1.text()));
            if ((element1.text().matches("[А-Яа-я\\s-]+линия")) | (element1.text().matches("МЦД-\\d"))) {
                lines.add(String.valueOf(element1.text()));
            }
        }
        for (int i = 0; i < station.size(); i++) {
            linesAndStations += String.valueOf(station.get(i) + ". ");
        }
        String[] a = linesAndStations.split("МЦД-\\d. ");
        String[] b = a[0].split("[А-Яа-я\\s-]+линия. ");
        for (int i = 1; i < b.length; i++) {
            stationList.add(b[i]);
        }
        for (int i = 1; i < a.length; i++) {
            stationList.add(a[i]);
        }
        setLines(stations);
        for(int i = 0; i < stationList.size(); i++){
            System.out.println(stationList.get(i));
        }
//        for(Station state : stations)
//            System.out.println(state);
//        for(String string1 : lines)
//            System.out.println(string1);
    }
    private static void setLines(List<Station> stations){
        for(int j = 0; j < stationList.size(); j++) {
            String [] a = String.valueOf(stationList.get(j)).split(".");
            Station station1 = new Station(lines.get(j),a);
            stations.add(station1);
        }
    }
//    private static void setConnections (List<String> list , Elements elements){
//        for(Element elements1: elements){
//            String a = String.valueOf(elements.select("span[title]"));
//            a = a.replaceAll("[^А-Яа-я\\s+]", "");
//            String[] b = a.split("\\n");
//            list.addAll(Arrays.asList(b));
//        }
}
