import java.util.ArrayList;

/*
* A class that manages the accounts, more like a database of accounts
* */
public class UserManager
{
    public ArrayList<User> accounts;
    
    public UserManager() throws Exception {
        accounts= new ArrayList<User>();

        // Accounts
        accounts.add(new User("Jorene Apuya","200000",5000,1000,"1234567891011123",true));
        accounts.add(new User("John Doe","708787",5000,500,"1234567891011124",true));
        accounts.add(new User("Jane Doe","608778",4000,500,"1234567891011133",true));
    }

    // Finds the user with pin
    public User find_user_with_pin(String pin) throws Exception {
        for (User user: accounts){
            if(user.get_pin().equals(pin)){
                return user;
            }
        }
        throw new Exception("Cannot find user with this pin: "+pin);
    }
    // Finds the user with id
    public User find_user_with_id(String id) throws Exception {
        for (User user: accounts){
            if(user.get_id().equals(id)){
                return user;
            }
        }
        throw new Exception("Cannot find user with this id: "+id);
    }
    
}
