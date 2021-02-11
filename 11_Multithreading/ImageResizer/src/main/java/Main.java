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
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();
        int numberOfCPUCores;
        numberOfCPUCores = Runtime.getRuntime().availableProcessors();
        int halfNumberOfCPUCores = numberOfCPUCores/2;
        assert files != null;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCPUCores);
        for(int i = 0; i < numberOfCPUCores; i++){
            executorService.submit(new ImageResizer(files[i], newWidth, dstFolder, start));
            for(int j = numberOfCPUCores; j < files.length/numberOfCPUCores; j++) {
                executorService.submit(new ImageResizer(files[i*j], newWidth, dstFolder, start));
            }
        }
//        for(int i = 0; i < files.length/numberOfCPUCores; i++) {
//            executorService.submit(new ImageResizer(files[i], newWidth, dstFolder, start));
//        }
//        for(int i = (files.length/numberOfCPUCores); i < files.length/(files.length/numberOfCPUCores); i++){
//            executorService.submit(new ImageResizer(files[i], newWidth, dstFolder, start));
//        }
//        for(int i = files.length-1; i > files.length/(files.length/numberOfCPUCores); i--){
//            executorService.submit(new ImageResizer(files[i], newWidth, dstFolder, start));
//        }
//        for (int i = files.length/(files.length/numberOfCPUCores); i > (files.length/numberOfCPUCores); i--){
//            executorService.submit(new ImageResizer(files[i], newWidth, dstFolder, start));
//        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
