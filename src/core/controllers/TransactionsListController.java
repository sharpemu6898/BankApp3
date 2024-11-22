/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.TransactionsStorage;

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
                storage.getTransactions().sort((obj1, obj2) -> (obj1.getAmount() - obj2.getAmount()));
                return new Response("Showing transactions list",Status.OK, storage.getTransactions());
            }
        }catch(Exception ex){
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    
    
    }
}
