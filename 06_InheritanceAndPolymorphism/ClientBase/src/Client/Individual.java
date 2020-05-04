package Client;

public class Individual extends Client
{
    public double balance = 0;

    @Override
    public double getBalance() {
        return balance;
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
        if (amount > balance){
            return balance;
        }
        else {
            this.balance = balance - amount;
            return balance - amount;
        }
    }
}
