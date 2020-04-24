package Banking;
public class PaymentAccount {
    int id;
    String name;
    double balance = 1000;

    public void add (int amount){
        balance = balance + amount;
    }

    public void withdraw(double amount){
        balance = balance - amount;
    }

    public double getBalance(){
        return balance;
    }


}