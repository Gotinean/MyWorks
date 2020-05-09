package Hire;

public class Manager extends Company implements Employee
{
    @Override
    public double getMonthSalary(double amount) {
        rate = 40000;
        salary = rate + amount*0.05;
        return salary;
    }
}

