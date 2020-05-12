package Hire;

public class Operator extends Company implements Employee {
    @Override
    public double getMonthSalary(double amount) {
        return 20000;
    }
}
