import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoscowMetro {
    private static final String url = "https://www.moscowmap.ru/metro.html#lines";
    private static List<Line> lines = new ArrayList<>(); //Лист объектов, который заполняем по итогу (number - название Линии, stations - станции, которые к ней относятся
    private static List<NumberAndNameOfLine> numberAndNameOfLines = new ArrayList<>();
    private static List<Connections> connection = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Elements element = document.getElementsByAttribute("id");
        Elements elements = document.select("div[id = metrodata]");
        List<Line> lines = parseLine(element);
        System.out.println(lines.get(14));
        List<NumberAndNameOfLine> numberAndNameOfLines = getLines(element);
        System.out.println(numberAndNameOfLines.get(14));
        List<Connections> connection = getConnections(elements);
        System.out.println(connection.get(1));


    }

    private static List<Line> parseLine(Elements element) {
        List<String> stations = new ArrayList<>(); //Создаем лист для списка станций и линий.
        List<String> namesOfLines = new ArrayList<>(); //создаем лист только для линий.
        List<String> groupOfStations = new ArrayList<>(); // Создаю лист для групп станций, каждый элемент имеет свою группу станций,
        // которая относится к определенной линии
        for (Element element1 : element.select("span")) {
            if (!element1.text().matches("\\d+.") && (!element1.text().matches("")))
                stations.add(String.valueOf(element1.text()));
            if ((element1.text().matches("[А-Яа-я\\s-]+линия")) | (element1.text().matches("МЦД-\\d"))) {
                String linkHref = element1.attr("data-line");
                namesOfLines.add(linkHref);
            }
        }//В цикле закидываю элементы в листы через регулярки, чтоб в одном были чисто имена линий, в другом, линии и станции(дальше поймешь, зачем линии)
        String allStations = String.join(",", stations);
        String[] a = allStations.split("МЦД-\\d,"); //разделяю строку по МЦД-1/2 чтоб эта запись исчезла и у меня был элемент массива,
        // в котором содержатся станции определенной линии
        String[] b = a[0].split("[А-Яа-я\\s-]+линия,"); //То же самое, только разделение по какая-то линия.
        for (int i = 1; i < b.length; i++) {
            groupOfStations.add(b[i]);
        } //Закидываю все элементы массива в список
        for (int i = 1; i < a.length; i++) {
            groupOfStations.add(a[i]);
        }
        for (int i = 0; i < namesOfLines.size(); i++) {
            Line line = new Line(namesOfLines.get(i));
            for (String station : groupOfStations.get(i).split(",")) {
                line.addStation(station);
            }
            lines.add(line);
        }
        return lines;
    }

    private static List<NumberAndNameOfLine> getLines(Elements element) {
        List<String> names = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        for (Element element1 : element.select("span")) {

            if ((element1.text().matches("[А-Яа-я\\s-]+линия")) | (element1.text().matches("МЦД-\\d"))) {
                names.add(String.valueOf(element1.text()));
                String linkHref = element1.attr("data-line");
                numbers.add(linkHref);
            }

        }

        for (int i = 0; i < names.size(); i++) {
            NumberAndNameOfLine numberAndNameOfLine = new NumberAndNameOfLine(numbers.get(i), names.get(i));
            numberAndNameOfLines.add(numberAndNameOfLine);
        }
        return numberAndNameOfLines;
    }

    private static List<Connections> getConnections(Elements elements) {
        List<String> lines = new ArrayList<>();
        List<String> stations = new ArrayList<>();
        for (Element elements1 : elements.select("span")) {
            if (!(elements1.attr("class") == null)) {
                Connections connections = new Connections();
                String linkHref = elements1.attr("data-line");
                String station = null;
                if (!elements1.text().matches("\\d+.") && (!elements1.text().matches(""))) {
                    station = (String.valueOf(elements1.text()));
                }
                connections.addLineAndStation(linkHref, station);
                String connect = elements1.attr("class");
                connections.addLineAndStation(connect, station);
                connection.add(connections);
            }

        }
        return connection;
    }
}

//    String a = String.valueOf(elements.select("span[title]"));
//            a = a.replaceAll("[^А-Яа-я\\s+]", "");
//                    String[] b = a.split("\\n");
//                    lines.addAll(Arrays.asList(b));

//         {
//            if (!element1.text().matches("\\d+.") && (!element1.text().matches("")))
//                station.add(String.valueOf(element1.text()));
//            if ((element1.text().matches("[А-Яа-я\\s-]+линия")) | (element1.text().matches("МЦД-\\d"))) {
//                lines.add(String.valueOf(element1.text()));
//            }
//        }
//        for (int i = 0; i < station.size(); i++) {
//            linesAndStations += String.valueOf(station.get(i) + ". ");
//        }
//        String[] a = linesAndStations.split("МЦД-\\d. ");
//        String[] b = a[0].split("[А-Яа-я\\s-]+линия. ");
//        for (int i = 1; i < b.length; i++) {
//            stationList.add(b[i]);
//        }
//        for (int i = 1; i < a.length; i++) {
//            stationList.add(a[i]);
//        }
//        setLines(stations);
//        for(int i = 0; i < stationList.size(); i++){
//            System.out.println(stationList.get(i));
//        }
////        for(Station state : stations)
////            System.out.println(state);
////        for(String string1 : lines)
////            System.out.println(string1);
//    }
//    private static void setLines(List<Station> stations){
//        for(int j = 0; j < stationList.size(); j++) {
//            String [] a = String.valueOf(stationList.get(j)).split(".");
//            Station station1 = new Station(lines.get(j),a);
//            stations.add(station1);
//        }
//    }
//

