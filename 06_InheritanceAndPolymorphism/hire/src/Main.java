import Hire.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> managers = new ArrayList<Employee>();
        managers.add(new Manager());
        managers.add(new Operator());
        managers.add(new TopManager());
    }
}
