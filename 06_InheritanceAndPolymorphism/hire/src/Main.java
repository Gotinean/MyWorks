import Hire.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Generation generation = new Generation();
        ArrayList <Integer> persone = new ArrayList<>();
        for(int i = 0; i < 280; i++){
            int newPersone = generation.getNewPersone();
            persone.add(i,newPersone);
        }
        Company company = new Company();
        for(int i = 0; i < 180; i++) {
            company.getHireOperator(persone,i);
        }
        for(int i = 180; i < 270; i++) {
            company.getHireManager(persone,i);
        }
        for(int i = 270; i < 280; i++) {
            company.getHireTopManager(persone,i);
        }
        System.out.println(company.getIncome());
    }
}
