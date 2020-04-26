package Banking;

import java.time.LocalDate;

public class Depositary extends PaymentAccount
{
    LocalDate date = LocalDate.now();
        public void add(int amount) {
        super.add(amount);
            LocalDate date = LocalDate.now();
    }


    public void withdraw(double amount, LocalDate withdrawDate) {
        super.withdraw(amount);
        withdrawDate = date.plusMonths(1);
    }

}
