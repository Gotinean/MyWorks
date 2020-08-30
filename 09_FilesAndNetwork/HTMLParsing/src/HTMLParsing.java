import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.*;
import java.io.*;
import java.net.URL;

public class HTMLParsing {
    private static final String webSite = "https://lenta.ru/";
    private static final String folderPath = "C:/img";
    public static void main(String[] args) throws IOException {
        try {
            Document document = Jsoup.connect(webSite).get();
            Elements img = document.getElementsByTag("img");

            for(Element el : img){
                String src = el.absUrl("src");

                System.out.println("Изображение найдено!");
                System.out.println("Аттрибут изображения: " + src);

                getImages(src);
            }
        } catch (IOException ex) {
            System.err.println("На этой странице больше нет изображений!");
        }
    }
    private static void getImages (String src) throws IOException {
        String folder = null;
        int indexname = src.lastIndexOf("/");
        if(indexname == src.length()){
            src = src.substring(1, indexname);
        }
        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname);

        System.out.println(name);

        URL url = new URL(src);
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(folderPath + name));

        for(int b; (b = in.read()) != -1;){
            out.write(b);
        }
        out.close();
        in.close();
    }
}
