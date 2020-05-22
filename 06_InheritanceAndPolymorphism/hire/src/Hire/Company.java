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
        int count = ((employees.size() / 1)*capacity) / 100;
        for(int i = 0; i < count; i++) {
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
//        List<Employee> employeeListTop = new ArrayList<>();
//        employeeListTop.sort(new SalaryComparator());
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        Collections.reverse(employees);
//        for(int i = 0; i < count; i++){
//            employeeListTop.add(employees.get(i));
//        }

        return employees.subList(0, count);
    }
    public List<Employee> getLowestSalaryStaff(int count){
//        List<Employee> employeeList = new ArrayList<>();
//        employeeList.sort(new SalaryComparator());
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        Collections.reverse(employees);
//        for(int i = 0; i < count; i++){
//            employeeList.add(employees.get(i));
//        }
        return employees.subList(count, 0);
    }
}
