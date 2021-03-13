import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Sitemap {
    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "https://skillbox.ru/";
        Set<String> links = new ForkJoinPool().invoke(new MyFork(path));
        PrintWriter pw = new PrintWriter("file.txt");
        for (String link : links) {
            if (!link.endsWith("/")) {
                link = link + "/";
            }
            int count = 0;
            StringBuilder ident = new StringBuilder();
            for (char element : link.toCharArray()) {
                if (element == '/') count++;
            }
            if (count > 3) {
                ident.append("  ".repeat(count - 3));
            }
            pw.write(ident + link + "\n");
        }
        pw.close();
    }
    static class MyFork extends RecursiveTask<Set<String>> {
        String url;
        public MyFork(String url) {
            this.url = url;
        }
        @Override
        protected Set<String> compute() {
            List<MyFork> taskList = new ArrayList<>();
            Set<String> result = getLinks(url);
            for (String s : result) {
                taskList.add((MyFork) new MyFork(s).fork());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            taskList.forEach(child -> result.addAll(child.join()));
            return result;
        }
        private Set<String> getLinks(String url){
            Set<String> urls = new TreeSet<>();
            try {
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.select("a[href]");
                for (Element element : elements) {
                    String link = element.absUrl("href");
                    if(check(link, url, urls)) {
                        urls.add(link);
                        System.out.println(link);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return urls;
        }
        boolean check(String url, String link, Set<String> urls) {
            return (url.startsWith(link) && url.endsWith("/") && !urls.contains(url) && !url.equals(link));
        }
    }
}
