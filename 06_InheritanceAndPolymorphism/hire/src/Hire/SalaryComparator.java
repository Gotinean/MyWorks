package Hire;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee>  {
    @Override
    public int compare(Employee employee, Employee t1) {
        return Double.compare(t1.getMonthSalary(), employee.getMonthSalary());
    }
}
