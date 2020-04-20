import Banking.PaymentAccount;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
public class Main
{
    public static void main(String[] args) {
        System.out.println("Если вы хотите пополнить счет введите команду 'PUT' и колличество денег \n" +
                " если вы хотите снять деньги со своего счета, введите команду 'WITHDRAW' и колличество денег \n" +
                " если вы хотите посмотреть остаток по счету введите команду 'BALANCE' ");
        TreeMap<Integer, Integer> bank = new TreeMap<>();
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 2);
            String action = parts[0];
            int nr = 0;
            String text = null;
            if (parts.length > 1) {
                if (Character.isDigit(parts[1].charAt(0))) {
                    String subParts[] = parts[1].split(" ", 2);
                    nr = Integer.parseInt(subParts[0]);
                    if (subParts.length > 1) {
                        text = subParts[1];
                    }
                } else {
                    text = parts[1];
                }
            }
            if (action.equals("PUT")) {
                int i = bank.get(1);
                i = i + nr;
                bank.put(1, i);
                System.out.println("Вы пополнили счет, баланс вашего счета составляет: " + (bank.get(1)));
            } else if (action.equals("WITHDRAW")) {
                int i = bank.get(1);
                i = i - nr;
                bank.put(1, i);
                System.out.println("Вы сняли деньги с вашего счета, баланс вашего счета составляет: " + (bank.get(1)));
            } else if (action.equals("BALANCE")) {
                System.out.println("Баланс вашего счета составляет: " + bank.get(1));
            } else {
                System.out.println("Вы ввели неверную команду, попробуйте снова");
            }
        }

}
}
