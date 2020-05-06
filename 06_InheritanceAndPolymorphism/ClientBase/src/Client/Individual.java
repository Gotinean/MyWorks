package Client;

public class Individual extends Client
{
    @Override
    public double getBalance() {
        return super.getBalance();
    }

    @Override
    public double getPutmoney(double amount) {
        if(amount < 1000){
            balance = (balance + amount) * 0.99;
        return balance;
        }
        else {
            balance = (balance + amount) * 0.995;
            return balance ;
        }
    }

    @Override
    public double getWithdrawMoney(double amount) {
        return super.getWithdrawMoney(amount);
    }
}
