import Hire.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        Operator operator = new Operator();
        Manager manager = new Manager();
        TopManager topManager = new TopManager(company);
        List<Employee> operators = new ArrayList<>();
        List<Employee> managers = new ArrayList<>();
        List<Employee> topManagers = new ArrayList<>();
        for (int i = 0; i < 180; i++) {
            operators.add(new Operator());
            company.hire(operator);
        }
        for(int i = 0; i < 80; i++){
            managers.add(new Manager());
            company.hire(manager);
        }
        for(int i = 0; i < 10; i++){
            topManagers.add(new TopManager(company));
            company.hire(topManager);
        }
        System.out.println("До увольнения: \n");
        for(Employee employee : company.getTopSalaryStaff(15)){
            System.out.println(employee.getMonthSalary() + "руб");
        }
        System.out.println("" + "\n");
        for(Employee employee : company.getLowestSalaryStaff(30)){
            System.out.println(employee.getMonthSalary() + "руб");
        }

        company.fire(90);
        System.out.println("После увольнения: \n");
        for(Employee employee : company.getTopSalaryStaff(15)){
            System.out.println(employee.getMonthSalary() + "руб");
        }
        System.out.println("" + "\n");
        for(Employee employee : company.getLowestSalaryStaff(30)){
            System.out.println(employee.getMonthSalary() + "руб");
        }

    }
}
