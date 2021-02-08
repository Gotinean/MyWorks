import lombok.SneakyThrows;

import java.util.List;

public class Transaction implements Runnable {
    private List<Account> list;
    private Bank bank;
    public Transaction(List<Account> list, Bank bank) {
        this.list = list;
        this.bank = bank;
    }
    @SneakyThrows
    @Override
    public void run() {
        while (true){
            int firstAccount = (int)(Math.random() * 100);
            int secondAccount = (int)(Math.random() * 100);
            long transferMoney = (int)(Math.random() * 70000);
            if(firstAccount != secondAccount){
                bank.transfer(list.get(firstAccount),list.get(secondAccount),transferMoney);
                System.out.println(bank.getBalance(list));
            }

        }

    }
}
