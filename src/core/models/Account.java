
package core.models;

public class Account {
    
    private String id;
    private User owner;
    private double balance;

    public Account(String id, User owner) {
        this.id = id;
        this.owner = owner;
        this.balance = 0;
        
        this.owner.addAccount(this);
    }
    
    public Account(String id, User owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        
        this.owner.addAccount(this);
    }
    
    public String getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        this.balance += amount;
    }
    
    public boolean withdraw(double amount) {
        if (amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }
    
}
