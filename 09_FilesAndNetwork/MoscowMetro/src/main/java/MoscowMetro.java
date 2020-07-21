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
    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect(url).maxBodySize(0).get();
        Elements element = document.getElementsByAttribute("id");
        Elements elements = document.select("div[id = metrodata]");
        parseLine(element);
        System.out.println(Arrays.toString(lines.toArray()));

    }
    private static void parseLine(Elements element){
        List<String> stations = new ArrayList<>(); //Создаем лист для списка станций и линий.
        List<String> namesOfLines = new ArrayList<>(); //создаем лист только для линий.
        List<String> groupOfStations = new ArrayList<>(); // Создаю лист для групп станций, каждый элемент имеет свою группу станций,
        // которая относится к определенной линии
        for (Element element1 : element.select("span")) {
            if (!element1.text().matches("\\d+.") && (!element1.text().matches("")))
                stations.add(String.valueOf(element1.text()));
            if ((element1.text().matches("[А-Яа-я\\s-]+линия")) | (element1.text().matches("МЦД-\\d"))) {
                namesOfLines.add(String.valueOf(element1.text()));
            }
        }//В цикле закидываю элементы в листы через регулярки, чтоб в одном были чисто имена линий, в другом, линии и станции(дальше поймешь, зачем линии)
        String allStations = null;
        for (int i = 0; i < stations.size(); i++) {
            allStations += String.valueOf(stations.get(i) + ", ");//преобразую лист в простой стринг, чтоб можно было его разделить так как мне надо.
        }
        String[] a = allStations.split("МЦД-\\d, "); //разделяю строку по МЦД-1/2 чтоб эта запись исчезла и у меня был элемент массива,
        // в котором содержатся станции определенной линии
        String[] b = a[0].split("[А-Яа-я\\s-]+линия, "); //То же самое, только разделение по какая-то линия.
        for (int i = 1; i < b.length; i++) {
            groupOfStations.add(b[i]);
        } //Закидываю все элементы массива в список
        for (int i = 1; i < a.length; i++) {
            groupOfStations.add(a[i]);
        }
        for(int i = 0; i < namesOfLines.size(); i++){
            Line line = new Line(namesOfLines.get(i));
            line.addStation(groupOfStations.get(i));
            lines.add(line);
        }
    }

}











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
//    private static void setConnections (List<String> list , Elements elements){
//        for(Element elements1: elements){
//            String a = String.valueOf(elements.select("span[title]"));
//            a = a.replaceAll("[^А-Яа-я\\s+]", "");
//            String[] b = a.split("\\n");
//            list.addAll(Arrays.asList(b));
//        }

