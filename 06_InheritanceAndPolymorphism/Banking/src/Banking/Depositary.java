package Banking;

import java.time.LocalDate;
import java.util.Calendar;

public class Depositary extends PaymentAccount
{
    public Calendar date;
    public void add(int amount, Calendar date) {
        super.add(amount);
        this.date.add(Calendar.MONTH, 1);

    }


    public void withdraw(double amount, Calendar date) {
        if(Calendar.getInstance() == this.date){
        balance = balance - amount;}
        else {
            balance = balance - 0;
        }

    }

}
