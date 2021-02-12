import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final Random random = new Random();
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        List<String> accounts = new ArrayList<>();
        long totalBalance = 0;
        for(int i = 1; i <= 10; i++) {
            bank.createAccount(String.valueOf(i), random.nextInt(200000));
            System.out.println("Счет клиента "+i+" равен: "+bank.getBalance(String.valueOf(i)));
            totalBalance = totalBalance + bank.getBalance(String.valueOf(i));
        }
        System.out.println("Суммма всех счетов: " + totalBalance);
        for(int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        int a = (int) (Math.random() * 10);
                        int b = (int) (Math.random() * 10);
                        if (a == 0) {
                            a++;
                        }
                        if (b == 0) {
                            b++;
                        }
                        String firstAcc = String.valueOf(a);
                        String secondAcc = String.valueOf(b);
                        long amount = random.nextInt(51000);
                        for (int j = 0; j < 10000; j++) {
                            if (!firstAcc.equals(secondAcc)) {
                                try {
                                    bank.transfer(firstAcc, secondAcc, amount);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

            });

            thread.start();
            thread.join();
        }
        long finalTotalBalance = 0;
        for(int i = 1; i <= 10; i++){
            bank.getBalance(String.valueOf(i));
            finalTotalBalance = finalTotalBalance + bank.getBalance(String.valueOf(i));
        }
        System.out.println(finalTotalBalance);
        //System.out.println(totalBalance);
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
    }
}
