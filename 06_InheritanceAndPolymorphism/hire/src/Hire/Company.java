package Hire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Company
{
    private double companyIncome;
    ArrayList<Employee> employees = new ArrayList<>();
    public void hire(Employee employee){
        this.employees.add(employee);
    }
    public void hireAll(Employee employee){
        this.employees.addAll(employees);
    }
    public void fire(int capacity){
        for(int i = 0; i < capacity; i++) {
            employees.remove(employees.get(i));
        }
    }
    public double getIncome(){
        for (Employee employee : employees) {
            companyIncome += employee.getSale();
        }
        return companyIncome;
    }
    public List<Employee> getTopSalaryStaff(int count){
        List<Employee> employeeListTop = new ArrayList<>();
        for(int i = 0; i < count; i++){
            employeeListTop.add(employees.get(i));
        }
        employeeListTop.sort((employee, employee2) -> Double.compare(employee.getMonthSalary(), employee2.getMonthSalary()));
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        Collections.reverse(employees);
        return employees.subList(0, count);
    }
    public List<Employee> getLowestSalaryStaff(int count){
        List<Employee> employeeList = new ArrayList<>();
        for(int i = 0; i < count; i++){
            employeeList.add(employees.get(i));
        }
        employeeList.sort((employee, employee2) -> Double.compare(employee2.getMonthSalary(), employee.getMonthSalary()));
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        Collections.reverse(employeeList);

        return employees.subList(0, count);
    }
}
