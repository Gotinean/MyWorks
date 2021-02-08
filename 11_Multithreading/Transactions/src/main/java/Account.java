import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
@Data
@AllArgsConstructor
public class Account
{
    private long money;
    private String accNumber;

    public static void makeAccounts(int accId, List<Account> list) throws InterruptedException {

        int a = 0;
        long amount = a + Math.round(Math.random() * 100000);
        String b = String.valueOf(accId);
        Account account = new Account(amount, b);
        list.add(account);

    }


}
