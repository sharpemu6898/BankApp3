
package core.models.storage;

import core.models.Transaction;
import java.util.ArrayList;

public class TransactionsStorage {
    
    // Instancia Singleton
    private static TransactionsStorage instance;
    
    // Atributos del Storage
    private ArrayList<Transaction> transactions;
    
    private TransactionsStorage() {
        this.transactions = new ArrayList<>();
    }
    
    public static TransactionsStorage getInstance() {
        if (instance == null) {
            instance = new TransactionsStorage();
        }
        return instance;
    }
    
    public boolean addPerson(Person person) {
        for (Person p : this.persons) {
            if (p.getId() == person.getId()) {
                return false;
            }
        }
        this.persons.add(person);
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
