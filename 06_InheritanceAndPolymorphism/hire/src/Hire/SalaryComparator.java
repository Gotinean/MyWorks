package Hire;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee>  {
    @Override
    public int compare(Employee employee, Employee employee2) {
        return Double.compare(employee2.getMonthSalary(), employee.getMonthSalary());
    }
}
