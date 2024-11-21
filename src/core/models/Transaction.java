
package core.models;

import core.models.utils.TransactionType;

public class Transaction {
    
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private double amount;
    
    public Transaction(TransactionType type, Account sourceAccount, Account destinationAccount, double amount) {
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public double getAmount() {
        return amount;
    }
    
}
