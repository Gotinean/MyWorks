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
    static Set<String> links = new TreeSet<>();
    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "https://skillbox.ru/";
        links = new ForkJoinPool().invoke(new MyFork(path));
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
            Set<String> result = getLinks();
            for (String s : result) {
                if(check(s)) {
                    taskList.add((MyFork) new MyFork(s).fork());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
            for (MyFork child : taskList) {
                child.join();
                result.add(child.url);
            }
            return result;
        }
        private Set<String> getLinks(){
            Set<String> urls = new TreeSet<>();
            try {
                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.select("a");
                for (Element element : elements) {
                    String url = element.absUrl("href");
                    urls.add(url);
                    System.out.println(url);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return urls;
        }
        boolean check(String url) {
            return (url.matches("https:\\/\\/skillbox\\.ru[a-zA-Z\\/-]+") && url.endsWith("/"));
        }
    }
}
