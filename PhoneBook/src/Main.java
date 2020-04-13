/*
TODO:
 Написать программу, которая будет работать как телефонная книга: если пишем новое имя,
 просит ввести номер телефона и запоминает его, если новый номер телефона — просит ввести имя и тоже запоминает.
 Если вводим существующее имя или номер телефона, программа должна выводить всю информацию о контакте.
 При вводе команды LIST программа должна печатать в консоль список всех абонентов в алфавитном порядке с номерами.
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        TreeMap<String, String> phoneBook = new TreeMap<>();
        while (true) {
            Scanner command = new Scanner(System.in);
            String fullName = command.nextLine();
            if (fullName.equals("LIST")) {
                printMap(phoneBook);
                continue;
            }

            String[] parts = fullName.split(" ");
            if (parts.length > 1) {
                if (parts[0].matches("\\d+")) {
                    phoneBook.put(parts[1], parts[0]);
                } else phoneBook.put(parts[0], parts[1]);
            } else {
                if (fullName.matches("\\d+")) {
                    if (phoneBook.containsValue(fullName)) {
                        for (Map.Entry<String, String> item : phoneBook.entrySet()) {
                            System.out.println(item.getKey() + ": " + item.getValue());
                        }
                        System.out.println("Данный номер принадлежит контакту: " + phoneBook.keySet());
                    } else {
                        System.out.println("Введите пожалуйста имя владельца телефона");
                        Scanner command2 = new Scanner(System.in);
                        String newName = command2.nextLine();
                        phoneBook.put(newName, fullName);
                    }
                } else if (fullName.matches("\\D+")) {
                    if (phoneBook.containsKey(fullName)) {
                        System.out.println("Данная личность имеет номер: " + phoneBook.get(fullName));
                    } else {
                        System.out.println("Введите пожалуйста номер телефона данного человека");
                        Scanner command3 = new Scanner(System.in);
                        String newNumber = command3.nextLine();
                        phoneBook.put(fullName, newNumber);
                    }
                } else {
                    System.out.println("Введнные вами имя или номер неправильного формата. Попробуйте ещё раз.");
                }
            }
        }
    }

    public static void printMap(TreeMap<String, String> map) {
        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }

    public static int linearSearch(TreeMap<String, String> map, String elementToSearch) {

        for (int index = 0; index < map.size(); index++) {
            if (map.containsKey(elementToSearch))
                return index;
        }
        return -1;
    }

}
