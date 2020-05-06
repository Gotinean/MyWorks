package Client;

public class Physical extends Client
{
    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getPutmoney(double amount) {
        return super.getPutmoney(amount);
    }

    @Override
    public double getWithdrawMoney(double amount) {
        return super.getWithdrawMoney(amount);
    }
}

