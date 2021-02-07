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
        numberOfCPUCores = getNumberOfCPUCores();
        assert files != null;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfCPUCores);
        executorService.submit(new ImageResizer(files,newWidth,dstFolder,start));
        executorService.shutdown();
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
    private static int getNumberOfCPUCores() {
        OsValidator osValidator = new OsValidator();
        String command = "";
        if(osValidator.isMac()){
            command = "sysctl -n machdep.cpu.core_count";
        }else if(osValidator.isUnix()){
            command = "lscpu";
        }else if(osValidator.isWindows()){
            command = "cmd /C WMIC CPU Get /Format:List";
        }
        Process process = null;
        int numberOfCores = 0;
        int sockets = 0;
        try {
            if(osValidator.isMac()){
                String[] cmd = { "/bin/sh", "-c", command};
                process = Runtime.getRuntime().exec(cmd);
            }else{
                process = Runtime.getRuntime().exec(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                if(osValidator.isMac()){
                    numberOfCores = line.length() > 0 ? Integer.parseInt(line) : 0;
                }else if (osValidator.isUnix()) {
                    if (line.contains("Core(s) per socket:")) {
                        numberOfCores = Integer.parseInt(line.split("\s+")[line.split("\s+").length - 1]);
                    }
                    if(line.contains("Socket(s):")){
                        sockets = Integer.parseInt(line.split("\s+")[line.split("\s+").length - 1]);
                    }
                } else if (osValidator.isWindows()) {
                    if (line.contains("NumberOfCores")) {
                        numberOfCores = Integer.parseInt(line.split("=")[1]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(osValidator.isUnix()){
            return numberOfCores * sockets;
        }
        return numberOfCores;
    }
}
