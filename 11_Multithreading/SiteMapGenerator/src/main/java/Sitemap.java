import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sitemap {
    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "http://lenta.ru/";
        List<Maps> links = new ArrayList<>();
        Document document = Jsoup.connect(path).get();
        Element content = document.getElementById("sidebar");
        Elements links2 = content.select("li");
        for (Element link : links2) {
            String readyLink = link.select("a").attr("href");
            if (readyLink.length() > 0) {
                char a = readyLink.charAt(0);
                if (a == '/') {
                    Maps maps = new Maps();
                    maps.setFirstLink("    " + path + readyLink.substring(1));
                    links.add(maps);
                }
            }
        }
        for (int i = 0; i < links.size(); i++) {
            Thread.sleep(150);
            Document document1 = Jsoup.connect(links.get(i).getFirstLink()).get();
            Element second = document1.getElementById("root");
            Elements links3 = second.select("div[class^='items']");
            Elements linked4 = links3.select("a[class^='item dark']");
            List<List<String>> secondList = new ArrayList<>();
            List<String> secondLinks = new ArrayList<>();
            links.get(i).setSecondLink(secondLinks);
            for(int j = 1; j < linked4.size(); j++){
                Document document2 = Jsoup.connect(path + linked4.get(j).attr("href").substring(1)).get();
                Element third = document2.getElementById("root");
                Elements links5 = third.select("div[class^='news']");
                Elements linked5 = links5.select("a");
                StringBuilder a = new StringBuilder();
                for(int k = 0; k < linked5.size(); k++){
                    a.append("           ").append(path, 0, path.length()-1).append(linked5.get(k).attr("href")).append("\n");
                }
                links.get(i).addSecondLink(secondLinks, "        " + path + linked4.get(j).attr("href").substring(1) + "\n" + a);

            }
        }
        System.out.println(path);
        for (int i = 1; i < links.size(); i++) {
            Maps link = links.get(i);
            System.out.println(link.getFirstLink());
            for (String string : link.getSecondLink()) {
                System.out.println(string);
            }
        }
    }
}