package Hire;

public class Operator implements Employee {
    private double salary;

    public Operator() {
        this.salary = 20000;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

    @Override
    public double getSale() {
        return 0;
    }
}
