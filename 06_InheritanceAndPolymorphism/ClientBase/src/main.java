import Client.*;

import java.util.Scanner;

public class main
{
    public static void main(String[] args) {
        Client physical = new Physical();
        Client entity = new Entity();
        Client individual = new Individual();
        System.out.println("Вас приветствует ЛучшийБанк! Если вы физическое лицо, то Вам доступны команды: Put,With,Balance \n " +
                "Если вы юридическое лицо, то Вам доступны команды EPut, EWith, EBalance \n" +
                "Если вы ИП, то Вам доступны команды IPut, IWith, IBalance" );
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
            else {
                action = command;
            }

            if(action.equals("Balance")){
                physical.getBalance();
                System.out.println("Ваш баланс составляет: " + physical.getBalance());
            }
            else if (action.equals("With")){
                double a = physical.getBalance();
                if (a < nr){
                    System.out.println("Недостаточно средств на вашем счете");
                }
                else {
                    System.out.println("Вы сняли деньги со своего счета, Ваш баланс составляет: " + physical.WithdrawMoney(nr));
                }
            }
            else if (action.equals("Put")){
                System.out.println("Вы пополнили Ваш счет, Ваш баланс составляет: " + physical.Putmoney(nr));
            }
            else if (action.equals("EPut")){
                System.out.println("Вы пополнили Ваш счет, Ваш баланс составляет: " + entity.Putmoney(nr));
            }
            else if (action.equals("EWith")){
                double a = entity.getBalance();
                if (a < nr){
                    System.out.println("Недостаточно средств на вашем счете");
                }
                else {
                    System.out.println("Вы сняли деньги со своего счета, Ваш баланс составляет: " + entity.WithdrawMoney(nr));
                }
            }
            else if (action.equals("EBalance")){
                System.out.println("Ваш баланс составляет: " + entity.getBalance());
            }
            else if (action.equals("IPut")) {
                System.out.println("Вы пополнили счет, Ваш баланс составляет: " + individual.Putmoney(nr));
            }
            else if (action.equals("IWith")){
                double a = individual.getBalance();
                if (a < nr){
                    System.out.println("Недостаточно средств на вашем счете");
                }
                else {
                    System.out.println("Вы сняли деньги со своего счета, Ваш баланс составляет: " + individual.WithdrawMoney(nr));
                }
            }
            else if (action.equals("IBalance")){
                System.out.println("Ваш баланс составляет: " + individual.getBalance());
            }
            else {
                System.out.println("Вы ввели неверную команду, попробуйте снова");
            }


        }
    }
}
