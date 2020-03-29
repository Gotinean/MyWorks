
import java.util.ArrayList;
import java.util.Scanner;
public class main
{
    public static void main(String[] args)
    {
        String command;
        System.out.println("Выберите действие и введите одну из комманд: \n ADD \n EDIT \n DELETE \n LIST");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<>();
        toDoList.add("");
        toDoList.add("Сходить в магазин");
        toDoList.add("Убрать в доме");
        toDoList.add("Выбросить мусор");
        toDoList.add("Сделать дз Скиллбокс");
        toDoList.add("Найти дракона");
        while(true) {
            command = scanner.nextLine();
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
            if (action.equals("ADD"))
            {
                if (nr == 0) {
                    toDoList.add(text);
                    for (int j = 1; j < toDoList.size(); j++) {
                        System.out.println((j) + " " + toDoList.get(j));
                    }
                }
                    else {
                    toDoList.add(nr, text);
                    for (int j = 1; j < toDoList.size(); j++) {
                        System.out.println((j) + " " + toDoList.get(j));
                    }
                }
            }
            else if (action.equals("EDIT"))
            {
                toDoList.set(nr, text);
                for (int j = 1; j<toDoList.size(); j++)
                {
                    System.out.println((j) + " " + toDoList.get(j));
                }
            }
            else if (action.equals("DELETE"))
            {
                
                toDoList.remove(nr);
                toDoList.remove(text);
                for (int j = 1; j<toDoList.size(); j++)
                {
                    System.out.println((j) + " " + toDoList.get(j));
                }
            }
            else if (action.equals("LIST"))
            {
                list(toDoList);
            }
            else {
                System.out.println("Введите команду корректно");
            }
            
        }
    }

    private static void list(ArrayList todo) {
        for (int j = 1; j< todo.size(); j++){
            System.out.println((j) + " " + todo.get(j));
        }
    }

}


