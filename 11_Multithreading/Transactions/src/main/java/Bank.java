import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Bank implements Runnable {
    Random random = new Random();
    private long bankAmount;
    private List<Account> accountList = new ArrayList<>();

    public long getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(long bankAmount) {
        this.bankAmount = bankAmount;
    }

    public synchronized boolean isFraud() throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }
    public synchronized void transfer(Account fromAccount, Account toAccount, long amount) throws InterruptedException {
        if(fromAccount.isBlocked() | toAccount.isBlocked()){
            System.out.println("Транзакция невозможна, один из аккаунтов в блоке. Обратитесь в банк");
        }
        else {
            if(amount > 50000){
                fromAccount.setBlocked(isFraud());
                toAccount.setBlocked(isFraud());
                if(isFraud()){
                    System.out.println("Подозрительная транзакция. Для совершения транзакции обратитесь в банк.");
                }
                else {
                    if(fromAccount.getMoneyAmount() > amount) {
                        fromAccount.setMoneyAmount(fromAccount.getMoneyAmount() - amount);
                        toAccount.setMoneyAmount(toAccount.getMoneyAmount() + amount);
                    }
                    else {
                        System.out.println("Недостаточно средств для перевода");
                    }
                }

            }
            else {
                if(fromAccount.getMoneyAmount() > amount) {
                    fromAccount.setMoneyAmount(fromAccount.getMoneyAmount() - amount);
                    toAccount.setMoneyAmount(toAccount.getMoneyAmount() + amount);
                }
                else {
                    System.out.println("Недостаточно средств для перевода");
                }
            }
        }
    }
    public void createAccountList (List<Account> list){
        String newAccount = "newAccount";
        Account account = new Account(random.nextInt(200000),newAccount);
        list.add(account);
    }

    public void main(Bank bank) throws InterruptedException {
        List<Account> list = new ArrayList<>();
        long totalAmount = 0;
        for(int i = 0; i < 10; i++) {
            bank.createAccountList(list);
            totalAmount = list.get(i).getMoneyAmount() + totalAmount;
        }
        bank.setBankAmount(totalAmount);
        System.out.println("Остаток в банке до начала транзакций: " + bank.bankAmount
                + "=======================================================");
        for(int i = 0; i < 1000; i++) {
            int firstAccount = random.nextInt(10);
            int secondAccount = random.nextInt(10);
            int amount = random.nextInt(70000);
            if(firstAccount != secondAccount) {
                bank.transfer(list.get(firstAccount), list.get(secondAccount), amount);
            }
        }
        long totalAmountAfterTransactions = 0;
        for(int i = 0; i < 10; i++) {
            totalAmountAfterTransactions = list.get(i).getMoneyAmount() + totalAmountAfterTransactions;
        }
        bank.setBankAmount(totalAmountAfterTransactions);
        System.out.println("Остаток в банке после совершения всех транзакций составляет: "
                +bank.bankAmount + " ---------------------------------");
    }

    @Override
    public void run() {
        Bank bank = new Bank();
        try {
            main(bank);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i++){
            executorService.submit(new Bank());
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
    }
}
