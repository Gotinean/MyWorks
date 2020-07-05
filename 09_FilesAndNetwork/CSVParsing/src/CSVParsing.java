import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVParsing {
    private static List<Expenses> expenses = new ArrayList<>();
    private static List<Integer> income = new ArrayList<>();
    public static void main(String[] args) {
        String bankStatement = "data/movementList.csv";
        File file = new File(bankStatement);
        try {
            List<String> lines = Files.readAllLines(Paths.get(bankStatement));
            lines.stream().map(line
                    -> line.split(",", 8)).filter(fragments
                    -> fragments[6].matches("\\d+")).forEach(fragments
                    -> { String[] name = fragments[5].split("\\s{3,}");
                fragments[7] = fragments[7].replaceAll("\"", "");
                fragments[7] = fragments[7].replaceAll(",", "\\.");
                expenses.add(new Expenses(name[1], Double.parseDouble(fragments[7])));
                income.add(Integer.parseInt(fragments[6]));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Общие расходы равны:  " + sumExpenses(expenses));
        System.out.println("Общие доходы равны: " + sumIncome(income));
        checkList(expenses);
        System.out.println("Суммарный расход по каждой из компаний");
        for (Expenses expense : expenses) {
            System.out.println(expense.toString());
        }
        System.out.println(sumExpenses(expenses));


    }

    public static void checkList(List<Expenses> list) {
        expenses.sort(new Comparator<Expenses>() {
            @Override
            public int compare(Expenses expenses, Expenses t1) {
                return Integer.compare(expenses.name.length(), t1.name.length());
            }
        });
            for(int i = 0; i < expenses.size(); i++){
                for(int j = 0; j < expenses.size(); j++){
                    if(i != j && expenses.get(i).equals(expenses.get(j))){
                        expenses.get(i).expensesValue += expenses.get(j).expensesValue;
                        expenses.remove(j);
                        j--;
                    }
                }

            }

    }


    public static double sumIncome(List<Integer> list) {

        double sumIncome = 0;

        for (int j = 1; j < list.size(); j++) {
            sumIncome += income.get(j);
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
