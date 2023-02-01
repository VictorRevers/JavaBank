package classes;

import java.util.List;
import java.util.ArrayList;

public class User {
    public int id;
    public double balance;
    public String name;
    public String cpf;
    public String password;
    
    public ArrayList<User> usersDbInit(){
        User user = new User();
        user.cpf = "00000";
        user.id = 0;
        
        ArrayList users = new ArrayList();       
        users.add(user);
                   
        return users;
    }    
}
