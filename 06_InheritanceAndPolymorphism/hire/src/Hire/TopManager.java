package Hire;

public class TopManager extends Company implements Employee {
    @Override
    public double getMonthSalary(double amount) {
        int rate = 50000;
        if(sales > 10000000){
            amount = rate + rate*1.5;
        }
        else {
            amount = rate;
        }
        return amount;
    }

}
