package Hire;

import java.util.Comparator;

public class SalaryComparator implements Comparator<Employee>  {
    @Override
    public int compare(Employee employee, Employee employee2) {
        if(employee.getMonthSalary() > employee2.getMonthSalary()){
            return 1;
        }
        else if (employee.getMonthSalary() < employee2.getMonthSalary()){
            return -1;
        }
        else
        return 0;
    }
}
