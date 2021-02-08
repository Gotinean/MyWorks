import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Account> accounts = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Account.makeAccounts(i, accounts);
        }
        long totalAmount = 0;
        for(int i = 0; i < accounts.size(); i++){
            totalAmount = totalAmount + accounts.get(i).getMoney();
        }
        Bank bank = new Bank();
        System.out.println(bank.getBalance(accounts));
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new Transaction(accounts,bank));
        executorService.shutdown();
    }
}
