import java.util.ArrayList;
import java.util.Scanner;
public class main
{
    public static void main(String[] args)
    {
        String deals;
        String nameOfDeal;
        int dealNumber;
        String editName;
        int deleteDeal;
        System.out.println("Выберите действие и введите одну из комманд: \n ADD \n EDIT \n DELETE \n LIST");
        Scanner scanner = new Scanner(System.in);
        deals = scanner.nextLine();
        ArrayList<String> toDoList = new ArrayList<>();
        toDoList.add("Сходить в магазин");
        toDoList.add("Убрать в доме");
        toDoList.add("Выбросить мусор");
        toDoList.add("Сделать дз Скиллбокс");
        toDoList.add("Найти дракона");
        if (deals.equals("ADD"))
        {
            System.out.println("Введите, какое дело вы хотите добавить ");
            Scanner scanner1 = new Scanner(System.in);
            nameOfDeal = scanner1.nextLine();
            toDoList.add(nameOfDeal);
            for (int j = 0; j<toDoList.size(); j++)
            {
                System.out.println(toDoList.get(j));
            }
        }
        else if (deals.equals("EDIT"))
        {
        System.out.println("Введите порядковый номер дела, которое вы хотите изменить");
        Scanner scanner2 = new Scanner(System.in);
        dealNumber = scanner2.nextInt();
        toDoList.remove(dealNumber);
        System.out.println("Введите, новое задание вместо старого");
        Scanner scanner3 = new Scanner(System.in);
        editName = scanner3.nextLine();
        toDoList.add(dealNumber,editName);
            for (int j = 0; j<toDoList.size(); j++)
            {
                System.out.println(toDoList.get(j));
            }
        }
        else if (deals.equals("DELETE"))
        {
            System.out.println("Введите порядковый номер дела, которое вы хотите удалить");
            Scanner scanner4 = new Scanner(System.in);
            deleteDeal = scanner4.nextInt();
            toDoList.remove(deleteDeal);
            for (int j = 0; j<toDoList.size(); j++)
            {
                System.out.println(toDoList.get(j));
            }
        }
        else if (deals.equals("LIST"))
        {
            for (int j = 0; j<toDoList.size(); j++)
            {
                System.out.println(toDoList.get(j));
            }
        }
        else {
            System.out.println("Введите команду корректно");
        }
    }
}
