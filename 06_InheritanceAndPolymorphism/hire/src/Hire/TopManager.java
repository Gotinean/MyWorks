package Hire;

public class TopManager implements Employee {
    double salary;
    Company company = new Company();
    public TopManager(){
        int rate = 50000;
        double income = company.getIncome();
        if(income > 10000000){
            salary = rate + rate*1.5;
        }
        else {
            salary = rate;
        }
    }
    @Override
    public double getMonthSalary() {
        return salary;
    }

}


