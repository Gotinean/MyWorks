package Client;

public class Entity extends Client
{
    public double WithdrawMoney(double amount) {
        if (amount > balance){
            return balance;
        }
        else {
            balance = (balance - amount) * 0.99;
            return balance;
        }
    }
}
