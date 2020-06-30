import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReader {
    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("Введите путь до нужной директории");
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();
            File folder = null;
            folder = new File(url);
            if (folder.exists()) {
                long length = getFolderSize(folder);
                System.out.println(sizeConverter(length));

            } else {
                System.out.println("Вы ввели неверный путь до директории, перепроверьте и попробуйте снова");
            }
        }
    }

    private static long getFolderSize(File folder) throws IOException {
        Path fold = Paths.get(String.valueOf(folder));
        long size = Files.walk(fold)
                .filter(p -> p.toFile().isFile())
                .mapToLong(p -> p.toFile().length())
                .sum();
        return size;
    }
    private static String sizeConverter(long size){

        if (size < 1024) return size + " Б";

        int exp = (int) (Math.log(size) / (Math.log(1024)));

        char unitsPrefix = "КМГТПЭ".charAt(exp - 1);

        return String.format("%.2f %sБ", size / Math.pow(1024, exp), unitsPrefix);

    }


}
