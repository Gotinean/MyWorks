package Hire;

public class Manager implements Employee {
    private double salary;
    private double sale;

    public double getSale() {
        return sale;
    }

    public Manager(double sale1) {
        this.sale = (double) (sale1 * Math.random());
        int rate = 25000;
        this.salary = sale * 0.05 + rate;
    }

    @Override
    public double getMonthSalary() {
        return salary;
    }

}

