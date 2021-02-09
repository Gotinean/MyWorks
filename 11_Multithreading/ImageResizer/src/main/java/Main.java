import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int numberOfCPUCores;
        int middle = files.length/2;
        numberOfCPUCores = Runtime.getRuntime().availableProcessors();
        int halfNumberOfCPUCores = numberOfCPUCores/2;
        assert files != null;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCPUCores);
        File[] files1 = new File[middle];
        System.arraycopy(files, 0,files1,0,files1.length);
        File[] files2 = new File[files.length - middle];
        System.arraycopy(files,middle,files2,0,files2.length);
        for(int i = 0; i < numberOfCPUCores; i++) {
            executorService.submit(new ImageResizer(files1, newWidth, dstFolder, start));
            executorService.submit(new ImageResizer(files2, newWidth, dstFolder, start));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
