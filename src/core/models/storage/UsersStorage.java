
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

public class UsersStorage {
    
    // Instancia Singleton
    private static UsersStorage instance;
    
    // Atributos del Storage
    private ArrayList<User> users;
    
    private UsersStorage() {
        this.users = new ArrayList<>();
    }
    
    public static UsersStorage getInstance() {
        if (instance == null) {
            instance = new UsersStorage();
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
