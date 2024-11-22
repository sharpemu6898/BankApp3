/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Transaction;
import core.models.storage.TransactionsStorage;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author AAAAA
 */
public class TransactionsListController {
    
    public static Response listTransactions(){
        try{
            TransactionsStorage storage=TransactionsStorage.getInstance();
            if(storage.getTransactions().isEmpty()){
                return new Response("There are no registrated transactions to show",Status.NOT_FOUND);
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
