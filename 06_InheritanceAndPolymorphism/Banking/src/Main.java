import Banking.PaymentAccount;

import java.util.Scanner;
import java.util.TreeMap;

public class Main
{


            public static void main (String[]args){
            System.out.println("Если вы хотите пополнить счет введите команду 'PUT' и колличество денег \n" +
                    " если вы хотите снять деньги со своего счета, введите команду 'WITHDRAW' и колличество денег \n" +
                    " если вы хотите посмотреть остаток по счету введите команду 'BALANCE' \n" +
                    " если вы хотите снять деньги со своей карты, введите команду 'CARDWITHDRAW' \n" +
                    " если вы хотите пополнить счет своей карты, введите команду 'CARDPUT' \n" +
                    " если вы хотите пополнить свой депозитарный счет, введите команду 'PUTDEPOSITORY' \n" +
                    " если вы хотите снять деньги с депозитарного счета, введите команду 'WITHDRAWDEPOSITORY' \n");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                String[] parts = command.split(" ", 2);
                String action = parts[0];
                int nr = Integer.parseInt(parts[1]);
                PaymentAccount account = new PaymentAccount();
                if (action.equals("PUT")) {
                    System.out.println(account.add(nr));

                } else if (action.equals("WITHDRAW")) {
                    System.out.println(account.withdraw(nr));

                } else if (action.equals("BALANCE")) {
                    System.out.println(account.getBalance());

                } else {
                    System.out.println("Вы ввели неверную команду, попробуйте снова");
                }
            }

        }
        }


