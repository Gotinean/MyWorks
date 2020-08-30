package Banking;
import java.util.Calendar;

public class Depositary extends PaymentAccount
{
    public Calendar date;
    public void add(int amount) {
        super.add(amount);
        this.date = Calendar.getInstance();
        this.date.add(Calendar.MONTH, 1);

    }


    public void withdraw(double amount) {
        if(Calendar.getInstance().compareTo(date) > 0){
        super.withdraw(amount);}
        else {
            super.withdraw(amount - amount);
        }

    }

}
