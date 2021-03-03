import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Sitemap {
    static Set<String> links = new TreeSet<>();

    static String path = "https://skillbox.ru/";
    static int numOfThreads = Runtime.getRuntime().availableProcessors();
    static Elements elements;
    public static void main(String[] args) throws IOException, InterruptedException {
        links.add(path);
        int numOfOperations = links.size();
        //getLinks(path, links);
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
        pool.invoke(new MyFork(0,numOfOperations));
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
    static void getLinks(String url, Set<String> urls) throws InterruptedException {
    Thread.sleep(500);
    if (urls.contains(url)) {
        return;
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
    }
    static boolean check(String path, Set<String> urls) {
        if (path.endsWith(".pdf") | !path.matches("https:\\/\\/skillbox\\.ru[a-zA-Z\\/-]+") |
                urls.contains(path)) {
            return false;
        } else
            return true;

    }
    static class MyFork extends RecursiveTask<Set<String>>{
        int from, to;
        public MyFork(int from, int to){
            this.from = from;
            this.to = to;
        }
        @Override
        protected Set<String> compute() {
            if((to-from) <= links.size()/numOfThreads){
                try {
                    getLinks(path, links);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                int middle = (to+from)/2;
                MyFork firstHalf = new MyFork(from, middle);
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle+1, to);
                links = secondHalf.compute();
                secondHalf.join();
                return links;
            }
            return links;
        }
    }
}
