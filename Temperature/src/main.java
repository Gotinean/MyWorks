import java.util.Arrays;
import java.util.stream.Collectors;

public class main<next>
{
    public static void main(String[] args)
    {
        int [] peopleCount = new int [30];
        int minTemperature = 32;
        int variableTemperature = 8;
        int j;
        int sum = 0;
        for (j=0; j < peopleCount.length; j++) {
            peopleCount[j] = (minTemperature + ((int) (Math.random() * variableTemperature)));
            sum = sum + peopleCount[j];
        }
        long numberOfHealthPeople = Arrays.stream(peopleCount) //Выполняется стрим, который нужен, чтобы работать только с теми элементами, которые удовлетворяют условия описанные ниже
                .filter(t -> t >= 36 && t <= 37) //Фильтруется все пациенты с температурой выше либо равно 36 и в то же время меньше либо равно 37
                .count();
        System.out.println(sum/30);
        System.out.println("Колличество здоровых людей: " + numberOfHealthPeople );







    }


}
