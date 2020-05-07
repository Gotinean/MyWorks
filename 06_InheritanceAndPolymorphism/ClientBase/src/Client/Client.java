package Client;

public abstract class Client
{
    public double balance = 0;
    private String client;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public double getBalance() {
        return balance;
    }
    public double Putmoney(double amount){
        balance = balance + amount;
        return balance;
    }
    public double WithdrawMoney(double amount){
        if (amount > balance){
            return balance;
        }
        else {
            balance = balance - amount;
            return balance;
        }
    }
}
