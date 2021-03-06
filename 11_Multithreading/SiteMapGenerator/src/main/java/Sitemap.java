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

    static String path = "https://skillbox.ru/";
    static int numOfThreads = Runtime.getRuntime().availableProcessors();
    static Elements elements;
    public static void main(String[] args) throws IOException, InterruptedException {
        links.add(path);
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
        links.addAll(pool.invoke(new MyFork(path)));
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

    static class MyFork extends RecursiveTask<Set<String>>{
        String url;
        public MyFork(String url) {
            this.url = url;
        }
        Set<String> result = new TreeSet<>();

        static Set<String> getLinks(String url, Set<String> urls) throws InterruptedException {
            Thread.sleep(500);
            if (urls.contains(url)) {
                return urls;
            }
            urls.add(url);
            try {
                Document doc = Jsoup.connect(path).get();
                elements = doc.select("a");
                for (Element element : elements) {
                    if (!check(element.absUrl("href"), urls)) {
                        continue;
                    }
                    System.out.println(element.absUrl("href"));
                    urls.add(url);
                    getLinks(element.absUrl("href"), urls);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return urls;
        }
        static boolean check(String path, Set<String> urls) {
            if (path.endsWith(".pdf") | !path.matches("https:\\/\\/skillbox\\.ru[a-zA-Z\\/-]+") |
                    urls.contains(path)) {
                return false;
            } else
                return true;

        }

        @Override
        protected Set<String> compute() {
            result.add(url);
            try {
                Set<String> linksInsideCompute = getLinks(url, result);
                List<MyFork> taskList = new ArrayList<>();
                for(String link : linksInsideCompute){
                    MyFork myFork = new MyFork(link);
                    myFork.fork();
                    taskList.add(myFork);
                }
                taskList.forEach(MyFork::join);
                return result;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
