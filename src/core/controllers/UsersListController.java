
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.UsersStorage;

public class UsersListController { 

    //VERIFICAR QUE MUESTRE LOS USUARIOS EN LA TABLA
    
    public static Response listUsers(){
        try{
            UsersStorage storage = UsersStorage.getInstance(); 
            
            if(storage.getUsers().isEmpty()){
                return new Response ("There are no registrated users to show",Status.NOT_FOUND);
            }else{
                //Se tiene que verificar si hay solo un usuario o más?
                storage.getUsers().sort((obj1, obj2) -> (obj1.getId() - obj2.getId())); //ordena el arraylist de users ascendentemente por su id
                
                return new Response("Showing users list",Status.OK, storage.getUsers());
                
            }
        }catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
