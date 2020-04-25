package Banking;
public class PaymentAccount {
    int id;
    String name;
    double balance = 1000;


    public void add (int amount){
        balance = balance + amount;
    }

    public void withdraw(double amount){
        if (amount <= balance){
        balance = balance - amount;}
        else {balance = -1;}
    }

    public double getBalance(){
        return balance;
    }


}