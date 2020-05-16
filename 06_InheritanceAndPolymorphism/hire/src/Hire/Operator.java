package Hire;

public class Operator implements Employee {
    double salary;
    public Operator()
    {
        this.salary = 20000;
    }
    @Override
    public double getMonthSalary() {
        return salary;
    }
}
