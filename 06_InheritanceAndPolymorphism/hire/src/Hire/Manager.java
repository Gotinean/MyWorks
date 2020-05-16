package Hire;

import java.util.Random;

public class Manager implements Employee
{
    double salary;
    public Manager(){
        int sale = (int) (Math.random()* 10);
        this.salary = sale * 0.05;
    }
    @Override
    public double getMonthSalary() {
        return salary;
    }
}

