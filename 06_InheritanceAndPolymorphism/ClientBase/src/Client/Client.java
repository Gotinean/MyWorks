package Client;

public abstract class Client
{
    private String client;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public abstract double getBalance();
    public abstract double getPutmoney(double amount);
    public abstract double getWithdrawMoney(double amount);

}
