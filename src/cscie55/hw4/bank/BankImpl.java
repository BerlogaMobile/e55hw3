package cscie55.hw4.bank;

import java.util.HashMap;
import java.util.Map;

public class BankImpl implements Bank{
    private Map<Integer,Account> accounts;

    public BankImpl() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(Account account) throws DuplicateAccountException {
        if (!accounts.containsKey(account.id())) {
            accounts.put(account.id(), account);
        } else {
            throw new DuplicateAccountException(account.id());
        }
    }
    public void transferWithoutLocking(int fromId, int toId, long amount) throws InsufficientFundsException {
        if (accounts.containsKey(fromId) && accounts.containsKey(toId)) {
            Account fromAcct = accounts.get(fromId);
            Account toAcct = accounts.get(toId);
            if (fromAcct.balance() >= amount) {
                fromAcct.withdraw(amount);
                toAcct.deposit(amount);
            } else {
                throw new InsufficientFundsException(fromAcct,amount);
            }
        }
    }
    public synchronized void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException{
        if (accounts.containsKey(fromId) && accounts.containsKey(toId)) {
            Account fromAcct = accounts.get(fromId);
            Account toAcct = accounts.get(toId);
            if (fromAcct.balance() >= amount) {
                fromAcct.withdraw(amount);
                toAcct.deposit(amount);
            } else {
                throw new InsufficientFundsException(fromAcct,amount);
            }
        }
    }
    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException {
        if (accounts.containsKey(fromId) && accounts.containsKey(toId)) {
            Account fromAccount = accounts.get(fromId);
            Account toAccount = accounts.get(toId);
            Account firstLock, secondLock;
            if (fromAccount.id() == toAccount.id())
                return;
            else if (fromAccount.id() < toAccount.id()) {
                firstLock = fromAccount;
                secondLock = toAccount;
            }
            else {
                firstLock = toAccount;
                secondLock = fromAccount;
            }
            synchronized (firstLock) {
                synchronized (secondLock) {
                    if (fromAccount.balance()>=amount){
                        fromAccount.withdraw(amount);
                        toAccount.deposit(amount);
                    } else {
                        throw new InsufficientFundsException(fromAccount,amount);
                    }
                }
            }
        }
    }
    public long totalBalances(){
        return accounts.values().stream().mapToLong(account->account.balance()).sum();
    }
    public int numberOfAccounts(){
        return accounts.size();
    }

}
