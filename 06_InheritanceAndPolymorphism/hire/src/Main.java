import Hire.*;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        Operator operator = new Operator();
        Manager manager = new Manager(3000);
        TopManager topManager = new TopManager(company);
        for (int i = 0; i < 180; i++) {
            company.hire(operator);
        }

        for (int i = 0; i < 80; i++) {
            company.hire(manager);
        }

        for (int i = 0; i < 10; i++) {
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
