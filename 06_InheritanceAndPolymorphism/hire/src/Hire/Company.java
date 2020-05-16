package Hire;

import java.util.ArrayList;
import java.util.Collection;

public class Company
{
    double sales;
    private Object Manager;
    private Object Operator;
    private Object TopManager;
    ArrayList<Employee> employees = new ArrayList<>();

    public void getHireOperator(ArrayList<Integer> persone, int id){
        employees.add(new Operator());
    }
    public void getHireManager(ArrayList<Integer> persone, int id){
        employees.add(new Manager());
    }
    public void getHireTopManager(ArrayList<Integer> persone, int id){
        employees.add(new TopManager());
    }
    public void getFireOperator(int id){
        employees.remove(Operator);
    }
    public void getFireManager(int id){
        employees.remove(Manager);
    }
    public void getFireTopManager(int id){
        employees.remove(TopManager);
    }
    public void getHireAllOperators(){
        employees.addAll((Collection<? extends Employee>) new Operator());
    }
    public void getHireAllManagers(){
        employees.addAll((Collection<? extends Employee>) new Manager());
    }
    public void getHireAllTopManagers(){
        employees.addAll((Collection<? extends Employee>) new TopManager());
    }
    public double getIncome(){
        long j;
        j = (int) employees.get(198).getMonthSalary();
        for(int i = 180; i < 270; i++){
            j = (int) ((j + employees.get(i).getMonthSalary()) * 20);
        }
        return j;
    }
}
