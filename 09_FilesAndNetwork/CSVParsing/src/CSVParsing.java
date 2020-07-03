import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVParsing {
    private static String bankStatement = "data/movementList.csv";
    public static List<Expenses> expenses = new ArrayList<>();
    public static void main(String[] args) {
        File file = new File(bankStatement);
        try {
            List<String> lines = Files.readAllLines(Paths.get(bankStatement));
            for(String line : lines){
                String[] fragments = line.split(",", 8 );
                if(!fragments[6].matches("\\d")){
                    continue;
                }
                String[] name = fragments[5].split("\\s+");
                fragments[7] = fragments[7].replaceAll("\"", "");
                expenses.add(new Expenses(name[1], Double.parseDouble(fragments[6]), Double.parseDouble(fragments[7])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkList(expenses);
        for(Expenses expense : expenses){
            System.out.println(expense);
        }

    }
    public static void checkList(List<Expenses> list){
        for(int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).name.equals(expenses.get(i + 1).name)) {
                double sumIncome = expenses.get(i).incomeValue + expenses.get(i + 1).incomeValue;
                double sumExpenses = expenses.get(i).expensesValue + expenses.get(i+1).expensesValue;
                expenses.get(i).incomeValue = sumIncome;
                expenses.get(i).expensesValue = sumExpenses;
                expenses.remove(i + 1);
            }
        }

    }
}
