public class Expenses {
    String name = null;
    double incomeValue = 0;
    double expensesValue = 0;
    public Expenses(String name, double incomeValue, double expensesValue){
        this.name = name;
        this.incomeValue = incomeValue;
        this.expensesValue = expensesValue;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "Имя операции: " + name + '\'' +
                ", Доход: " + incomeValue +
                ", Расход: " + expensesValue +
                '}';
    }
}
