import lombok.SneakyThrows;
import lombok.Synchronized;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Bank
{
    private final Random random = new Random();
    private Bank bank = new Bank();
    private long totalAmount;
    public long getTotalAmount() {
        return totalAmount;
    }
    private List<Account> list;

    public static void main(String[] args) throws InterruptedException {
        new Bank().main();
    }

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
    public synchronized void transfer(Account fromAccount, Account toAccount, long amount) throws InterruptedException {
        if (fromAccount.isBlocked() | toAccount.isBlocked()) {
            System.out.println("Транзакции невозможны. Счет одного из участников заблокирован," +
                    " обратитесь в центральный офис банка");
        } else {
            if (amount > 50000) {
                isFraud(fromAccount, toAccount, amount);
                if (!isFraud(fromAccount, toAccount, amount)) {
                    if (fromAccount.getMoney() > amount) {
                        fromAccount.setMoney(fromAccount.getMoney() - amount);
                        toAccount.setMoney(toAccount.getMoney() + amount);
                        Thread.sleep(1005);
                    } else {
                        fromAccount.setBlocked(true);
                        toAccount.setBlocked(true);
                        System.out.println("У вас недостаточно средств на счету для осуществления транзакции \n " +
                                "Доступная сумма: " + fromAccount.getMoney() + " \n Запрашиваемый перевод: " + amount);
                    }
                } else {
                    System.out.println("Данная транзакция показалась нам подозрительной, \n" +
                            " пожалуйста обратитесь в отделение банка для осуществления перевода");
                }
            } else {
                if (fromAccount.getMoney() > amount) {
                    fromAccount.setMoney(fromAccount.getMoney() - amount);
                    toAccount.setMoney(toAccount.getMoney() + amount);
                } else {
                    System.out.println("У вас недостаточно средств на счету для осуществления транзакции \n " +
                            "Доступная сумма: " + fromAccount.getMoney() + " \n Запрашиваемый перевод: " + amount);
                }
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
    public synchronized void makeAccounts(int accId, List<Account> list) throws InterruptedException {
        int a = 0;
        long amount = random.nextInt(200000);
        String b = String.valueOf(accId);
        Account account = new Account(amount, b, false);
        list.add(account);

    }
    public synchronized void work(Bank bank) throws InterruptedException {
        int firstAccount = random.nextInt(10);
        int secondAccount = random.nextInt(10);
        long transferMoney = random.nextInt(70000);
        if (firstAccount != secondAccount) {
            bank.transfer(list.get(firstAccount), list.get(secondAccount), transferMoney);
            System.out.println(bank.getBalance(list));
        }
    }

    public void main() throws InterruptedException {
        long before = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            int finalI = i;
            threads.add(new Thread(()-> {
                try {
                    makeAccounts(finalI,list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 1000; j++) {
                    try {
                        work(bank);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

    }

}
