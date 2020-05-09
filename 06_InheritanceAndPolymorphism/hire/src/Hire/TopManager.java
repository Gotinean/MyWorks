package Hire;

public class TopManager extends Company implements Employee {
    @Override
    public double getMonthSalary(double amount) {
        rate = 80000;
        if(amount > 10000000)
        salary = rate*1.5;
        else salary = rate;
        return salary;
    }
}
