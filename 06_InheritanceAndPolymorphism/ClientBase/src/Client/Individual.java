package Client;

public class Individual extends Client
{
    public double putMoney(double amount) {
        if(amount < 1000){
           return super.putMoney(amount) * 0.99;
        }
        else {
            return super.putMoney(amount) * 0.995;
        }
    }
}
