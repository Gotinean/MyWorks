package Client;

public class Entity extends Client
{
    @Override
    public double withdrawMoney(double amount) {
        return super.withdrawMoney(amount) * 0.99;

    }
}
