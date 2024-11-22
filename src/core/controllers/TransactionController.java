
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
import core.models.storage.AccountsStorage;
import core.models.storage.TransactionsStorage;
import core.models.utils.TransactionType;

public class TransactionController {
    
    public static Response executeTransaction(String transactionType, String sourceAccountID, String destinationAccountID, String amount) {
        
        try {
            double amountDouble;
            
            AccountsStorage accountsStorage = AccountsStorage.getInstance();
            TransactionsStorage transactionsStorage = TransactionsStorage.getInstance();
            
            try {
                amountDouble = Double.parseDouble(amount);
                if (amountDouble < 0) {
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }
            
            switch (transactionType) {
                case "Deposit": {
                    
                    if (destinationAccountID.equals("")) {
                        return new Response("Destination account must be not empty", Status.BAD_REQUEST);
                    }
                    
                    Account destinationAccount = null;
                    for (Account account : accountsStorage.getAccounts()) {
                        if (account.getId().equals(destinationAccountID)) {
                            destinationAccount = account;
                        }
                    }
                    
                    if (destinationAccount != null) {
                        destinationAccount.deposit(amountDouble);
                        transactionsStorage.addTransaction(new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amountDouble));
                        return new Response ("Deposit executed succesfully", Status.CREATED);
                    }else{
                        return new Response ("Destination account does not exist", Status.NOT_FOUND);
                    }
                }
                case "Withdraw": {
                    
                    if (sourceAccountID.equals("")) {
                        return new Response("Source account must be not empty", Status.BAD_REQUEST);
                    }
                    
                    Account sourceAccount = null;
                    for (Account account : accountsStorage.getAccounts()) {
                        if (account.getId().equals(sourceAccountID)) {
                            sourceAccount = account;
                        }
                    }
                    
                    if (sourceAccount != null) {
                        if(!sourceAccount.withdraw(amountDouble)){
                            return new Response("You can't withdraw an amount greater than the source account balance", Status.BAD_REQUEST);
                        }else{
                            transactionsStorage.addTransaction(new Transaction(TransactionType.WITHDRAW, sourceAccount, null, amountDouble));
                            return new Response ("Withdraw executed succesfully", Status.CREATED);
                        }
                    }else{
                        return new Response ("Source account does not exist", Status.NOT_FOUND);
                    }
                }
                case "Transfer": {
                    
                    if (sourceAccountID.equals("")) {
                        return new Response("Source account must be not empty", Status.BAD_REQUEST);
                    }
                    
                    if (destinationAccountID.equals("")) {
                        return new Response("Destination account must be not empty", Status.BAD_REQUEST);
                    }
                    
                    Account sourceAccount = null;
                    for (Account account : accountsStorage.getAccounts()) {
                        if (account.getId().equals(sourceAccountID)) {
                            sourceAccount = account;
                        }
                    }
                    
                    Account destinationAccount = null;
                    for (Account account : accountsStorage.getAccounts()) {
                        if (account.getId().equals(destinationAccountID)) {
                            destinationAccount = account;
                        }
                    }
                    
                    if (sourceAccount != null) {
                        if (destinationAccount != null){
                            if (sourceAccount.withdraw(amountDouble)){
                                destinationAccount.deposit(amountDouble);
                                transactionsStorage.addTransaction(new Transaction(TransactionType.TRANSFER, sourceAccount, destinationAccount, amountDouble));
                                return new Response ("Transfer executed succesfully", Status.CREATED);
                            }else{
                                return new Response ("You can't transfer an amount greater than the source account balance",Status.BAD_REQUEST);
                            }
                            
                        }else{
                            return new Response ("Destination account does not exist", Status.NOT_FOUND);
                        }
                    }else{
                        return new Response ("Source account does not exist", Status.NOT_FOUND);
                    }
                }
                default: {
                    return new Response ("Invalid transaction type",Status.BAD_REQUEST);
                }
            }
        } catch (Exception ex) {
           return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
