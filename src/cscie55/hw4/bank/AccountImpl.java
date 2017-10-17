package cscie55.hw4.bank;

public class AccountImpl implements Account {
    private int id;
    private long balance;

    public AccountImpl(int id) {
        this.id = id;
    }

    public int id() {
        return this.id;
    }
    public long balance() {
        return this.balance;
    }
    public void deposit(long amount) throws IllegalArgumentException  {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("Amount of deposit must be positive");
        }
    }
    public void withdraw(long amount) throws InsufficientFundsException {
        if (amount < 1 ){
            throw new IllegalArgumentException("Amount of deposit must be positive");
        }
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new InsufficientFundsException(this, amount);
        }
    }
}
