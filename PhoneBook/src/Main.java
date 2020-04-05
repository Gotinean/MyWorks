/*
TODO:
 Написать программу, которая будет работать как телефонная книга: если пишем новое имя,
 просит ввести номер телефона и запоминает его, если новый номер телефона — просит ввести имя и тоже запоминает.
 Если вводим существующее имя или номер телефона, программа должна выводить всю информацию о контакте.
 При вводе команды LIST программа должна печатать в консоль список всех абонентов в алфавитном порядке с номерами.
 */

import java.util.Scanner;
import java.util.TreeMap;

public class Main
{
    public static void main(String[] args)
    {
        TreeMap<String, String> phoneBook = new TreeMap<>();
        while (true)
        {
            Scanner command = new Scanner(System.in);
            String fullName = command.nextLine();
            if(fullName.equals("LIST")) {
                printMap(phoneBook);
                continue;
            }

            if (Character.isDigit(fullName.charAt(0))){
            System.out.println("Введите пожалуйста имя владельца телефона");
            Scanner command2 = new Scanner(System.in);
            String newName = command2.nextLine();

            phoneBook.put(newName,fullName);
            }
            else if(Character.isAlphabetic(fullName.charAt(0))) {
                System.out.println("Введите пожалуйста номер телефона данного человека");
                Scanner command3 = new Scanner(System.in);
                String newNumber = command3.nextLine();
                phoneBook.put(fullName,newNumber);
            }
            else {
                phoneBook.get(fullName);
            }

        }

    }
    public static void printMap(TreeMap<String, String> map)
    {
       for (String key : map.keySet()) {
           System.out.println(key + " " + map.get(key));
       }
    }

}
