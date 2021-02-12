import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class Main
{
    public static void main(String[] args) throws InterruptedException {
        int newWidth = 300;
        String srcFolder = "/home/swile/pictures/";
        String dstFolder = "/home/swile/newPictures/";
        File srcDir = new File(srcFolder);

        File[] files = srcDir.listFiles();
        int numberOfCPUCores;
        numberOfCPUCores = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();
        System.out.println(numberOfCPUCores);
        assert files != null;
        int a = files.length%numberOfCPUCores;
        List<File[]> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCPUCores);
        for(int i = 0; i < numberOfCPUCores; i++){
            File[] files1 = new File[files.length/numberOfCPUCores];
            System.arraycopy(files, i*(files.length/numberOfCPUCores),files1,0,files1.length);
            list.add(files1);
        }
        if (a > 0){
            File[] files2 = new File[a+1];
            System.arraycopy(files, files.length/numberOfCPUCores,files2,0,files2.length);
            list.add(files2);
        }
        for(int i = 0; i < list.size(); i++){
                executorService.submit(new ImageResizer(list.get(i), newWidth, dstFolder, start));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
