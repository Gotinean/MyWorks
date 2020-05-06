package Client;

public class Entity extends Client
{
    @Override
    public double getBalance() {
        return super.getBalance();
    }

    @Override
    public double getPutmoney(double amount) {
        return super.getPutmoney(amount);
    }

    @Override
    public double getWithdrawMoney(double amount) {
        if (amount > balance){
            return balance;
        }
        else {
            balance = (balance -amount) * 0.99;
            return balance;
        }
    }
}
