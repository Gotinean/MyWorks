import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class SampleTestBank extends TestCase {
    Bank bank;
    Random random;
    int numOfAccount = 10;
    final int threadCount = 10;
    final int transfersPerThread = 10000;
    ArrayList<Account> accounts = new ArrayList<>();

    @Override
    protected void setUp() {
      bank = new Bank();
      random = new Random();
      for (int i = 0; i < numOfAccount; i++) {
        String accNumber = "0000" + i;
        Account account = new Account(100000, accNumber);
        accounts.add(account);
        bank.createAccount(account.getAccNumber(), account.getMoney());
      }

    }

    @Test
    public void testBank() throws InterruptedException {
      long sumOfMoney = bank.getAccounts().values().stream().mapToLong(Account::getMoney).sum();
      ArrayList<Thread> threads = new ArrayList<>();
      for (int i = 0; i < threadCount; i++) {
        threads.add(new Thread(() -> {
          for (int j = 0; j < transfersPerThread; j++) {
            Account from = accounts.get(random.nextInt(accounts.size()));
            Account to = accounts.get(random.nextInt(accounts.size()));
            int sum = random.nextInt(50050); //сумма уменьшена для нечастого срабатывания фрода
              try {
                  bank.transfer(from.getAccNumber(), to.getAccNumber(), sum);
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

      long result = bank.getAccounts().values().stream().mapToLong(Account::getMoney).sum();
      System.out.println(sumOfMoney + "\n" + result);
      //System.out.println(bank.count.get());
      System.out.println(accounts.stream().map(account -> account.getAccNumber() + "-" + account.isBlocked()).collect(Collectors.toList()));
      assertEquals(sumOfMoney, result);
    }
}
