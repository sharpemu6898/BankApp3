
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Transaction;
import core.models.storage.TransactionsStorage;
import java.util.ArrayList;
import java.util.Collections;

public class TransactionsListController {
    
    public static Response listTransactions(){
        try{
            TransactionsStorage storage=TransactionsStorage.getInstance();
            if(storage.getTransactions().isEmpty()){
                return new Response("There are no executed transactions to show",Status.NOT_FOUND);
            }else{
                ArrayList<Transaction> transactionsCopy = (ArrayList<Transaction>) storage.getTransactions().clone();
                Collections.reverse(transactionsCopy);
                return new Response("Showing transactions list",Status.OK, transactionsCopy);
            }
        }catch(Exception ex){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    
    
    }
}
