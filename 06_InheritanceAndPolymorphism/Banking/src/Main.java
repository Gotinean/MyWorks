import Banking.Card;
import Banking.Depositary;
import Banking.PaymentAccount;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Если вы хотите пополнить счет введите команду 'PUT' и колличество денег \n" +
                " если вы хотите снять деньги со своего счета, введите команду 'WITHDRAW' и колличество денег \n" +
                " если вы хотите посмотреть остаток по счету введите команду 'BALANCE' \n" +
                " если вы хотите посмотреть остаток по счету введите команду 'CARDBALANCE' \n" +
                " если вы хотите снять деньги со своей карты, введите команду 'CARDWITHDRAW' \n" +
                " если вы хотите пополнить счет своей карты, введите команду 'CARDPUT' \n" +
                " если вы хотите пополнить свой депозитарный счет, введите команду 'PUTDEPOSITORY' \n" +
                " если вы хотите посмотреть остаток по счету введите команду 'DEPOSITBALANCE' \n" +
                " если вы хотите снять деньги с депозитарного счета, введите команду 'WITHDRAWDEPOSITORY' \n");
        PaymentAccount account = new PaymentAccount();
        Depositary deposit = new Depositary();
        Card card = new Card();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            String action = null;
            int nr = 0;
            String[] parts = command.split(" ", 2);
            if (parts.length > 1){
            action = parts[0];
            nr = Integer.parseInt(parts[1]);
            }
            else {action = command;}
            if (action.equals("PUT")) {
                account.add(nr);
                System.out.println("Вы пополнили счет. Баланс вашего счета: " + account.getBalance());
            } else if (action.equals("WITHDRAW")) {
                double oldBalance = account.getBalance();
                account.withdraw(nr);
                if (account.getBalance() == oldBalance) {
                    System.out.println("На вашем счете недостаточно средств.");
                }
                else {
                    System.out.println("Вы сняли деньги со своего счета. Баланс счета: " + account.getBalance());
                }
            } else if (action.equals("BALANCE")) {
                System.out.println("Баланс вашего счета: "+ account.getBalance());
            } else if (action.equals("CARDPUT")) {
                card.add(nr);
                System.out.println("Вы пополнили свой карточный счет. Баланс на карте: " + card.getBalance());
            } else if (action.equals("CARDWITHDRAW")) {
                card.withdraw(nr);
                System.out.println("Вы сняли денег с карты. Баланс на карте: " + account.getBalance());
            } else if (action.equals("CARDBALANCE")) {
                System.out.println("Баланс счета на вашей карте:" + card.getBalance());
            } else if (action.equals("PUTDEPOSITORY")) {
                deposit.add(nr);
                System.out.println("Вы пополнили свой депозит. Баланс счета депозита: " + deposit.getBalance());
            } else if (action.equals("WITHDRAWDEPOSITORY")) {
                double a = deposit.getBalance();
                deposit.withdraw(nr);
                if(deposit.getBalance() == a){
                    System.out.println("Вы не можете снять деньги с депозитарного счета на данный момент");
                }
                else {
                System.out.println("Вы сняли деньги с депозитарного счета. Баланс счета депозита: " + account.getBalance());}
            } else if (action.equals("DEPOSITBALANCE")) {
                System.out.println("Баланс Депозитного счета: " + deposit.getBalance());

            } else {
                System.out.println("Вы ввели неверную команду, попробуйте снова");
            }
        }

    }
}


