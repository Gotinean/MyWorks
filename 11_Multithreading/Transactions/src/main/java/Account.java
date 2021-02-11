public class Account {
    private long moneyAmount;
    private String accountNumber;
    private boolean isBlocked = false;

    public Account(long moneyAmount, String accountNumber) {
        this.moneyAmount = moneyAmount;
        this.accountNumber = accountNumber;
    }

    public long getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(long moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
