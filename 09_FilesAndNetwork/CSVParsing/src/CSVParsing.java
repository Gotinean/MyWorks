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
                    name[1] = name[1].replaceAll("\\d", "");
                name[1] = name[1].replaceAll("[\\W]", " ");
                name[1] = name[1].replaceAll("\\s{2,}", " ");
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
        System.out.println(expenses.stream().mapToDouble(e-> e.expensesValue).sum());
        checkList(expenses);
        System.out.println("Суммарный расход по каждой из компаний");
        for (Expenses expense : expenses) {
            System.out.println(expense.toString());
        }



    }

    public static void checkList(List<Expenses> list) {
        list.sort(new Comparator<Expenses>() {
            @Override
            public int compare(Expenses expenses, Expenses t1) {
                return Integer.compare(expenses.name.length(), t1.name.length());
            }
        });
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < list.size(); j++){
                    if(i != j && list.get(i).equals(list.get(j))){
                        list.get(i).expensesValue += list.get(j).expensesValue;
                        list.remove(j);
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
        double sumExpenses = list.get(0).expensesValue;
        for (int j = 0; j < list.size(); j++) {
            sumExpenses += list.get(j).expensesValue;
        }
        return sumExpenses;
    }
}
