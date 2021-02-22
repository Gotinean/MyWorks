import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank
{
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    AtomicInteger count = new AtomicInteger(0);

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
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
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        if (accounts.get(fromAccountNum).isBlocked() | accounts.get(toAccountNum).isBlocked()) {
            return;
        }
        if(accounts.get(fromAccountNum).getMoney() < amount){
            return;
        }
        if (amount > 50000) {
            if (isFraud(fromAccountNum, toAccountNum, amount)) {
                accounts.get(fromAccountNum).setBlocked(true);
                accounts.get(toAccountNum).setBlocked(true);
            } else {
                accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
            }
            } else {
                accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
            }
        count.incrementAndGet();
    }
    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum)
    {
        return accounts.get(accountNum).getMoney();
    }
    public void createAccount(String accountNumber, long accountMoney){
        Account account = new Account(accountMoney, accountNumber);
        accounts.put(accountNumber,account);
    }
    public Map<String, Account> getAccounts(){
        for(Map.Entry<String, Account> accountEntry : accounts.entrySet()){
            accountEntry.getValue();
            accountEntry.getKey();
        }
        return accounts;
    }



}
