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
    private boolean blocked;

    public boolean isBlocked() {
        return blocked;
    }

    public static void makeAccounts(int accId, List<Account> list) throws InterruptedException {
        int a = 0;
        long amount = a + Math.round(Math.random() * 100000);
        String b = String.valueOf(accId);
        Account account = new Account(amount, b, false);
        list.add(account);
    }


}
