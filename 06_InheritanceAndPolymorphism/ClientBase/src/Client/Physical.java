package Client;

public class Physical extends Client
{
    public double balance = 0;

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getPutmoney(double amount) {
        balance = balance + amount;
        return balance;
    }

    @Override
    public double getWithdrawMoney(double amount) {
        if (amount > balance){
            return balance;
        }
        else {
            balance = balance - amount;
            return balance;
        }
    }
}

