
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
            if (a.getId() == account.getId()) {
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }
    
    public Person getPerson(int id) {
        for (Person person : this.persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }
    
    public boolean delPerson(int id) {
        for (Person person : this.persons) {
            if (person.getId() == id) {
                this.persons.remove(person);
                return true;
            }
        }
        return false;
    }
    
}
