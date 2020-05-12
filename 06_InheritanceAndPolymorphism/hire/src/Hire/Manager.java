package Hire;

import java.util.Random;

public class Manager extends Company implements Employee
{
    @Override
    public double getMonthSalary(double amount) {
        double sale = Math.random()* 500000;
        amount = sale * 0.05;
        return amount;
    }
}

