package Client;

public class Individual extends Client
{
    public double Putmoney(double amount) {
        if(amount < 1000){
            balance = (balance + amount) * 0.99;
        return balance;
        }
        else {
            balance = (balance + amount) * 0.995;
            return balance ;
        }
    }
}
