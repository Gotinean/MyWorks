import java.sql.Time;
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
public class Main
{
    public static void main(String[] args) {

            ArrayList<String> list = new ArrayList<>();
            list.addAll(generateList(2000000));
            Collections.sort(list);
            HashSet<String> set = new HashSet<>();
            set.addAll(list);
            TreeSet<String> set1 = new TreeSet<>(list);
            Scanner scanner = new Scanner(System.in);
            String stateNumber = scanner.nextLine();
        while (true) {
            long a = System.currentTimeMillis();
            System.out.println(list.equals(stateNumber));
            System.out.println(System.currentTimeMillis() - a);
            long b = System.currentTimeMillis();
            System.out.println(Collections.binarySearch(list, stateNumber));
            System.out.println(set.contains(stateNumber));
            long c = System.currentTimeMillis();
            System.out.println(set.contains(stateNumber));
            System.out.println(System.nanoTime() - c);
            long d = System.currentTimeMillis();
            System.out.println(set1.contains(stateNumber));
            System.out.println(System.nanoTime() - d);
        }
    }

    private static List<String> generateList(int size) {
        String[] letters = {"C","M","T","B","A","P","O","H","E","Y"};
        String[] number =  {"1","2","3","4","5","6","7","8","9","0"};
        List<String> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int X = (int)Math.floor(Math.random() * letters.length);
            int Y = (int)Math.floor(Math.random() * letters.length);
            int Z = (int)Math.floor(Math.random() * letters.length);
            int N = (int)Math.floor(Math.random() * number.length);
            int R = (int)(Math.random()*199.0);
            String govNumber = letters[X] + number[N] + number[N] + number[N] + letters[Y] + letters[Z] + R;
            numbers.add(govNumber);

        }
        return numbers;
    }
}
