
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.UsersStorage;

public class UserController {
    
    //los id de los usuarios deben ser unicos, mayores o iguales que 0 y 
    //tener a lo mas 0 digitos
    public static Response createPerson(String id, String firstname, String lastname, String age) {
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
            if (!storage.addPerson(new User(idInt, firstname, lastname, ageInt))) {
                return new Response("A user with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("User created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response readPerson(String id) {
        try {
            int idInt;
            
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            
            Person person = storage.getPerson(idInt);
            if (person == null) {
                return new Response("Person not found", Status.NOT_FOUND);
            }
            return new Response("Person found", Status.OK, person);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response updatePerson(String id, String firstname, String lastname, String age, String gender) {
        try {
            int idInt, ageInt;
            boolean genderB;
            
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            
            Person person = storage.getPerson(idInt);
            if (person == null) {
                return new Response("Person not found", Status.NOT_FOUND);
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
            
            if (gender.equals("M")) {
                genderB = false;
            } else if (gender.equals("F")) {
                genderB = true;
            } else {
                return new Response("Gender error", Status.BAD_REQUEST);
            }
            
            person.setFirstname(firstname);
            person.setLastname(lastname);
            person.setAge(ageInt);
            person.setGender(genderB);
            
            return new Response("Person data updated successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response deletePerson(String id) {
        try {
            int idInt;
            
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();
            if (!storage.delPerson(idInt)) {
                return new Response("Person not found", Status.NOT_FOUND);
            }
            return new Response("Person deleted successfully", Status.NO_CONTENT);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
