package Banking;

import com.sun.tools.javac.Main;

public class Depositary extends PaymentAccount
{
    double balance = 500;
    @Override
    public void add(int amount) {
        super.add(amount);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
    }

}
