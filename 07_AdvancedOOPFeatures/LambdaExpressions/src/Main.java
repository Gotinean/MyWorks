import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        Calendar calendar1 = new GregorianCalendar();
        calendar.set(Calendar.YEAR,2016);
        calendar1.set(Calendar.YEAR,2018);
        Date date = calendar.getTime();
        Date date1 = calendar1.getTime();
        ArrayList<Employee> staff = loadStaffFromFile();
        staff.stream().filter(employee -> employee.getWorkStart().after(date)).
                filter(employee -> employee.getWorkStart().before(date1))
                .mapToInt(Employee::getSalary).max().ifPresent(System.out::println);
    }

//
    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}