package Hire;

import java.util.ArrayList;
import java.util.Collection;

public class Company
{
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
        //Я не понимаю, как реализовать эти методы? я верно выбрал тактику?
    }
    public void getFireManager(int id){
        employees.remove(Manager);
    }
    public void getFireTopManager(int id){
        employees.remove(TopManager);
    }
    public void getHireAllOperators(){
        employees.addAll((Collection<? extends Employee>) new Operator());
        //а с этими, так вообще беда, нужно 3 метода для каждого из классов сотрудников? или можно как-то реализовать одним?
    }
    public void getHireAllManagers(){
        employees.addAll((Collection<? extends Employee>) new Manager());
    }
    public void getHireAllTopManagers(){
        employees.addAll((Collection<? extends Employee>) new TopManager());
    }
    public double getIncome(){
        //и самое интересное, когда я кидаю класс топ мэнеджер в комменты и всё что с ним связано,
        // у меня считает все и выдает цифры, хоть и очень большие(больше возможного диапазона),
        //а с топом, мне ошибку выдает.
        long j;
        j = (int) employees.get(198).getMonthSalary();
        for(int i = 180; i < 270; i++){
            j = (int) ((j + employees.get(i).getMonthSalary()) * 20);
        }
        return j;
    }
}
