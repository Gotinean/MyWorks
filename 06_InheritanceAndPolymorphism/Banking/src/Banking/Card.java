package Banking;

import com.sun.tools.javac.Main;

public class Card extends PaymentAccount {
    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        balance = balance - amount - (amount * (0.01));
    }
}
