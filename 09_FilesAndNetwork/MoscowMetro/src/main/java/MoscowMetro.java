import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class MoscowMetro {
    private static final String url = "https://www.moscowmap.ru/metro.html#lines";
    private static List<Connections> connection = new ArrayList<>();
    private static Map<String, List<String>> mapSt = new HashMap<>();

    public static void main(String[] args) throws IOException {

        Metro metro = new Metro();
        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Elements element = document.getElementsByAttribute("id");
        Elements elements = document.select("div[id = metrodata]");
        metro.setLines(parseLine(element));
        metro.setStations(mapSt);
        metro.setConnections(getConnections(elements));
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        Path path = Paths.get("data/1.json");
        File file  = new File(String.valueOf(path));
        objectMapper.writeValue(file, metro);

    }
    public static List<Line> parseLine(Elements element) {
        List<Line> lines = new ArrayList<>();
        List<String> stations = new ArrayList<>(); //Создаем лист для списка станций и линий.
        List<String> numOfLines = new ArrayList<>(); //создаем лист только для линий.
        List<String> groupOfStations = new ArrayList<>(); // Создаю лист для групп станций, каждый элемент имеет свою группу станций,
        // которая относится к определенной линии
        List<String> namesOfLines = new ArrayList<>();


        for (Element element1 : element.select("span")) {
            if (!element1.text().matches("\\d+.") && (!element1.text().matches("")))
                stations.add(String.valueOf(element1.text()));
            if ((element1.text().matches("[А-Яа-я\\s-]+линия")) || (element1.text().matches("МЦД-\\d"))) {
                namesOfLines.add(String.valueOf(element1.text()));
                String linkHref = element1.attr("data-line");
                numOfLines.add(linkHref);
            }
        }//В цикле закидываю элементы в листы через регулярки, чтоб в одном были чисто имена линий, в другом, линии и станции(дальше поймешь, зачем линии)
        String allStations = String.join(",", stations);
        String[] a = allStations.split("МЦД-\\d,");
        //разделяю строку по МЦД-1/2 чтоб эта запись исчезла и у меня был элемент массива,
        // в котором содержатся станции определенной линии
        String[] b = a[0].split("[А-Яа-я\\s-]+линия,"); //То же самое, только разделение по какая-то линия.
        for (int i = 1; i < b.length; i++) {
            groupOfStations.add(b[i]);
        } //Закидываю все элементы массива в список
        for (int i = 1; i < a.length; i++) {
            groupOfStations.add(a[i]);

        }

        for (int i = 0; i < numOfLines.size(); i++) {
            Line line = new Line(numOfLines.get(i), namesOfLines.get(i));
            for (String station : groupOfStations.get(i).split(",")) {
                mapSt.computeIfAbsent(numOfLines.get(i), b1 -> new ArrayList<>());
                mapSt.get(numOfLines.get(i)).add(station);

            }
            lines.add(line);
        }
        for(int i = 0; i < groupOfStations.size(); i++){
            String[] z = groupOfStations.get(i).split(",");
            System.out.println("На " + namesOfLines.get(i) + " " + z.length + "станций");
        }
        return lines;
    }


    public static List<List<Station>> getConnections(Elements elements) {


        List<List<Station>> stNameWithConnection = new ArrayList<>();


        for (Element elements1 : elements.select(".js-metro-stations")) {
            String line = elements1.attr("data-line");
            Elements element2 = elements1.select("p");
            element2.stream().forEach((element) -> {
                List<Station> temp = new ArrayList<>();

                Elements spanElem = element.select("span");
                String nameSt = spanElem.get(1).text();
                if (spanElem.size() > 2) {
                    temp.add(new Station(nameSt, line));
                    for (int i = 2; i < spanElem.size(); i++) {
                        String nameStConnect = spanElem.get(i).attr("title");
                        String lineConnect = spanElem.get(i).attr("class").substring(18);
                        temp.add(new Station(nameStConnect, lineConnect));


                    }
                    stNameWithConnection.add(temp);
                }



            });


        }
        return stNameWithConnection;
    }
}


