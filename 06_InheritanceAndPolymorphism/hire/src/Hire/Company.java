package Hire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company {
    private double companyIncome;
    ArrayList<Employee> employees = new ArrayList<>();

    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    public void hireAll(Employee employee) {
        this.employees.addAll(employees);
    }

    public void fire(int capacity) {
        if (capacity > employees.size()) {
            System.out.println("Число уволенных сотрудников превышает их колличество, максимальное число: " + employees.size());
        } else {
            for (int i = 0; i < capacity; i++) {
                employees.remove(employees.get(i));
            }
        }
    }

    public double getIncome() {
        for (Employee employee : employees) {
            companyIncome += employee.getSale();
        }
        return companyIncome;
    }

    public List<Employee> getTopSalaryStaff(int count) {
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        Collections.reverse(employees);
        return employees.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        employees.sort((employee, employee2) -> Double.compare(employee2.getMonthSalary(), employee.getMonthSalary()));
        Collections.reverse(employees);
        return employees.subList(0, count);
    }
}
