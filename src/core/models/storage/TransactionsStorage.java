
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
    
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
    
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}