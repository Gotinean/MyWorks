import java.util.HashSet;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        String command;
        System.out.println("Вы можете посмотреть список существующих e-mail командой LIST \n так же, можете добавить новый e-mail командой ADD ");
        Scanner scanner = new Scanner(System.in);
        HashSet<String> eMail = new HashSet<>();
        eMail.add("qwerty@mail.ru");
        eMail.add("stan@gmail.com");
        eMail.add("zzz@gmail.com");
        eMail.add("cat@bk.ru");
        eMail.add("fiveth@gmail.com");
        while (true){
            command = scanner.nextLine();
            String[] parts = command.split(" ", 2);
            String action = parts[0];
            String text = null;
            if(parts.length > 1)
            {
                text = parts[1];
            }
                if (action.equals("ADD")) {if (text.matches("^(.+)@(.+)$")) {
                    eMail.add(text);
                    for (String email : eMail) {
                        System.out.println(email);
                    }
                }
                else {
                    System.out.println("E-mail введен некорректно, введите верный адрес");
                }
                } else if (action.equals("LIST")) {
                    for (String email : eMail) {
                        System.out.println(email);
                    }
                } else {
                    System.out.println("Введите команду корректно");
                }
        }
    }
}
