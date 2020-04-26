package Banking;
public class PaymentAccount {
    double balance = 0;
    public void add (int amount){
        balance = balance + amount;
    }

    public void withdraw(double amount){
        if (amount <= balance){
        balance = balance - amount;}
        else {balance = balance;}
    }

    public double getBalance(){
        return balance;
    }


}