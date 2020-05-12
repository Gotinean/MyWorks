import Hire.*;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        String person = null;
        for(int i = 0; i < 270; i++){
            person = "Person" + i;
            company.getEmployee().add(i,person);
        }
        System.out.println(company.getEmployee().size());

    }
}
