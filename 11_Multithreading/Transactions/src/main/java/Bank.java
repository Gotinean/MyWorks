import java.util.*;

public class Bank
{
    private HashMap<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();
    private final Boolean key = false;
    final Account src = new Account();
    final Account dst = new Account();

//    public void setSrcNum(String srcNum) {
//        this.srcNum = srcNum;
//    }
//
//    public void setDstNum(String dstNum) {
//        this.dstNum = dstNum;
//    }

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
        } else {
            if (amount > 50000) {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    accounts.get(fromAccountNum).setBlocked(true);
                    accounts.get(toAccountNum).setBlocked(true);
                } else synchronized (src){
                    accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                    accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
                }
            } else synchronized (dst){
                accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
                accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
            }
        }
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
