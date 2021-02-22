import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class SampleTestBank extends TestCase {
    Bank bank;
    Random random;
    int numOfAccount = 10;
    final int threadCount = 10;
    final int transfersPerThread = 10000;
    ArrayList<Account> accounts = new ArrayList<>();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();
    private void takeLocks(Lock lock1, Lock lock2) throws InterruptedException {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;
        while (true) {
            try {
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {
                if (firstLockTaken && secondLockTaken) {
                    return;
                }
                if (firstLockTaken) {
                    lock1.unlock();
                }
                if (secondLockTaken) {
                    lock2.unlock();
                }
            }
            Thread.sleep(1);
        }
    }


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
                  takeLocks(lock1,lock2);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              try {
                  bank.transfer(from.getAccNumber(), to.getAccNumber(), sum);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              finally {
                  lock1.unlock();
                  lock2.unlock();
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
      System.out.println(bank.count.get());
      System.out.println(Arrays.toString(bank.getAccounts().values().stream().map(Account::isBlocked).toArray()));
      System.out.println(accounts.stream().map(account -> account.getAccNumber() + "-" + account.isBlocked()).collect(Collectors.toList()));
      assertEquals(sumOfMoney, result);
    }
}
