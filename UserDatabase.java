import java.util.ArrayList;
/**
 * The UserDatabase class is a Singleton that serves as a repository for all of
 * the Users in the Internship system.
 * @author Joshua DuPuis
 */
public class UserDatabase {
    private UserDatabase userDatabase;
    private ArrayList<User> users;

    /**
     * The UserDatabase constructor initializes the UserDatabase, which holds
     * all of the User accounts on the internship system
     */
    private UserDatabase() {

    }

    /**
     * The addUser method adds a new User to the UserDatabase
     */
    public void addUser() {

    }

    /**
     * The getInstance method returns the one instance of the UserDatabase
     * @return
     */
    public static UserDatabase getInstance() {
        return null;
    }

    /**
     * The getUsers method returns the list of all of the users in the system
     * @return An ArrayList of all the users who have an account in the system
     */
    public ArrayList<User> getUsers(){
        return null;
    }
}
