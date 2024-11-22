
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.UsersStorage;

public class UserController {
    
    //falta
    //los id de los usuarios deben tener máximo 9 digitos (PENDIENTE)
    //La edad de los usuarios debe ser mayor o igual a 18. (PENDIENTE)
    
    public static Response registerUser(String id, String firstname, String lastname, String age) {
        try {
            int idInt, ageInt;
            
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                ageInt = Integer.parseInt(age);
                if (ageInt < 0) {
                    return new Response("Age must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }
            
            UsersStorage storage = UsersStorage.getInstance();       
            if (!storage.addUser(new User(idInt, firstname, lastname, ageInt))) {
                return new Response("A user with that ID already exists", Status.BAD_REQUEST);
            }
            return new Response("User registrated successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
