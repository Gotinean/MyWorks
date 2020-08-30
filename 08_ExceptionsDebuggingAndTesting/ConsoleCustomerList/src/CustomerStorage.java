import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;
    Pattern pattern = Pattern.compile("\\d+");
    Pattern pattern1 = Pattern.compile("^(\\S+)@([a-z0-9-]+)(\\.)([a-z]{2,4})(\\.?)([a-z]{0,4})+$");
    Pattern pattern2 = Pattern.compile("\\D+");


    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws Exception
    {
        String[] components = data.split("\\s+");
        String name = components[0] + " " + components[1];
        String email = components[2];
        String phone = components[3];
        if (components[0].matches(String.valueOf(pattern2)) && (components[1].matches(String.valueOf(pattern2)))){
            if(email.matches(String.valueOf(pattern1))){
                if (phone.matches(String.valueOf(pattern))){
                    storage.put(name, new Customer(name, email, phone));
                }
                else {System.out.println("Неверный формат ввода номера телефона, пример: 79215637722");}
            }
            else {System.out.println("Неверный формат ввода e-mail, пример: my@mail.com");}
        }
        else {
            System.out.println("Неверный формат ввода имени и фамилии, пример: Иван Иванов");
        }
    }

    public void listCustomers() throws Exception
    {
        if(storage.size() != 0) {
            storage.values().forEach(System.out::println);
        }
        else {
            System.out.println("Список пока-что пуст, сначала введите 1 и/или более личностей ");
        }
    }

    public void removeCustomer(String name) throws Exception
    {
        if(storage.equals(name)) {
            storage.remove(name);
        }
        else {
            System.out.println("Данной личности итак нет в нашем списке");
        }
    }

    public int getCount() throws Exception
    {
        return storage.size();
    }
}