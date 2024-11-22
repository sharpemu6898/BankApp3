
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
    
    public boolean addUser(User user) {
        for (User u : this.users) {
            if (u.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }
    
    public ArrayList<User> getUsers() {
        return users;
    }
}
