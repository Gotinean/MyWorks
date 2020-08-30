import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class CopyFiles {
    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите путь к директории, которую вы хотите скопировать");
            Scanner scanner = new Scanner(System.in);
            String original = scanner.nextLine();
            System.out.println("Введите путь к директории, где вы хотите сохранить скопированные файлы");
            Scanner scanner1 = new Scanner(System.in);
            String copy = scanner1.nextLine();
            File folder = new File(original);
            File newFolder = new File(copy);
            if (folder.exists()) {
                try {
                    copyFolder(folder, newFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Директория, путь до которой Вы ввели, отсутствует, перепроверьте и попробуйте снова");
            }
        }
    }

    public static void copyFolder(File folder, File newFolder) throws IOException {

        if (folder.isDirectory()) {
            if (!newFolder.exists()) {
                newFolder.mkdir();
            }
            String[] files = folder.list();
            assert files != null;
            for (String file : files) {
                File folderFile = new File(folder, file);
                File newFolderFile = new File(newFolder, file);
                copyFolder(folderFile, newFolderFile);
            }
            System.out.println("Директроия скопирована в папку: " + newFolder);
        } else {
            InputStream in = new FileInputStream(folder);
            OutputStream out = new FileOutputStream(newFolder);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
            out.close();
            System.out.println("Файл скопирован в папку: " + newFolder);
        }
    }
}
