import java.util.Arrays;
import java.util.stream.Collectors;

public class main<next>
{
    public static void main(String[] args)
    {
        int [] peopleCount = new int [30];
        int minTemperature = 32;
        int variableTemperature = 8;
        peopleCount[0] = (minTemperature + ((int) (Math.random() * variableTemperature)));
        peopleCount[1] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[2] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[3] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[4] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[5] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[6] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[7] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[8] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[9] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[10] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[11] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[12] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[13] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[14] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[15] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[16] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[17] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[18] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[19] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[20] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[21] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[22] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[23] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[24] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[25] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[26] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[27] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[28] = minTemperature + ((int) (Math.random() * variableTemperature));
        peopleCount[29] = minTemperature + ((int) (Math.random() * variableTemperature));
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
