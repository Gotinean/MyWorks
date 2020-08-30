import java.util.Objects;

public class Expenses {
    String name = null;
    double expensesValue = 0;
    public Expenses(String name, double expensesValue){
        this.name = name;
        this.expensesValue = expensesValue;
    }

    @Override
    public String toString() {
        return  "Имя операции: " + name +
                "; Расход: " + expensesValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expenses expenses = (Expenses) o;
        return name.equals(expenses.name);
    }
}
