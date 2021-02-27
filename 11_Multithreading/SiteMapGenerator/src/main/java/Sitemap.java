import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Sitemap {
    static Set<String> links = new TreeSet<>();

    static String path = "https://skillbox.ru/";
    static int numOfOperations;
    static int numOfThreads = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) throws IOException, InterruptedException {
        getLinks(path, links);
        PrintWriter pw = new PrintWriter("file.txt");
        for(String link : links) {
            int countNulls=0;
            for (char element : link.toCharArray()){
                if (element == '/') countNulls++;
            }
            if (countNulls == 3) {
                pw.write(link + "\n");
            }
            else if(countNulls == 4 && link.endsWith("/")){
                pw.write("  "+link + "\n");
            }
            else if(countNulls == 4 && Character.isLetter(link.charAt(link.length()-1))){
                pw.write("      "+link + "\n");
            }
            else if(countNulls == 5){
                pw.write("      "+link + "\n");
            }
            else if(countNulls == 6){
                pw.write("        "+link + "\n");
            }
        }
        pw.close();
//        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
//        readyBlocks = pool.invoke(new MyFork(0, numOfOperations));
//        //getSitemap(links2);
//        File file = new File("./files/file.txt");
//        PrintWriter pw = new PrintWriter(file);
//        for (String string : readyBlocks) {
//            pw.write(string + "\n");
//  //          System.out.println(string);
//        }
//        pw.close();
    }


    static void getLinks(String url, Set<String> urls) throws InterruptedException {
    Thread.sleep(500);
    if (urls.contains(url)) {
        return;
    }
    urls.add(url);

    try {
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("a");
        for(Element element : elements){
            if(urls.contains(element.absUrl("href"))){
                continue;
            }
            if(element.absUrl("href").equals("https://skillbox.ru/privacy_policy.pdf")) {
                continue;
            }
            if(!element.absUrl("href").matches("https:\\/\\/skillbox\\.ru[a-zA-Z\\/-]+")){
                continue;
            }
//            if(element.absUrl("href").length() < url.length()){
//                continue;
//            }
            System.out.println(element.absUrl("href"));
            urls.add(url);
            getLinks(element.absUrl("href"), urls);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }

//    static class MyFork extends RecursiveTask<List<String>> {
//        int from, to;
//        public MyFork(int from, int to){
//            this.from = from;
//            this.to = to;
//        }
//
//        @Override
//        protected List<String> compute() {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if((to - from) <= numOfOperations){
//                Document document = null;
//                try {
//                    document = Jsoup.connect(path).get();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                assert document != null;
//                Element content = document.getElementById("sidebar");
//                Elements links2 = content.select("li");
//                try {
//                    getSitemap(links2);
//                } catch (IOException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            else {
//                int middle = (to + from)/2;
//                MyFork firstHalf = new MyFork(from,middle);
//                firstHalf.fork();
//                MyFork secondHalf = new MyFork(middle+1, to);
//                List<String> secondValue = secondHalf.compute();
//                firstHalf.join();
//                return secondValue;
//            }
//            return null;
//        }
//    }
//}