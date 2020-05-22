package Hire;

import java.util.Random;

public class Manager implements Employee
{
    private double salary;
    private int sale;
    public double getSale() {
        this.sale = (int) (Math.random() * 400000);
        return sale ;
    }
    public Manager(){
        int rate = 25000;
        this.salary = sale * 0.05 + rate;
    }
    @Override
    public double getMonthSalary() {
        return salary;
    }

}

