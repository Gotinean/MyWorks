import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Bank
{
    private long totalAmount;


    public long getTotalAmount() {
        return totalAmount;
    }

    private final Random random = new Random();

    public synchronized boolean isFraud(Account fromAccount, Account toAccount, long amount)
        throws InterruptedException
    {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer(Account fromAccount, Account toAccount, long amount) throws InterruptedException {
        if(amount > 50000){
            isFraud(fromAccount, toAccount, amount);
            if (!isFraud(fromAccount, toAccount, amount)){
                if(fromAccount.getMoney() > amount) {
                    fromAccount.setMoney(fromAccount.getMoney() - amount);
                    toAccount.setMoney(toAccount.getMoney() + amount);
                    Thread.sleep(1005);
                }
                else {
                    System.out.println("У вас недостаточно средств на счету для осуществления транзакции \n " +
                            "Доступная сумма: " + fromAccount.getMoney() + " \n Запрашиваемый перевод: " + amount);
                }
            }
            else {
                System.out.println("Данная транзакция показалась нам подозрительной, \n" +
                        " пожалуйста обратитесь в отделение банка для осуществления перевода");
            }
        }
        else {
            if(fromAccount.getMoney() > amount) {
                fromAccount.setMoney(fromAccount.getMoney() - amount);
                toAccount.setMoney(toAccount.getMoney() + amount);
            }
            else {
                System.out.println("У вас недостаточно средств на счету для осуществления транзакции \n " +
                        "Доступная сумма: " + fromAccount.getMoney() + " \n Запрашиваемый перевод: " + amount);
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(List<Account> list)
    {
        long balance = 0;
        for(int i = 0; i < list.size(); i++){
            balance = list.get(i).getMoney() + balance;
        }
        return balance;
    }

}
