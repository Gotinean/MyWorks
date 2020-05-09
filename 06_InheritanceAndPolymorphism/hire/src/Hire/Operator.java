package Hire;

public class Operator extends Company implements Employee {
    @Override
    public double getMonthSalary(double amount) {
        rate = 35000;
        return rate;
    }
}
