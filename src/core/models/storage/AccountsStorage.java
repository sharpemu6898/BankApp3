
package core.models.storage;

import java.util.ArrayList;
import core.models.Account;

public class AccountsStorage {
    
    // Instancia Singleton
    private static AccountsStorage instance;
    
    // Atributos del Storage
    private ArrayList<Account> accounts;
    
    private AccountsStorage() {
        this.accounts = new ArrayList<>();
    }
    
    public static AccountsStorage getInstance() {
        if (instance == null) {
            instance = new AccountsStorage();
        }
        return instance;
    }
    
    public boolean addAccount(Account account) {
        for (Account a : this.accounts) {
            if (a.getId().equals(account.getId())) {
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }    
}
