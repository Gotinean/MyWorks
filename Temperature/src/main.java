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
        for (j=0; j < 30; j++) {
            peopleCount[j] = (minTemperature + ((int) (Math.random() * variableTemperature)));
        }
        int sum = 0;
        for(int i = 0; i < peopleCount.length ; i++) {
            sum = sum + peopleCount[i];
        }
        long numberOfHealthPeople = Arrays.stream(peopleCount)
                .filter(t -> t >= 36 && t <= 37)
                .count();
        System.out.println(sum/30);
        System.out.println("Колличество здоровых людей: " + numberOfHealthPeople );







    }


}
