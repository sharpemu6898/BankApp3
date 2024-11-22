
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.AccountsStorage;

public class AccountsListController {
    
    //VERIFICAR QUE MUESTRE LOS USUARIOS EN LA TABLA
    
    public static Response listAccounts(){
        try{
            AccountsStorage storage = AccountsStorage.getInstance(); 
            
            if(storage.getAccounts().isEmpty()){
                return new Response ("There are no created accounts to show",Status.NOT_FOUND);
            }else{                
                
                storage.getAccounts().sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId()))); //ordena el arraylist de accounts por su id
                
                return new Response("Showing accounts list",Status.OK, storage.getAccounts());
                
            }
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
