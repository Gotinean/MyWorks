import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Sitemap {
    static List<String> readyBlocks = new ArrayList<>();
    static String path = "http://lenta.ru/";
    static int numOfOperations;
    static int numOfThreads = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) throws IOException, InterruptedException {
        Document document = Jsoup.connect(path).get();
        Element content = document.getElementById("sidebar");
        Elements links2 = content.select("li");
        numOfOperations = links2.size();
        readyBlocks.add(path);
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
        readyBlocks = pool.invoke(new MyFork(0, numOfOperations));
        //getSitemap(links2);
        File file = new File("./files/file.txt");
        PrintWriter pw = new PrintWriter(file);
        for (String string : readyBlocks) {
            pw.write(string + "\n");
  //          System.out.println(string);
        }
        pw.close();
    }

    public static void getSitemap(Elements links2) throws IOException, InterruptedException {
        if (readyBlocks.size() == 1) {
            for (Element link : links2) {
                String temporaryLink = link.select("a").attr("href");
                if (temporaryLink.length() > 1) {
                    char a = temporaryLink.charAt(0);
                    if (a == '/') {
                        String pathForSecondLink = path + temporaryLink.substring(1);
                        readyBlocks.add("   " + path + temporaryLink.substring(1));
                        int secondNum = 1;
                        getSecondPart(pathForSecondLink, readyBlocks, secondNum);
                    }
                }
            }
        }
    }

    public static void getSecondPart(String pathForSecondLink, List<String> list, int secondNum) throws IOException, InterruptedException {
        Thread.sleep(1500);
        Document document1 = Jsoup.connect(pathForSecondLink).get();
        Element second = document1.getElementById("root");
        Elements links3 = second.select("div[class^='items']");
        if (links3 != null) {
            Elements linked4 = links3.select("a[class^='item dark']");
            if (linked4.size() > secondNum) {
                String secondPart = path + linked4.get(secondNum).attr("href").substring(1);
                list.add("      " + secondPart);
                secondNum++;
                int thirdNum = 0;
                //getThirdPart(secondPart, list, thirdNum);
                getSecondPart(pathForSecondLink, list, secondNum);
            }
        }
        return;
    }

    public static void getThirdPart(String pathForThirdLink, List<String> list, int thirdNum) throws IOException, InterruptedException {
        Thread.sleep(1500);
        Document document2 = Jsoup.connect(pathForThirdLink).get();
        Element third = document2.getElementById("root");
        Elements links5 = third.select("div[class^='news']");
        if (links5 != null) {
            Elements linked5 = links5.select("a");
            if (linked5.size() != 0) {
                while (linked5.size() > thirdNum) {
                    list.add("           " + path.substring(0, path.length() - 1) + linked5.get(thirdNum).attr("href"));
                    thirdNum++;
                    getThirdPart(pathForThirdLink, list, thirdNum);
                }
            }
        }
        return;
    }

    static class MyFork extends RecursiveTask<List<String>> {
        int from, to;
        public MyFork(int from, int to){
            this.from = from;
            this.to = to;
        }

        @Override
        protected List<String> compute() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if((to - from) <= numOfOperations){
                Document document = null;
                try {
                    document = Jsoup.connect(path).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assert document != null;
                Element content = document.getElementById("sidebar");
                Elements links2 = content.select("li");
                try {
                    getSitemap(links2);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
            else {
                int middle = (to + from)/2;
                MyFork firstHalf = new MyFork(from,middle);
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle+1, to);
                List<String> secondValue = secondHalf.compute();
                firstHalf.join();
                return secondValue;
            }
            return null;
        }
    }
}