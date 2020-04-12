import java.util.*;

/*
TODO: Написать генератор «красивых» автомобильных номеров и реализовать поиск элементов в списке:
 прямым перебором по ArrayList
 бинарным поиском по сортированному ArrayList
 поиском в HashSet
 поиском в TreeSet
 Измерить и сравнить длительность каждого метода поиска,
 написать в форме ответа, какой поиск оказался самым быстрым, а какой самым медленным.
 XYZ - различные буквы, N - цифры, R - регион (от 01 до 199)
 XNNNYZR - пример, A111BC197, Y777HC66
 Таким образом количество номер будет достаточно для оценки времени поиска даже в миллисекундах
 1- создать массивы с разрешенными цифрами и буквами
 2- написать генератор номерных знаков.
 3- создать сканер для ввода номерного знака.
 4- создать поиск элементов в списке четыремя способами.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String number = null;
        String[] letters = {"C", "M", "T", "B", "A", "P", "O", "H", "E", "Y"};
        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 1; k < 199; k++) {
                    String letter = letters[i];
                    String region = String.valueOf(k);
                    if (k < 10) {
                        region = "0" + region;
                    }
                    number = String.format("%s%d%d%d%s%s%s", letter, j, j, j, letter, letter, region);
                    list.add(number);
                }
            }
        }
        Collections.sort(list);
        HashSet<String> set = new HashSet<>(list);
        TreeSet<String> set1 = new TreeSet<>(list);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String stateNumber = scanner.nextLine();
            long a = System.nanoTime();
            if (list.contains(stateNumber)) {
                System.out.println("Поиск перебором: номер найден, поиск занял: " + (System.nanoTime() - a) + "нс");
            } else {
                System.out.println("Поиск перебором: номер не найден, поиск занял: " + (System.nanoTime() - a) + "нс");
            }
            long b = System.nanoTime();
            if (Collections.binarySearch(list, stateNumber) == 0) {
                System.out.println("Бинарный поиск: номер найден, поиск занял: " + (System.nanoTime() - b) + "нс");
            } else {
                System.out.println("Бинарный поиск: номер не найден, поиск занял: " + (System.nanoTime() - b) + "нс");
            }
            long c = System.nanoTime();
            if (set.contains(stateNumber)) {
                System.out.println("Поиск в HashSet: номер найден, поиск занял: " + (System.nanoTime() - c) + "нс");
            } else {
                System.out.println("Поиск в HashSet: номер не найден, поиск занял: " + (System.nanoTime() - c) + "нс");
            }
            long d = System.nanoTime();
            if (set1.contains(stateNumber)) {
                System.out.println("Поиск в TreeSet: номер найден, поиск занял: " + (System.nanoTime() - d) + "нс");
            } else {
                System.out.println("Поиск в TreeSet: номер не найден, поиск занял: " + (System.nanoTime() - d) + "нс");
            }
        }
    }
}
