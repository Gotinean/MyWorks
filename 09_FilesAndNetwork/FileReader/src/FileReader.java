import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите путь до нужной директории");
            Scanner scanner = new Scanner(System.in);
            String url = scanner.nextLine();
            File folder = null;
            folder = new File(url);
            if (folder.exists()) {
                long length = 0;
                if (getFolderSize(folder) > 1024) {
                    length = (getFolderSize(folder) / 1024);
                    if (length > 1024) {
                        length = length / 1024;
                        if (length > 1024) {
                            length = length / 1024;
                            System.out.println("размер папки и вложенных файлов в сумме равен " + length + " ГБ");
                        } else {
                            System.out.println("размер папки и вложенных файлов в сумме равен " + length + " МБ");
                        }
                    } else {
                        System.out.println("размер папки и вложенных файлов в сумме равен " + length + " КБ");
                    }
                } else {
                    System.out.println(getFolderSize(folder) + " байт");
                }
            } else {
                System.out.println("Вы ввели неверный путь до директории, перепроверьте и попробуйте снова");
            }
        }
    }

    private static long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();
        int count = files.length;

        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            } else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }
}
