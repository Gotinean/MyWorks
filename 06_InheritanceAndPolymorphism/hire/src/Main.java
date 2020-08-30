import Hire.*;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        for (int i = 0; i < 180; i++) {
            Operator operator = new Operator();
            company.hire(operator);
        }

        for (int i = 0; i < 80; i++) {
            Manager manager = new Manager(3000);
            company.hire(manager);
        }

        for (int i = 0; i < 10; i++) {
            TopManager topManager = new TopManager(company);
            company.hire(topManager);
        }


        System.out.println("До увольнения: \n");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee.getMonthSalary() + "руб");
        }
        System.out.println("" + "\n");
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee.getMonthSalary() + "руб");
        }
        try {
            company.fire(150);
        }
        catch (Exception ex){
            System.out.println("Число уволенных сотрудников превышает их колличество" );
        };
        System.out.println("После увольнения: \n");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee.getMonthSalary() + "руб");
        }
        System.out.println("" + "\n");
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee.getMonthSalary() + "руб");
        }

    }
}
