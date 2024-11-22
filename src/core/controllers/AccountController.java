
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountsStorage;
import core.models.storage.UsersStorage;
import java.util.Random;

public class AccountController {
    
    public static Response createAccount(String userID, String initialBalance) {
        try {
            int userIDInt;
            double initialBalanceDouble;
            String accountId;
            
            //validar userID 
            try {
                userIDInt = Integer.parseInt(userID);
                if (userIDInt < 0) {
                    return new Response("ID must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            //validar Balance
            try {
                initialBalanceDouble = Double.parseDouble(initialBalance);
                if (initialBalanceDouble < 0) {
                    return new Response("Initial balance must be greater than 0", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Initial balance must be numeric", Status.BAD_REQUEST);
            }
            
            AccountsStorage accountStorage = AccountsStorage.getInstance(); 
            UsersStorage usersStorage = UsersStorage.getInstance(); 
            
            //Busqueda del usuario al que se le va a crear la cuenta
            User selectedUser = null;
            for (User user : usersStorage.getUsers()) {
                if (user.getId() == userIDInt && selectedUser == null) {
                    selectedUser = user;
                }
            }
            
            //Generación aleatorio del ID de la cuenta
            if (selectedUser != null) {
                Random random = new Random();
                int first = random.nextInt(1000);
                int second = random.nextInt(1000000);
                int third = random.nextInt(100);
                
                accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
                
                if (!accountStorage.addAccount(new Account(accountId, selectedUser, initialBalanceDouble))) {
                return new Response("The automatically generated account ID already exists, please try again", Status.INTERNAL_SERVER_ERROR);
            }
            return new Response("Account created successfully. Account ID:"+ accountId , Status.CREATED);
            
            }else{
               return new Response("The user is not registrated yet", Status.NOT_FOUND); //REVISAR
            }
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
