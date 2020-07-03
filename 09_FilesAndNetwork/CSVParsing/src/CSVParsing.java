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
            for (String line : lines) {
                String[] fragments = line.split(",", 8);
                if (!fragments[6].matches("\\d+")) {
                    continue;
                }
                String[] name = fragments[5].split("\\s+");
                fragments[7] = fragments[7].replaceAll("\"", "");
                fragments[7] = fragments[7].replaceAll(",", "\\.");
                expenses.add(new Expenses(name[1], Double.parseDouble(fragments[6]), Double.parseDouble(fragments[7])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Общие расходы равны:  " + sumExpenses(expenses));
        System.out.println("Общие доходы равны: " + sumIncome(expenses));
        checkList(expenses);
        for (Expenses expense : expenses) {
            System.out.println(expense.toString());
        }


    }

    public static void checkList(List<Expenses> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i).name.equals(list.get(j).name)) {
                    double sumIncome = list.get(i).incomeValue + list.get(j).incomeValue;
                    double sumExpenses = list.get(i).expensesValue + list.get(j).expensesValue;
                    list.get(i).incomeValue = sumIncome;
                    list.get(i).expensesValue = sumExpenses;
                    list.remove(j);
                }
            }
        }
    }

    public static double sumIncome(List<Expenses> list) {

        double sumIncome = 0;

        for (int j = 1; j < list.size(); j++) {
            sumIncome += list.get(j).incomeValue;
        }

        return sumIncome;
    }

    public static double sumExpenses(List<Expenses> list) {
        double sumExpenses = 0;
        for (int j = 1; j < list.size(); j++) {
            sumExpenses += list.get(j).expensesValue;
        }
        return sumExpenses;
    }
}
