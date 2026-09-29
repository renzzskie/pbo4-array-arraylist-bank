
import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private ArrayList<Account> accounts;

    public Customer(String f, String l) {
        this.firstName = f;
        this.lastName = l;
        this.accounts = new ArrayList<Account>();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setAccount(Account acct) {
        this.accounts.add(acct);
    }

    public Account getAccount(int accountIndex) {
        if (accountIndex >= 0 && accountIndex < this.accounts.size()) {
            return this.accounts.get(accountIndex);
        }
        return null;
    }

    public int getNumOfAccounts() {
        return this.accounts.size();
    }
}