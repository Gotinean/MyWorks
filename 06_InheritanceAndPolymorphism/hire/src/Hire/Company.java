package Hire;

import java.util.ArrayList;

public class Company
{
    double sales;
    ArrayList<String> Employee = new ArrayList<>();
    private Object Manager;
    private Object Operator;
    private Object TopManager;

    public ArrayList<String> getEmployee() {
        return Employee;
    }
    public Employee getHireOperator(){
        for(int i = 0; i < 180; i++){
            Employee.equals(i);
        }
        return (Hire.Employee) Operator;
    }
    public Employee getHireManager(){
        for (int i = 180; i < 260; i++){
            Employee.equals(i);
        }
        return (Hire.Employee) Manager;
    }
    public Employee getHireTopManager(){
        for(int i = 180; i < 270; i++){
            Employee.equals(i);
        }
        return (Hire.Employee) TopManager;
    }
    public void getFire(){

    }
    public void getHireAll(){

    }
    public double getIncome(){
        return 0;
    }
}
