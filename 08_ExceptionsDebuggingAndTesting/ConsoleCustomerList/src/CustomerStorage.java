import java.util.HashMap;
import java.util.regex.Pattern;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;
    Pattern pattern = Pattern.compile("\\d+");
    Pattern pattern1 = Pattern.compile("^(\\S+)@([a-z0-9-]+)(\\.)([a-z]{2,4})(\\.?)([a-z]{0,4})+$");


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
        if (email.matches(String.valueOf(pattern1)) && (phone.matches(String.valueOf(pattern)))){
            storage.put(name, new Customer(name, email, phone));
        }
        else {
            System.out.println("Вы ввели неправильные данные, Проверьте правильность ввода E-mail или номера телефона и попробуйте ещё раз");
        }


    }

    public void listCustomers() throws Exception
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) throws Exception
    {
        storage.remove(name);
    }

    public int getCount() throws Exception
    {
        return storage.size();
    }
}