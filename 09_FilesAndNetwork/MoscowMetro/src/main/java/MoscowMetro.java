import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class MoscowMetro {
    private static final String url = "https://www.moscowmap.ru/metro.html#lines";

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements elements = document.getElementsByAttribute("id");
        for (Element element : elements.select("span"))
            System.out.println(element.text());
    }
}
